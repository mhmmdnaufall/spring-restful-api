package com.domain.dto; // Data Transfer Object (DTO)

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> {
    
    private Boolean status;

    private List<String> messages = new ArrayList<>();

    private T payload;

}
