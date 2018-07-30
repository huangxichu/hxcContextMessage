package com.hxc.cms.param;

public class NewsParam extends PageParam{


    private Integer newCategoryId;

    private String title;

    private String subTitle;

    private String context;

    private String isOriginal;

    private String sources;

    public Integer getNewCategoryId() {
        return newCategoryId;
    }

    public void setNewCategoryId(Integer newCategoryId) {
        this.newCategoryId = newCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(String isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }
}
