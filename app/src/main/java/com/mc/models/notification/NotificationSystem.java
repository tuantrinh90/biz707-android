package com.mc.models.notification;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationSystem {
    @JsonProperty("mediaUri")
    private String mediaUri;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("isDraft")
    private Boolean isDraft;
    @JsonProperty("content")
    private String content;
    @JsonProperty("sendTo")
    private SendTo sendTo;
    @JsonProperty("sendTotal")
    private Integer sendTotal;
    @JsonProperty("partitionKey")
    private String partitionKey;
    @JsonProperty("platform")
    private String platform;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("deleted_at")
    private String deletedAt;


    @JsonProperty("mediaUri")
    public String getMediaUri() {
        return mediaUri;
    }

    @JsonProperty("mediaUri")
    public void setMediaUri(String mediaUri) {
        this.mediaUri = mediaUri;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("isDraft")
    public Boolean getIsDraft() {
        return isDraft;
    }

    @JsonProperty("isDraft")
    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("sendTo")
    public SendTo getSendTo() {
        return sendTo;
    }

    @JsonProperty("sendTo")
    public void setSendTo(SendTo sendTo) {
        this.sendTo = sendTo;
    }

    @JsonProperty("sendTotal")
    public Integer getSendTotal() {
        return sendTotal;
    }

    @JsonProperty("sendTotal")
    public void setSendTotal(Integer sendTotal) {
        this.sendTotal = sendTotal;
    }

    @JsonProperty("partitionKey")
    public String getPartitionKey() {
        return partitionKey;
    }

    @JsonProperty("partitionKey")
    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("updatedBy")
    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonProperty("updatedBy")
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("deleted_at")
    public String getDeletedAt() {
        return deletedAt;
    }

    @JsonProperty("deleted_at")
    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "NotificationSystem{" +
                "mediaUri='" + mediaUri + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", isDraft=" + isDraft +
                ", content='" + content + '\'' +
                ", sendTo=" + sendTo +
                ", sendTotal=" + sendTotal +
                ", partitionKey='" + partitionKey + '\'' +
                ", platform='" + platform + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", deletedAt='" + deletedAt + '\'' +
                '}';
    }
}
