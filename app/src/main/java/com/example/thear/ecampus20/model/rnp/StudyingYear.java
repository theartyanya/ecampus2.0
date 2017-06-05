package com.example.thear.ecampus20.model.rnp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class StudyingYear {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
