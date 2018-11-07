package io.nambm.sachviet.entity;

import io.nambm.sachviet.repository.generic.GenericEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`CateRelation`")
public class CateRelation implements GenericEntity {

    @Id
    private String id;
    private String cateId;
    private String compareGroupId;

    public CateRelation(String cateId, String compareGroupId) {
        this.cateId = cateId;
        this.compareGroupId = compareGroupId;

        this.id = this.getEntityId();
    }

    public CateRelation() {
    }

    public String getId() {
        return id;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCompareGroupId() {
        return compareGroupId;
    }

    public void setCompareGroupId(String compareGroupId) {
        this.compareGroupId = compareGroupId;
    }

    @Override
    public String getEntityId() {
        return cateId + "_" + compareGroupId;
    }
}
