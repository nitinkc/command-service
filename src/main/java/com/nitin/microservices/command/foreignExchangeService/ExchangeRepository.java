package com.nitin.microservices.command.foreignExchangeService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nitin on Sunday, November/17/2019 at 10:02 PM
 */
@Repository
public interface ExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {

    CurrencyExchangeEntity findByFromAndTo(String from, String to);
}
