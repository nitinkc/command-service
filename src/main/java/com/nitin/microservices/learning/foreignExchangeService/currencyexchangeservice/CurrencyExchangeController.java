package com.nitin.microservices.learning.foreignExchangeService.currencyexchangeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by nitin on Sunday, November/17/2019 at 7:57 PM
 */

@RestController
public class CurrencyExchangeController {

    @GetMapping("/foreign-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        return new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65) );

    }
}
