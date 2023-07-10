package com.organization.phonecodecountry.service.impl;

import com.organization.phonecodecountry.entity.InfoCountryPhoneCode;
import com.organization.phonecodecountry.repository.InfoCountryPhoneCodeRepository;
import com.organization.phonecodecountry.service.InfoCountryPhoneCodeProvider;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoCountryPhoneCodeProviderImpl implements InfoCountryPhoneCodeProvider {

    private static final int NUMBER_TABLE_IN_ARTICLE = 1;
    private static final int NUMBER_FIRST_ROW = 0;
    private static final int NUMBER_FIRST_COLUMN = 0;
    private static final int NUMBER_SECOND_COLUMN = 1;

    @Value("${url.wiki_table}")
    private String URL_WIKI_TABLE;

    private final InfoCountryPhoneCodeRepository infoCountryPhoneCodeRepository;

    @Override
    public List<InfoCountryPhoneCode> getInfoCountryPhoneCodeList() {
        List<InfoCountryPhoneCode> infoCountryPhoneCodeList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL_WIKI_TABLE).get();
            Element table = document.select("table.wikitable").get(NUMBER_TABLE_IN_ARTICLE);
            Elements body = table.select("tbody");
            Elements rows = body.select("tr");
            rows.remove(NUMBER_FIRST_ROW);

            rows.forEach(row -> {
                Elements columns = row.select("td");
                String country = columns.get(NUMBER_FIRST_COLUMN).text();
                String codePhone = columns.get(NUMBER_SECOND_COLUMN).text();
                getCodeList(codePhone)
                        .forEach(code -> infoCountryPhoneCodeList.add(new InfoCountryPhoneCode(country, code)));
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return infoCountryPhoneCodeList;
    }

    private List<String> getCodeList(String code) {
        List<String> resultCodeList = new ArrayList<>();
        if (code.contains("(")) {
            List<String> codeList = Arrays.stream(
                    code.replace("(", "")
                            .replace(")", "")
                            .replace(",", "")
                            .split(" ")
            ).toList();

            for (int i = 0; i < codeList.size() - 1; i++) {
                resultCodeList.add(codeList.get(0).concat(codeList.get(i + 1)));
            }
        } else {
            resultCodeList.add(code);
        }

        return resultCodeList;
    }

    @PostConstruct
    void saveInfoCountryPhoneCode() {
        infoCountryPhoneCodeRepository.saveAll(getInfoCountryPhoneCodeList());
    }

    @PreDestroy
    void cleanInfoCountryPhoneCodeTable() {
        infoCountryPhoneCodeRepository.deleteAll();
    }
}
