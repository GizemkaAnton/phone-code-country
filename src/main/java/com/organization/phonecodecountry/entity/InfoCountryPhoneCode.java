package com.organization.phonecodecountry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_country_phone_code")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoCountryPhoneCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "phone_code")
    private String phoneCode;

    public InfoCountryPhoneCode(String countryName, String phoneCode) {
        this.countryName = countryName;
        this.phoneCode = phoneCode;
    }
}
