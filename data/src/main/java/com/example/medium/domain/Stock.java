package com.example.medium.domain;

import com.example.medium.exception.SoldOutException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 현재 팔린 수량
    private Long count;

    // 전체 수량
    private Long originCount;

    // 유저가 구매 하는 최소 수량
    private Long limitCount;

    @Version
    private Long version;

    @OneToOne
    private Product product;

    @Transient
    public void changeCount(Long count) {
        this.count = count;
    }
}
