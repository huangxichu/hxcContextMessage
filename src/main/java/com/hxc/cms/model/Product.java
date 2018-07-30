package com.hxc.cms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "cms", catalog = "")
public class Product implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PRODUCT_CATEGORY_ID")
    private Integer productCategoryId;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_INFO",columnDefinition = "longtext")
    private String productInfo;
    @Column(name = "IS_HOT")
    private String isHot;
    @Column(name = "PIC")
    private String pic;
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

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(productCategoryId, product.productCategoryId) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productInfo, product.productInfo) &&
                Objects.equals(isHot, product.isHot) &&
                Objects.equals(pic, product.pic) &&
                Objects.equals(status, product.status) &&
                Objects.equals(createTime, product.createTime) &&
                Objects.equals(updateTime, product.updateTime) &&
                Objects.equals(companyCode, product.companyCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, productCategoryId, productName, productInfo, isHot, pic, status, createTime, updateTime, companyCode);
    }
}
