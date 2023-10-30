package com.nitin.microservices.command.foreignExchangeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nitin on Sunday, November/17/2019 at 7:57 PM
 */

@RestController
public class CurrencyExchangeController {

    @Autowired private Environment environment;
    @Autowired private ExchangeValueRepository exchangeValueRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/foreign-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        //Calling from H2 Database
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("server.port")));

        logger.info("{}",exchangeValue);
        return exchangeValue;
    }


    @GetMapping("/foreign-exchange")
    public String showGeneralMessage(){

        String msg = "Use the system to retrive the exchange like \n"+
                "http://localhost:8000/foreign-exchange/from/USD/to/INR"+"\nOR\n"+
                "http://localhost:8000/foreign-exchange/from/EUR/to/INR"+"\nOR\n"+
                "http://localhost:8000/foreign-exchange/from/AUD/to/INR"+"\n";

        return msg;
    }

    @GetMapping("/")
    public String welcomeMessage(){
        return "welcome to command service";
    }
}