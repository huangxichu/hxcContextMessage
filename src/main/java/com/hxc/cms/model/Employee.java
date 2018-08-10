package com.hxc.cms.model;

import com.hxc.cms.utils.ObjectUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "cms", catalog = "")
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
//    @Column(name = "COMPANY_ID")
//    private Integer companyId;
    @Column(name = "DEPT_ID")
    private Integer deptId;
    @Column(name = "REL_NAME")
    private String relName;
    @Column(name = "ID_CARD")
    private String idCard;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "ADRESS")
    private String adress;
    @Column(name = "PHONE")
    private String phone;
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

//    public Integer getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(Integer companyId) {
//        this.companyId = companyId;
//    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public static Employee copy(Employee employee) {
        Employee e = null;
        if(ObjectUtil.isNotBlank(employee)){
            e = new Employee();
            e.setId(employee.getId());
            e.setRelName(employee.getRelName());
            e.setCompanyCode(employee.getCompanyCode());
            e.setPhone(employee.getPhone());
            e.setSex(employee.getSex());
            e.setStatus(employee.getStatus());
            e.setAdress(employee.getAdress());
            e.setCreateTime(employee.getCreateTime());
            e.setIdCard(employee.getIdCard());
            e.setCreateTime(employee.getCreateTime());
        }
        return e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
//                Objects.equals(companyId, employee.companyId) &&
                Objects.equals(deptId, employee.deptId) &&
                Objects.equals(relName, employee.relName) &&
                Objects.equals(idCard, employee.idCard) &&
                Objects.equals(sex, employee.sex) &&
                Objects.equals(adress, employee.adress) &&
                Objects.equals(phone, employee.phone) &&
                Objects.equals(status, employee.status) &&
                Objects.equals(createTime, employee.createTime) &&
                Objects.equals(companyCode, employee.companyCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id,
//                companyId,
                deptId, relName, idCard, sex, adress, phone, status, createTime, companyCode);
    }
}
