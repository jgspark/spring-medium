package com.example.medium.service.vo.item;

import lombok.Getter;

@Getter
public class Item2 {

    private String name;

    private String content;

    public void changedData(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
