package com.organization.phonecodecountry.service;

import com.organization.phonecodecountry.entity.dto.PhoneNumberDto;

public interface CountryPhoneCodeService {
    
    String getCountryNameByPhoneNumber(PhoneNumberDto phoneNumber);
}
