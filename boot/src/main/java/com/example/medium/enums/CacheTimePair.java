package com.example.medium.enums;

public enum CacheTimePair {

    MIN_1(60L), MIN_5(300L), MIN_10(600L), MIN_30(1800L), HOUR_1(3600L), HOURS_6(7200L), HOURS_12(43200L), DAY_1(86400L), DAYS_2(
            172800L), WEEK_1(604800L);

    private final Long ttl;

    CacheTimePair(Long ttl) {
        this.ttl = ttl;
    }

    public Long getTtl() {
        return ttl;
    }

}
