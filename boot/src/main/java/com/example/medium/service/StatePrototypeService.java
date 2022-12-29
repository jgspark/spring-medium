package com.example.medium.service;

import com.example.medium.service.dto.StateRequest;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StatePrototypeService implements StateService {
    private Boolean state = false;

    @Override
    public StateRequest getState(Boolean isChangeAble) {
        if (isCheckedAble(isChangeAble)) this.state = true;
        return new StateRequest(state, this.hashCode());
    }
}
