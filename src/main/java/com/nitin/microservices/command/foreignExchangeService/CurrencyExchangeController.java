package com.nitin.microservices.command.foreignExchangeService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by nitin on Sunday, November/17/2019 at 7:57 PM
 */

@RestController
@Slf4j
public class CurrencyExchangeController {

    @Autowired private Environment environment;
    @Autowired private ExchangeRepository exchangeRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/foreign-exchange")
    public ResponseEntity<CurrencyExchangeEntity> retrieveExchangeValue(@RequestBody Map<String, Object> body){

        String from = (String) body.get("from");
        String to = (String) body.get("to");

        //Skipping Service Layer for simplicity
        //Calling from H2 Database
        CurrencyExchangeEntity fromAndTo = exchangeRepository.findByFromAndTo(from,to);
        fromAndTo.setPort(Integer.parseInt(environment.getProperty("server.port")));

        //Skipping the Entity to Bean/Dto Conversion and returning Entity directly
        logger.info("{}",fromAndTo);
        log.info("Entity Returned is {}",fromAndTo);
        return ResponseEntity.ok(fromAndTo);
    }

    @GetMapping("/foreign-exchange")
    public String showGeneralMessage(){

        String msg = """
                Use POST : http://localhost:8000/foreign-exchange
                With Request Body as
                {
                    "from":"AUD",
                    "to":"INR"
                }
                """;

        return msg;
    }

    @GetMapping("/")
    public String welcomeMessage(){
        return "welcome to command service";
    }
}