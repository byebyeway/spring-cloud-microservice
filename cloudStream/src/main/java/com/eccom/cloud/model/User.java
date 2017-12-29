package com.eccom.cloud.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    int id;

    @Max(20)
    String name;
    String mobileNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
