package com.organization.phonecodecountry.controller;

import com.organization.phonecodecountry.entity.dto.PhoneNumberDto;
import com.organization.phonecodecountry.service.CountryPhoneCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class InfoCountryPhoneCodeController {

    private final CountryPhoneCodeService countryPhoneCodeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        PhoneNumberDto phoneNumber = new PhoneNumberDto();
        model.addAttribute("phone_number", phoneNumber);
        return "index";
    }

    @PostMapping("/get-country")
    public String getCountry(@ModelAttribute("phone_number") @Valid PhoneNumberDto phoneNumber,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        model.addAttribute("result", countryPhoneCodeService.getCountryNameByPhoneNumber(phoneNumber));
        return "result_page";
    }
}
