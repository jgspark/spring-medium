package com.example.medium.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Stock implements Serializable {

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

    @JsonIgnore
    @OneToOne(mappedBy = "stock", fetch = FetchType.LAZY)
    private Product product;

    @Transient
    public void changeCount(Long count) {
        this.count = count;
    }

    @Transient
    public boolean isSoldOutAble() {
        return Objects.equals(count, originCount);
    }
}
