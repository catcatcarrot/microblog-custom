package com.example.reactordemo.entity;

import lombok.Data;

@Data
public class Review {

    private final String id;
    private final double grade;

    public Review(String id, double grade) {
        this.id = id;
        this.grade = grade;
    }

}
