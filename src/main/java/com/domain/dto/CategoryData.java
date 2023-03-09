package com.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryData {

    private Long id;
    
    @NotEmpty(message = "Name is required")
    private String name;

}