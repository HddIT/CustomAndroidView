package com.example.pzdf.jinjie.entity;

import java.io.Serializable;

/**
 * 功能描述:
 * Created by pzdf on 2018/11/15.
 */

public class MeetingEntity implements Serializable {

    private String start_time;
    private String end_time;
    private boolean isApproved;

    public MeetingEntity() {
        super();
    }

    public MeetingEntity(String start_time, String end_time, boolean isApproved) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.isApproved = isApproved;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
