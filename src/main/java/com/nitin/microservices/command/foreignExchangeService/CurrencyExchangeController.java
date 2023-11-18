package com.nitin.microservices.command.foreignExchangeService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    //Get from mock
    @GetMapping("/foreign-exchange")
    public ResponseEntity<Map<String, Object>> showGeneralMessage(){

        String url = "https://run.mocky.io/v3/2789a53b-401f-4f4f-a625-d513f547dfbf";
        //delete : https://designer.mocky.io/manage/delete/2789a53b-401f-4f4f-a625-d513f547dfbf/nP7J9HbzF0K7USKVg4QDrVsT3l9HRCnKoN0I

        // Define the type reference for the response
        ParameterizedTypeReference<Map<String, Object>> responseType =
                new ParameterizedTypeReference<Map<String, Object>>() {};

        // Send a GET request and handle the response
        ResponseEntity<Map<String, Object>> responseEntity = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                null,
                responseType
        );


        // Check the HTTP status code
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // HTTP status code is in the 2xx range (e.g., 200 OK)

            // Access the response body as a Map
            Map<String, Object> responseBody = responseEntity.getBody();

            // You can now work with the responseBody Map
            System.out.println("Response Body: " + responseBody);
        } else {
            // Handle non-successful HTTP status codes (4xx or 5xx) here
            System.err.println("HTTP Request failed with status code: " + responseEntity.getStatusCode());
        }
        //Calling the Foreign Exchange Microservice from port 8000


        return responseEntity;

    }

    @GetMapping("/")
    public String welcomeMessage(){
        String msg = """
                Use POST : http://localhost:8000/foreign-exchange
                With Request Body as
                {
                    "from":"AUD",
                    "to":"INR"
                }
                """;

        return msg;    }
}