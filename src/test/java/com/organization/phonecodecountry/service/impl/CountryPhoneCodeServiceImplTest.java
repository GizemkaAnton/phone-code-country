package com.organization.phonecodecountry.service.impl;

import com.organization.phonecodecountry.entity.dto.PhoneNumberDto;
import com.organization.phonecodecountry.service.CountryPhoneCodeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryPhoneCodeServiceImplTest {

    @Autowired
    private CountryPhoneCodeService countryPhoneCodeService;

    private static PhoneNumberDto phoneNumberBahamas;
    private static PhoneNumberDto phoneNumberUnitedStatesAndCanada;
    private static PhoneNumberDto phoneNumberRussia;
    private static PhoneNumberDto phoneNumberKazakhstan;
    private static PhoneNumberDto phoneNumberUnknown;
    private static PhoneNumberDto phoneNumberShort;
    private static PhoneNumberDto phoneNumberEmpty;

    @BeforeAll
    public static void setUp() {
        phoneNumberBahamas = new PhoneNumberDto("12423222931");
        phoneNumberUnitedStatesAndCanada = new PhoneNumberDto("11165384765");
        phoneNumberRussia = new PhoneNumberDto("72112227231");
        phoneNumberKazakhstan = new PhoneNumberDto("77112227231");
        phoneNumberUnknown = new PhoneNumberDto("4249174112227231");
        phoneNumberShort = new PhoneNumberDto("1356");
        phoneNumberEmpty = new PhoneNumberDto("");
    }

    @Test
    void getCountryNameByPhoneNumberWhenCodeExist() {
        Assertions.assertEquals("Bahamas", countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumberBahamas));
        Assertions.assertEquals("Canada, United States", countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumberUnitedStatesAndCanada));
        Assertions.assertEquals("Russia", countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumberRussia));
        Assertions.assertEquals("Kazakhstan", countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumberKazakhstan));
    }

    @Test
    void getCountryNameByPhoneNumberWhenCodeNotExist() {
        Assertions.assertEquals("There is no country with this phone number",
                countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumberUnknown));
    }

    @Test
    void getCountryNameByPhoneNumberWhenCodeShortOrEmpty() {
        Assertions.assertEquals("There is no country with this phone number",
                countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumberShort));
        Assertions.assertEquals("There is no country with this phone number",
                countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumberEmpty));
    }
}
