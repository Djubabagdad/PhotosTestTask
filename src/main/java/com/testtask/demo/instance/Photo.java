package com.testtask.demo.instance;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Photo {
    private String id;
    private String croppedPicture;
    private PhotoDetails details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCroppedPicture() {
        return croppedPicture;
    }

    public void setCroppedPicture(String croppedPicture) {
        this.croppedPicture = croppedPicture;
    }

    public PhotoDetails getDetails() {
        return details;
    }

    public void setDetails(PhotoDetails details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo = (Photo) o;
        return Objects.equals(getId(), photo.getId()) &&
                Objects.equals(getCroppedPicture(), photo.getCroppedPicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCroppedPicture());
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", croppedPicture='" + croppedPicture + '\'' +
                ", details=" + details +
                '}';
    }
}
