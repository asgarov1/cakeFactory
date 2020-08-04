package com.asgarov.liveproject.cakefactory.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;
    private String city;
    private String zip;
}
