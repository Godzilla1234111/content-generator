package com.nexthink.ContentGenerator;

public class ViewDomain  {
    private Integer hierarchyId;
    private Boolean noneSelected;
    private All all;

    public ViewDomain(Integer hierarchyId, Boolean noneSelected, All all) {
        this.hierarchyId = hierarchyId;
        this.noneSelected = noneSelected;
        this.all = all;
    }

    public ViewDomain(int hierarchyId, boolean noneSelected) {

    }

    public Integer getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public Boolean getNoneSelected() {
        return noneSelected;
    }

    public void setNoneSelected(Boolean noneSelected) {
        this.noneSelected = noneSelected;
    }

    public All getAll() {
        return all;
    }

    public void setAll(All all) {
        this.all = all;
    }

}