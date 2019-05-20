package com.mc.models.home;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Role implements Serializable {
    @JsonProperty("role")
    private String role;
    @JsonProperty("hashtag")
    private String hashtag;
    @JsonProperty("id")
    private Integer id;

    public Role() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", id=" + id +
                '}';
    }
}
