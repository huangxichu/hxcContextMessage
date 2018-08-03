package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class News implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NEW_CATEGORY_ID")
    private Integer newCategoryId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "SUB_TITLE")
    private String subTitle;
    @Column(name = "CONTEXT",columnDefinition = "longtext")
    private String context;
    @Column(name = "IS_ORIGINAL")
    private String isOriginal;
    @Column(name = "SOURCES")
    private String sources;
    @Column(name = "SOUR_URL")
    private String sourUrl;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Column(name = "COMPANY_CODE")
    private String companyCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getSourUrl() {
        return sourUrl;
    }

    public void setSourUrl(String sourUrl) {
        this.sourUrl = sourUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(newCategoryId, news.newCategoryId) &&
                Objects.equals(title, news.title) &&
                Objects.equals(subTitle, news.subTitle) &&
                Objects.equals(context, news.context) &&
                Objects.equals(isOriginal, news.isOriginal) &&
                Objects.equals(sources, news.sources) &&
                Objects.equals(sourUrl, news.sourUrl) &&
                Objects.equals(status, news.status) &&
                Objects.equals(createTime, news.createTime) &&
                Objects.equals(updateTime, news.updateTime) &&
                Objects.equals(companyCode, news.companyCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, newCategoryId, title, subTitle, context, isOriginal, sources, sourUrl, status, createTime, updateTime, companyCode);
    }
}
