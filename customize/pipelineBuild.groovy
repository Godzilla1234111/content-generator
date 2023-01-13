#!groovy

@Library('jenkins-pipeline-library') _

def buildArtifact(String branchName) {
    echo "Build artifact"
    sh """
        ./gradlew \
            -i \
            -Ddebuginfo=true \
            -PbranchName="${branchName}" \
            --refresh-dependencies \
            clean \
            infoGit \
            infoExtension \
            build
    """
}

def generateReports(String branchName) {
    echo "Generate test/coverage reports"
    sh """
        ./gradlew -i \
            -Ddebuginfo=true \
            -PbranchName="${branchName}" \
            jacocoTestReport
    """
}

def checkLicenses(String branchName) {
    echo "Check dependency licenses"
    sh """
        ./gradlew \
            -i \
            -Ddebuginfo=true \
            -PbranchName="${branchName}" \
            checkLicensePreparation \
            generateLicenseReport \
            checkLicense
    """
}

def qualityReport(String branchName, String sonarToken) {
    echo "Check sonarqube"
    sh """
        ./gradlew \
            -i \
            -Ddebuginfo=true \
            -PbranchName="${branchName}" \
            -Psonar.login="${sonarToken}" \
            sonarqube
    """
}

def checkoutFromGit(String branchName, String repositoryUrl, String credentialsId) {
    echo "Branch: ${branchName}"
    checkout([
            $class: 'GitSCM',
            branches: [[name: branchName]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [[
                                 $class: 'SubmoduleOption',
                                 disableSubmodules: false,
                                 parentCredentials: true,
                                 recursiveSubmodules: true,
                                 reference: '',
                                 trackingSubmodules: false
                         ]],
            submoduleCfg: [],
            userRemoteConfigs: [[
                                        credentialsId: "${credentialsId}",
                                        url: repositoryUrl
                                ]]
    ])
}

def uploadJarToArtifactory(String branchName, String credentialsId) {
    withCredentials([
            usernamePassword(credentialsId: "${credentialsId}",
                    usernameVariable: 'ARTIFACTORY_USERNAME', passwordVariable: 'ARTIFACTORY_PASSWORD')
    ]) {
        echo "Upload JAR to Artifactory"
        sh """
            ./gradlew \
                -i \
                -Ddebuginfo=true \
                -PbranchName="${branchName}" \
                artifactoryPublish
        """
    }
    def buildInfo = readProperties  file:'build/buildInfo.properties'
    def buildName = buildInfo['buildInfo.build.name']
    def buildNumber = buildInfo['buildInfo.build.number']
    artifactory.xrayScan(buildName, buildNumber, false)
}

/**
 * Build and upload docker images to Artifactory
 *
 * @param branchName
 * @param credentialsId
 * @return
 */
def uploadImageToArtifactory(String branchName, String credentialsId) {
    withCredentials([
            usernamePassword(credentialsId: "${credentialsId}",
                    usernameVariable: 'ARTIFACTORY_USERNAME', passwordVariable: 'ARTIFACTORY_PASSWORD')
    ]) {
        echo "Upload Docker Image to Artifactory"
        sh """
            ./gradlew \
                -i \
                -Ddebuginfo=true \
                -PbranchName="${branchName}" \
                jib
        """
    }
}

/**
 * Build and upload docker image to both Artifactory, ACR and ECR
 *
 * @param branchName
 * @param credentialsId
 * @return
 */
def uploadImageToRegistries(String branchName, String artifactoryCredentialId, String acrCredentialId, String jenkinsName) {
    withCredentials([
            usernamePassword(credentialsId: "${artifactoryCredentialId}",
                    usernameVariable: 'ARTIFACTORY_USERNAME', passwordVariable: 'ARTIFACTORY_PASSWORD'),
            usernamePassword(credentialsId: "${acrCredentialId}",
                    usernameVariable: 'ACR_USERNAME', passwordVariable: 'ACR_PASSWORD'),
            string(credentialsId: params.AWS_ACCESS_KEY_ID_CREDS, variable: "AWS_ACCESS_KEY_ID"),
            string(credentialsId: params.AWS_SECRET_ACCESS_KEY_CREDS, variable: "AWS_SECRET_ACCESS_KEY")
    ]) {
        echo "Upload Docker Image to registries"
        sh """
            ./gradlew \
                -i \
                -Ddebuginfo=true \
                -PbranchName="${branchName}" \
                -Pjenkins.name="${jenkinsName}" \
                uploadDockerImage
        """
    }
}

def buildUploadHelmToArtifactory(String branchName, String credentialsId) {
    withCredentials([
            usernamePassword(credentialsId: "${credentialsId}",
                    usernameVariable: 'ARTIFACTORY_USERNAME', passwordVariable: 'ARTIFACTORY_PASSWORD')
    ]) {
        echo "Build and upload helm charts archive to Artifactory"
        sh """
            ./gradlew \
                -i \
                -Ddebuginfo=true \
                -PbranchName="${branchName}" \
                generateChartArchive \
                uploadChartArchive
        """
    }
}

def publishTestReports(String rDir, String rName) {
    echo "Publishing ${rName}"
    publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: rDir, reportFiles: 'index.html', reportName: rName, reportTitles: ''])
}

/**
 * Shows the build status in Bitbucket.
 *
 * @param state The build status
 * @param bitbucketCreds Bitbucket credentials to commit build status
 * @param bitbucketBSURL Bitbucket build status URL
 */
def showBuild(String state, String bitbucketCreds, String bitbucketBSURL) {
    withCredentials([
            string(credentialsId: bitbucketCreds, variable: 'TOKEN')
    ]) {
        sh """
            curl \
                -X POST \
                -H 'Authorization: Bearer \$TOKEN' \
                -H 'Content-Type: application/json' \
                -d '{ \
                    "state":"${state}", \
                    "key":"${env.JOB_NAME}", \
                    "url":"${env.BUILD_URL}" \
                }' \
                "${bitbucketBSURL}${env.GIT_COMMIT}"
        """
    }
}

return this