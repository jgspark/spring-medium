package com.example.medium.service;

import com.example.medium.service.dto.StateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StateService {

    private Boolean state = false;

    public StateRequest getState(Boolean isChangeAble) {
        if (isCheckedAble(isChangeAble)) this.state = true;
        return new StateRequest(state, this.hashCode());
    }

    public boolean isCheckedAble(Boolean isCheckedAble) {
        if (isCheckedAble == null) return false;
        return isCheckedAble;
    }
}
