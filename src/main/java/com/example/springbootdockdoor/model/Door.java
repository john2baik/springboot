package com.example.springbootdockdoor.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Door {
    private String id;

    private String name;

    public Door( String id, String name){
        this.id = id;
        this.name = name;
    }


    public Door(){
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

    @Override
    public String toString() {
        return "Door{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
