package com.nexthink.ContentGenerator;

public class SysConfig {
        private static final String TENANT_UUID = "TENANT_UUID";
        private static final String POD_NAMESPACE = "POD_NAMESPACE";
        private static final String tenantUUID = System.getenv(TENANT_UUID);
        private static final String podNamespace = System.getenv(POD_NAMESPACE);
        public static String getTenantUUID() {
            return tenantUUID;
        }
        public static String getPodNamespace() {
            return podNamespace;
        }
}