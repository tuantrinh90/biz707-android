package com.mc.models.notification;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NotificationSystemResponse {
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("rows")
    private List<NotificationSystem> listNotificationSys = null;


    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("rows")
    public List<NotificationSystem> getlistNotificationSys() {
        return listNotificationSys;
    }

    @JsonProperty("rows")
    public void setlistNotificationSys(List<NotificationSystem> listNotificationSys) {
        this.listNotificationSys = listNotificationSys;
    }

    @Override
    public String toString() {
        return "NotificationSystemResponse{" +
                "count=" + count +
                ", listNotificationSys=" + listNotificationSys +
                '}';
    }
}
