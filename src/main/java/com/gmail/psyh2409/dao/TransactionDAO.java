package com.gmail.psyh2409.dao;

import com.gmail.psyh2409.entities.Rate;
import com.gmail.psyh2409.utils.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionDAO extends AbstractDAO {

    public BigDecimal exchange(Currency curTo, Currency curFrom, BigDecimal money, Rate rate){
        BigDecimal bd = new BigDecimal(0);
        double usdInEUR = rate.getEur()/ rate.getUsd();
        switch (curFrom){
            case EUR:
                switch (curTo){
                    case UAH: bd = money.multiply(new BigDecimal(rate.getEur()));
                        break;
                    case USD: bd = money.multiply(new BigDecimal(usdInEUR));
                        break;
                }
                break;
            case USD:
                switch (curTo){
                    case UAH: bd = money.multiply(new BigDecimal(rate.getUsd()));
                        break;
                    case EUR: bd = money.divide(new BigDecimal(usdInEUR), 2,  RoundingMode.HALF_UP);
                        break;
                }
                break;
            case UAH:
                switch (curTo){
                    case EUR: bd = money.divide(new BigDecimal(rate.getEur()), 2,  RoundingMode.HALF_UP);
                        break;
                    case USD: bd = money.divide(new BigDecimal(rate.getUsd()), 2,  RoundingMode.HALF_UP);
                        break;
                }
                break;
        }
        return bd;
    }
}
