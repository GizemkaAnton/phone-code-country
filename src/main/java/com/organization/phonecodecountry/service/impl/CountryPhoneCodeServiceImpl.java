package com.organization.phonecodecountry.service.impl;

import com.organization.phonecodecountry.entity.InfoCountryPhoneCode;
import com.organization.phonecodecountry.entity.dto.PhoneNumberDto;
import com.organization.phonecodecountry.repository.InfoCountryPhoneCodeRepository;
import com.organization.phonecodecountry.service.CountryPhoneCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryPhoneCodeServiceImpl implements CountryPhoneCodeService {
    private static final int MAX_NUMBER_DIGITS_IN_CODE = 8;
    private static final int MIN_DIGITS_IN_PHONE_NUMBER = 10;
    private static final int MIN_NUMBER_DIGITS_IN_CODE = 1;
    private static final int NUMBER_FIRST_CHARACTER_STRING = 0;

    @Value("${message.country_not_exist}")
    private String MESSAGE_IF_COUNTRY_NOT_EXIST;

    private final InfoCountryPhoneCodeRepository infoCountryPhoneCodeRepository;

    @Override
    public String getCountryNameByPhoneNumber(PhoneNumberDto phoneNumber) {
        List<InfoCountryPhoneCode> resultInfoCountryPhoneCodeList = new ArrayList<>();
        int count = MAX_NUMBER_DIGITS_IN_CODE;

        while (resultInfoCountryPhoneCodeList.isEmpty() &&
                count >= MIN_NUMBER_DIGITS_IN_CODE && phoneNumber.getNumber().length() >= MIN_DIGITS_IN_PHONE_NUMBER) {

            resultInfoCountryPhoneCodeList =
                    infoCountryPhoneCodeRepository.findInfoCountryPhoneCodeByPhoneCode(
                            phoneNumber.getNumber().substring(NUMBER_FIRST_CHARACTER_STRING, count));
            count--;
        }

        if (!resultInfoCountryPhoneCodeList.isEmpty()) {
            return resultInfoCountryPhoneCodeList.stream()
                    .map(InfoCountryPhoneCode::getCountryName)
                    .collect(Collectors.joining(", "));
        } else {
            return MESSAGE_IF_COUNTRY_NOT_EXIST;
        }
    }
}
