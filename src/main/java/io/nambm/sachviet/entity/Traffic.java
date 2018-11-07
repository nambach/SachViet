package io.nambm.sachviet.entity;

import io.nambm.sachviet.repository.generic.GenericEntity;

import javax.persistence.*;

@Entity
@Table(name = "`Traffic`")
public class Traffic implements GenericEntity {

    public static final String RS_COMPARE_GROUP = "compareGroup";
    public static final String RS_SEARCH_CONTENT = "searchContent";

    @Id
    private String id;
    private String ip;
    private Long time;
    private String resource;
    private String resourceType;

    public Traffic(String ip, Long time, String resource, String resourceType) {
        this.ip = ip;
        this.time = time;
        this.resource = resource;
        this.resourceType = resourceType;
        this.id = getEntityId();
    }

    public Traffic() {
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String getEntityId() {
        return time + "_" + ip;
    }
}
