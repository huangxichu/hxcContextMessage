package com.hxc.cms.dto;

import java.util.ArrayList;
import java.util.List;

public class PageResult<T> {

    private int totalPages = 0;

    private long totalElements = 0;

    List<T> content = new ArrayList<>();


    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
