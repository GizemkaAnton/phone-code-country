package com.organization.phonecodecountry.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDto {

    @NotEmpty(message = "Phone number required")
    @Size(min = 10, message = "Number is too short")
    private String number;
}
