package io.nambm.sachviet.entity;

import io.nambm.sachviet.model.book.Book;
import io.nambm.sachviet.repository.generic.GenericEntity;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "`RawBook`")
public class RawBook implements GenericEntity {

    @Id
    private String id;
    private String siteName;

    //Basic information
    private String title;
    private String authors;
    private String keyword;

    //Image
    private String image;

    //Link to original page
    private String link;

    //Prices
    private String price;
    private String oldPrice;
    private String discountRate;

    //Status
    private String status;
    private String compareGroupId;
    private String suggestGroupId;

    public RawBook() {
    }

    public RawBook(String id, String siteName, String title, String authors, String keyword, String image, String link, String price, String oldPrice, String discountRate, String status) {
        this.id = id;
        this.siteName = siteName;
        this.title = title;
        this.authors = authors;
        this.keyword = keyword;
        this.image = image;
        this.link = link;
        this.price = price;
        this.oldPrice = oldPrice;
        this.discountRate = discountRate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCompareGroupId() {
        return compareGroupId;
    }

    public void setCompareGroupId(String compareGroupId) {
        this.compareGroupId = compareGroupId;
    }

    public String getSuggestGroupId() {
        return suggestGroupId;
    }

    public void setSuggestGroupId(String suggestGroupId) {
        this.suggestGroupId = suggestGroupId;
    }

    @Override
    public String toString() {
        return "RawBook{" +
                "id='" + id + '\'' +
                ", siteName='" + siteName + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", keyword='" + keyword + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", price='" + price + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public String getEntityId() {
        return getId();
    }

    public static RawBook convert(Map<String, String> obj) {
        String id = obj.getOrDefault("id", "");
        String siteName = obj.getOrDefault("siteName", "");
        String title = obj.getOrDefault("title", "");
        String authors = obj.getOrDefault("authors", "");
        String keyword = Normalizer
                .normalize(title, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
        keyword = StringUtils.replace(keyword, "đ", "d");
        String image = obj.getOrDefault("image", "");
        String link = obj.getOrDefault("link", "");
        String price = obj.getOrDefault("price", "");
        String oldPrice = obj.getOrDefault("oldPrice", "");
        String discountRate = obj.getOrDefault("discountRate", "");
        String status = obj.getOrDefault("status", "");

        return new RawBook(id, siteName, title, authors, keyword, image, link, price, oldPrice, discountRate, status);
    }

    public static List<RawBook> convert(List<Map<String, String>> list) {
        List<RawBook> rawBooks = new LinkedList<>();
        for (Map<String, String> item : list) {
            rawBooks.add(convert(item));
        }
        return rawBooks;
    }

    public Book toBook() {
        return new Book(id, siteName, title, authors, keyword, image, link, price, oldPrice, discountRate, status);
    }
}
