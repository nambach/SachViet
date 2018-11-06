package io.nambm.sachviet.entity;

import io.nambm.sachviet.repository.generic.GenericEntity;
import io.nambm.sachviet.utils.JsonUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "`CompareGroup`")
public class CompareGroup implements GenericEntity {

    @Id
    @Column(name = "`groupId`")
    private String id;
    private String coreMember;
    private String members;

    // Core book information
    private String title;
    private String authors;
    private String image;
    private String link;
    private String minPrice;
    private String maxPrice;

    // For suggestion
    private String suggestGroupId;

    public CompareGroup() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCoreMember() {
        return coreMember;
    }
    public void setCoreMember(String coreMember) {
        this.coreMember = coreMember;
    }
    public String getMembers() {
        return members;
    }
    public void setMembers(String members) {
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
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
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


    public void addMember(String bookId) {
        if (this.members == null) {
            this.members = JsonUtils.EMPTY_LIST;
        }
        this.members = JsonUtils.addValueToArray(this.members, bookId);
    }

    public void removeMember(String bookId) {
        if (this.members == null) {
            this.members = JsonUtils.EMPTY_LIST;
        }
        this.members = JsonUtils.deleteValueFromArray(this.members, bookId);
    }

    public int countMembers() {
        return JsonUtils.getStringArray(this.members).size();
    }

    public List<String> getMemberList() {
        if (this.members == null) {
            this.members = JsonUtils.EMPTY_LIST;
        }
        return JsonUtils.getStringArray(this.members);
    }

    public boolean checkMemberSource(String bookId) {
        String bookSource = bookId.substring(0, bookId.indexOf('_'));
        List<String> memberSources = getMemberList()
                .stream()
                .map(s -> s.substring(0, s.indexOf('_')))
                .collect(Collectors.toList());

        return memberSources.contains(bookSource);
    }

    public void importCoreMember(RawBook coreMember) {
        this.title = coreMember.getTitle();
        this.authors = coreMember.getAuthors();
        this.image = coreMember.getImage();
        this.minPrice = coreMember.getPrice();
        this.maxPrice = coreMember.getPrice();

        //Remove authors name from title
        this.title = this.title.replace(this.authors, "");
    }

    private void updatePrice(RawBook book) {
        try {
            int minPrice = Integer.parseInt(this.minPrice.replaceAll("[\\s,.đd]+", ""));
            int maxPrice = Integer.parseInt(this.minPrice.replaceAll("[\\s,.đd]+", ""));

            int bookPrice = Integer.parseInt(book.getPrice().replaceAll("[\\s,.đd]+", ""));

            if (minPrice > bookPrice) {
                this.minPrice = book.getPrice().replaceAll("[.]", ",");
            }
            if (maxPrice < bookPrice) {
                this.maxPrice = book.getPrice().replaceAll("[.]", ",");
            }
        } catch (Exception ignored) {
        }

    }

    public void updateBookInfo(RawBook book) {
        if (this.authors == null || authors.equals("")) {
            this.authors = book.getAuthors();

            //Remove authors name from title
            this.title = this.title.replace(this.authors, "");
        }
        if (this.image == null || authors.equals("")) {
            this.image = book.getImage();
        }
        updatePrice(book);
    }

    @Override
    public String toString() {
        return "CompareGroup{" +
                "id='" + id + '\'' +
                ", coreMember='" + coreMember + '\'' +
                ", members='" + members + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", suggestGroupId='" + suggestGroupId + '\'' +
                '}';
    }

    @Override
    public String getEntityId() {
        return id;
    }
}
