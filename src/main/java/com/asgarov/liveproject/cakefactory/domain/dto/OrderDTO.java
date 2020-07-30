package com.asgarov.liveproject.cakefactory.domain.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;
    private String state;
    private String zip;
}
