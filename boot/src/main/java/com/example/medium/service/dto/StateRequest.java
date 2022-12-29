package com.example.medium.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StateRequest {

    private final Boolean state;

    private final int hashCode;
}
