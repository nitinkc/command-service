package com.nitin.microservices.learning.foreignExchangeService.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nitinon Sunday, November/17/2019 at 10:02 PM
 */

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}
