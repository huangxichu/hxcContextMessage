package com.hxc.cms.model;

import com.hxc.cms.utils.ObjectUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_info", schema = "cms", catalog = "")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
//    @Column(name = "EMP_ID")
//    private Integer empId;
    @Column(name = "LOGIN_CODE")
    private String loginCode;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;
    @Column(name = "LAST_LOGIN_IP")
    private String lastLoginIp;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "COMPANY_CODE")
    private String companyCode;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMP_ID")
    private Employee employee;
    @Transient
    private Integer empId;

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

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public static UserInfo copy(UserInfo userInfo){
        UserInfo u = null;
        if(ObjectUtil.isNotBlank(userInfo)){
            u = new UserInfo();
            u.setId(userInfo.getId());
            u.setCompanyCode(userInfo.getCompanyCode());
            u.setLoginCode(userInfo.getLoginCode());
            u.setPassword(userInfo.getPassword());
            u.setStatus(userInfo.getStatus());
            u.setCreateTime(userInfo.getCreateTime());
            u.setLastLoginIp(userInfo.getLastLoginIp());
            u.setLastLoginTime(userInfo.getLastLoginTime());
            Employee employee = Employee.copy(userInfo.getEmployee());
            if(employee != null){
                u.setEmpId(employee.getId());
                u.setEmployee(employee);
            }
        }
        return u;
    }

    public static List<UserInfo> copy(List<UserInfo> userInfos){
        List<UserInfo> list = new ArrayList<>();
        if(ObjectUtil.isNotBlank(userInfos)){
            for(UserInfo userInfo : userInfos){
                UserInfo u = UserInfo.copy(userInfo);
                if(u != null){
                    list.add(u);
                }
            }
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id &&
                Objects.equals(employee, userInfo.employee) &&
                Objects.equals(loginCode, userInfo.loginCode) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(lastLoginTime, userInfo.lastLoginTime) &&
                Objects.equals(lastLoginIp, userInfo.lastLoginIp) &&
                Objects.equals(status, userInfo.status) &&
                Objects.equals(createTime, userInfo.createTime) &&
                Objects.equals(companyCode, userInfo.companyCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id,employee, loginCode, password, lastLoginTime, lastLoginIp, status, createTime, companyCode);
    }
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", employee=" + employee +
                ", loginCode='" + loginCode + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", companyCode='" + companyCode + '\'' +
                '}';
    }
}
