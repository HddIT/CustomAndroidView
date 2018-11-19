package com.example.pzdf.jinjie.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 功能描述:
 * Created by pzdf on 2018/11/13.
 */

public class GoodsInfoEntity implements Serializable {

    private int id;
    private String head_iamge;
    private String title;
    private String content;
    private int newPrice;
    private int oldPrice;
    private String address;
    private String address_image;
    private String desc;
    private ArrayList<MeetingEntity> meetingEntities;


    public String getHead_iamge() {
        return head_iamge;
    }

    public void setHead_iamge(String head_iamge) {
        this.head_iamge = head_iamge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_image() {
        return address_image;
    }

    public void setAddress_image(String address_image) {
        this.address_image = address_image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public GoodsInfoEntity() {
        super();
    }

    public ArrayList<MeetingEntity> getMeetingEntities() {
        return meetingEntities;
    }

    public void setMeetingEntities(ArrayList<MeetingEntity> meetingEntities) {
        this.meetingEntities = meetingEntities;
    }

    @Override
    public String toString() {
        return "GoodsInfoEntity{" +
                "id=" + id +
                ", head_iamge='" + head_iamge + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", newPrice=" + newPrice +
                ", oldPrice=" + oldPrice +
                ", address='" + address + '\'' +
                ", address_image='" + address_image + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public GoodsInfoEntity(String head_iamge, String title, String content, int newPrice, int oldPrice, String address, String address_image, String desc) {
        this.head_iamge = head_iamge;
        this.title = title;
        this.content = content;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.address = address;
        this.address_image = address_image;
        this.desc = desc;
    }

    public GoodsInfoEntity(String head_iamge, String title, String content, int newPrice, int oldPrice, String address, String address_image, String desc, ArrayList<MeetingEntity> meetingEntities) {
        this.head_iamge = head_iamge;
        this.title = title;
        this.content = content;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.address = address;
        this.address_image = address_image;
        this.desc = desc;
        this.meetingEntities = meetingEntities;
    }
}
