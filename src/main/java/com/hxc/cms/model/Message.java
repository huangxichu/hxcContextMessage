package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "message", schema = "cms", catalog = "")
public class Message implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "REL_NAME")
    private String relName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "MESSAGE_CONTEXT")
    private String messageContext;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "COMPANY_CODE")
    private String companyCode;
    @Column(name = "STATUS")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(relName, message.relName) &&
                Objects.equals(phone, message.phone) &&
                Objects.equals(messageContext, message.messageContext) &&
                Objects.equals(createTime, message.createTime) &&
                Objects.equals(status, message.status) &&
                Objects.equals(companyCode, message.companyCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, relName, phone, messageContext, createTime,status, companyCode);
    }
}
