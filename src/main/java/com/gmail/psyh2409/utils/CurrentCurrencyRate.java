package com.gmail.psyh2409.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class CurrentCurrencyRate {

    public static Double[] getEURandUSDrangeNow() {
        String surl = "https://bank.gov.ua/";
        Double[] eurUsdDoubleArr = new Double[2];
        try {
            Element element = Jsoup.parse(
                    new URL(surl), 10000)
                    .selectFirst("div[class=collection macro-indicators]");
            for (int i = 2; i < 4; i++) {
                eurUsdDoubleArr[i-2] = Double.valueOf(element
                        .getElementsByIndexEquals(i)
                        .select("div[class=value-full]")
                        .first()
                        .selectFirst("small")
                        .text()
                        .replaceAll(",", "."));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return eurUsdDoubleArr;
    }
}
