package com.example.medium.service.vo.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Item1Test {


    @Test
    @DisplayName("item1 객체 init 테스트 케이스")
    public void initData() {

        final String name = "이름 입니다.";

        final String content = "컨텐츠 입니다.";

        Item1 i = new Item1();

        i.setName(name);
        i.setContent(content);

        assertEquals(i.getName(), name);
        assertEquals(i.getContent(), content);
    }
}
