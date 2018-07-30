package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dept_emp", schema = "cms", catalog = "")
public class DeptEmp implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "EMP_ID")
    private Integer empId;
    @Column(name = "DEPT_ID")
    private Integer deptId;
    @Column(name = "POST_ID")
    private Integer postId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEmp deptEmp = (DeptEmp) o;
        return id == deptEmp.id &&
                Objects.equals(empId, deptEmp.empId) &&
                Objects.equals(deptId, deptEmp.deptId) &&
                Objects.equals(postId, deptEmp.postId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, empId, deptId, postId);
    }
}
