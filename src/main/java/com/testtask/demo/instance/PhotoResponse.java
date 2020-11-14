package com.testtask.demo.instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoResponse {
    private List<Photo> pictures;
    private Integer page;
    private Integer pageCount;
    private Boolean hasMore;

    public List<Photo> getPictures() {
        if(pictures == null){
            pictures = new ArrayList<>();
        }
        return pictures;
    }

    public void setPictures(List<Photo> pictures) {
        this.pictures = pictures;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoResponse)) return false;
        PhotoResponse that = (PhotoResponse) o;
        return getPictures().equals(that.getPictures());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPictures());
    }

    @Override
    public String toString() {
        return "PhotoResponse{" +
                "pictures=" + pictures +
                ", page=" + page +
                ", pageCount=" + pageCount +
                ", hasMore=" + hasMore +
                '}';
    }
}
