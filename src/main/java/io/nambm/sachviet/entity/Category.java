package io.nambm.sachviet.entity;

import io.nambm.sachviet.repository.generic.GenericEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`Category`")
public class Category implements GenericEntity {

    @Id
    private String id;
    private String name;
    private String child;
    private String parent;

    public Category(String id, String name, String child, String parent) {
        this.id = id;
        this.name = name;
        this.child = child;
        this.parent = parent;
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String getEntityId() {
        return id;
    }
}
