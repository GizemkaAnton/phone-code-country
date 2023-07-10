package com.organization.phonecodecountry.repository;

import com.organization.phonecodecountry.entity.InfoCountryPhoneCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoCountryPhoneCodeRepository extends JpaRepository<InfoCountryPhoneCode, Integer> {

    List<InfoCountryPhoneCode> findInfoCountryPhoneCodeByPhoneCode(String phoneCode);
}
