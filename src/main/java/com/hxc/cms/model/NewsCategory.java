package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "news_category", schema = "cms", catalog = "")
public class NewsCategory implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "IDE")
    private Integer ide;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "COMPANY_CODE")
    private String companyCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIde() {
        return ide;
    }

    public void setIde(Integer ide) {
        this.ide = ide;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        NewsCategory that = (NewsCategory) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status) &&
                Objects.equals(ide, that.ide) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(companyCode, that.companyCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, status, ide, createTime, companyCode);
    }
}
