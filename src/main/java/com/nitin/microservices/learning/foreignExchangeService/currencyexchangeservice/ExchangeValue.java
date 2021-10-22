package com.nitin.microservices.learning.foreignExchangeService.currencyexchangeservice;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by nitin on Sunday, November/17/2019 at 8:00 PM
 */
@Data
@Entity
public class ExchangeValue {

    @Id
    private Long id;

    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiple;
    private int port;
}