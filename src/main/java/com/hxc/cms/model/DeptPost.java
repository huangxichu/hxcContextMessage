package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dept_post", schema = "cms", catalog = "")
public class DeptPost implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "POST_ID")
    private Integer postId;
    @Column(name = "DEPT_ID")
    private Integer deptId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptPost deptPost = (DeptPost) o;
        return id == deptPost.id &&
                Objects.equals(postId, deptPost.postId) &&
                Objects.equals(deptId, deptPost.deptId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, postId, deptId);
    }
}
