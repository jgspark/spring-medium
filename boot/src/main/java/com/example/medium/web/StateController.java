package com.example.medium.web;

import com.example.medium.service.StateService;
import com.example.medium.service.dto.StateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("state")
public class StateController {

    private final StateService stateSingletonService;

    private final StateService statePrototypeService;

    /*
     curl --location --request GET 'localhost:8080/state/t1'
     */
    @GetMapping("t1")
    public StateRequest getState(Boolean isChangeAble) {
        return stateSingletonService.getState(isChangeAble);
    }

    /*
     curl --location --request GET 'localhost:8080/state/t2'
     */
    @GetMapping("t2")
    public StateRequest getState2(Boolean isChangeAble) {
        return statePrototypeService.getState(isChangeAble);
    }
}
