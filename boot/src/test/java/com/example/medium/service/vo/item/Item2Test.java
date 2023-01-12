package com.example.medium.service.vo.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Item2Test {

    @Test
    @DisplayName("item2 객체 init 테스트 케이스")
    public void initData() {

        final String name = "이름 입니다.";

        final String content = "컨텐츠 입니다.";

        Item2 i = new Item2();

        i.changedData(name, content);

        assertEquals(i.getName(), name);
        assertEquals(i.getContent(), content);
    }
}
