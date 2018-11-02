package io.nambm.sachviet.model.book;

import io.nambm.sachviet.entity.RawBook;

import java.util.List;

public class CompareModel {

    private List<RawBook> members;

    // Core book information
    private String title;
    private String authors;
    private String image;
    private String minPrice;
    private String maxPrice;

    // For suggestion
    private String suggestGroupId;

    public CompareModel(String title, String authors, String image, String minPrice, String maxPrice, String suggestGroupId) {
        this.title = title;
        this.authors = authors;
        this.image = image;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.suggestGroupId = suggestGroupId;
    }

    public CompareModel() {
    }

    public List<RawBook> getMembers() {
        return members;
    }

    public void setMembers(List<RawBook> members) {
        this.members = members;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSuggestGroupId() {
        return suggestGroupId;
    }

    public void setSuggestGroupId(String suggestGroupId) {
        this.suggestGroupId = suggestGroupId;
    }
}
