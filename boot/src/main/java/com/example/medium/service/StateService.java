package com.example.medium.service;

import com.example.medium.service.dto.StateRequest;

public interface StateService {

    StateRequest getState(Boolean isChangeAble);

    default boolean isCheckedAble(Boolean isCheckedAble) {
        if (isCheckedAble == null) return false;
        return isCheckedAble;
    }
}
