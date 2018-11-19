package com.example.pzdf.jinjie.entity;

import java.io.Serializable;

/**
 * 功能描述:
 * Created by pzdf on 2018/11/16.
 */

public class Translation implements Serializable {

    private int status;

    private String from;
    private String to;
    private String vendor;
    private String out;
    private int errNo;

    public void show() {
        System.out.println(status);
        System.out.println(from);
        System.out.println(to);
        System.out.println(vendor);
        System.out.println(out);
        System.out.println(errNo);
    }

    @Override
    public String toString() {
        return "Translation{" +
                "status=" + status +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", vendor='" + vendor + '\'' +
                ", out='" + out + '\'' +
                ", errNo=" + errNo +
                '}';
    }
}
