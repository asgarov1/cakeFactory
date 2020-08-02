package com.asgarov.liveproject.cakefactory.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;
    private String city;
    private String zip;
}
