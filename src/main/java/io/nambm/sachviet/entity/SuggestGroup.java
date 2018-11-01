package io.nambm.sachviet.entity;

import io.nambm.sachviet.repository.generic.GenericEntity;
import io.nambm.sachviet.utils.JsonUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "`SuggestGroup`")
public class SuggestGroup implements GenericEntity {

    @Id
    @Column(name = "`groupId`")
    private String id;
    @Column(name = "`coreMember`")
    private String coreMember;
    @Column(name = "`members`")
    private String members;

    // For comparing
    @Column(name = "`title`")
    private String title;

    @Override
    public String getEntityId() {
        return id;
    }

    public SuggestGroup() {
    }

    public SuggestGroup(String id, String coreMember, String members, String title) {
        this.id = id;
        this.coreMember = coreMember;
        this.members = members;
        this.title = title;
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


    @Override
    public String toString() {
        return "SuggestGroup{" +
                "id='" + id + '\'' +
                ", coreMember='" + coreMember + '\'' +
                ", members='" + members + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
