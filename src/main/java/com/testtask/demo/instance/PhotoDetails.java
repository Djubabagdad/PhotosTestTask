package com.testtask.demo.instance;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhotoDetails {
    private String id;
    private String author;
    private String camera;
    private String tags;
    private String croppedPicture;
    private String fullPicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCroppedPicture() {
        return croppedPicture;
    }

    public void setCroppedPicture(String croppedPicture) {
        this.croppedPicture = croppedPicture;
    }

    public String getFullPicture() {
        return fullPicture;
    }

    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoDetails)) return false;
        PhotoDetails details = (PhotoDetails) o;
        return Objects.equals(id, details.id) &&
                Objects.equals(getCroppedPicture(), details.getCroppedPicture()) &&
                Objects.equals(getFullPicture(), details.getFullPicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getCroppedPicture(), getFullPicture());
    }

    @Override
    public String toString() {
        return "PhotoDetails{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", camera='" + camera + '\'' +
                ", tags='" + tags + '\'' +
                ", croppedPicture='" + croppedPicture + '\'' +
                ", fullPicture='" + fullPicture + '\'' +
                '}';
    }
}
