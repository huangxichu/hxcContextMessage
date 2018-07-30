package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "cms", catalog = "")
public class Post implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "POST_NAME")
    private String postName;
    @Column(name = "STATUS")
    private String status;
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

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(postName, post.postName) &&
                Objects.equals(status, post.status) &&
                Objects.equals(createTime, post.createTime) &&
                Objects.equals(companyCode, post.companyCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, postName, status, createTime, companyCode);
    }
}
