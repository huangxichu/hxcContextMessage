package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "cms", catalog = "")
public class Company implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COMPANY_CODE")
    private String companyCode;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATE_TIME")
    private Date createTime;


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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Objects.equals(name, company.name) &&
                Objects.equals(companyCode, company.companyCode) &&
                Objects.equals(status, company.status) &&
                Objects.equals(createTime, company.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, companyCode, status, createTime);
    }
}
