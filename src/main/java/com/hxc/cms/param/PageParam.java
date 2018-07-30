package com.hxc.cms.param;

import java.io.Serializable;

public class PageParam implements Serializable {

    private Integer page;

    private Integer rows;

    private Integer skip;

    private Integer limit;

    private String type;

    private String companyCode;

    private String status;

    public PageParam() {
    }

    public PageParam(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
        this.skip = (page - 1)*rows;
        this.limit = page*rows;
    }

    public PageParam(Integer skip, Integer limit, String type) {
        this.skip = skip;
        this.limit = limit;
        this.type = type;

    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
