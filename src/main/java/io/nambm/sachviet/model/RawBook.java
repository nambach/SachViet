package io.nambm.sachviet.model;

import io.nambm.sachviet.repository.generic.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "`RawBook`")
public class RawBook implements GenericEntity {

    @Id
    @Column(name = "`id`")
    private String id;
    @Column(name = "`siteName`")
    private String siteName;

    //Basic information
    @Column(name = "`title`")
    private String title;
    @Column(name = "`authors`")
    private String authors;

    //Image
    @Column(name = "`image`")
    private String image;

    //Link to original page
    @Column(name = "`link`")
    private String link;

    //Prices
    @Column(name = "`price`")
    private String price;
    @Column(name = "`oldPrice`")
    private String oldPrice;

    //Status
    @Column(name = "`status`")
    private String status;

    public RawBook() {
    }

    public RawBook(String id, String siteName, String title, String authors, String image, String link, String price, String oldPrice, String status) {
        this.id = id;
        this.siteName = siteName;
        this.title = title;
        this.authors = authors;
        this.image = image;
        this.link = link;
        this.price = price;
        this.oldPrice = oldPrice;
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

    @Override
    public String toString() {
        return "RawBook{" +
                "id='" + id + '\'' +
                ", siteName='" + siteName + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
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
        String image = obj.getOrDefault("image", "");
        String link = obj.getOrDefault("link", "");
        String price = obj.getOrDefault("price", "");
        String oldPrice = obj.getOrDefault("oldPrice", "");
        String status = obj.getOrDefault("status", "");

        return new RawBook(id, siteName, title, authors, image, link, price, oldPrice, status);
    }

    public static List<RawBook> convert(List<Map<String, String>> list) {
        List<RawBook> rawBooks = new LinkedList<>();
        for (Map<String, String> item : list) {
            rawBooks.add(convert(item));
        }
        return rawBooks;
    }
}
