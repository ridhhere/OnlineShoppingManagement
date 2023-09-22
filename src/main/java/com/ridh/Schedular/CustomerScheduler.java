package com.ridh.Schedular;

import com.ridh.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class CustomerScheduler {
    @Autowired
    CustomerService customerService;

    @Scheduled(fixedRate = 1000000)
    public void updateCustomerStatus(){
        log.info("CustomerScheduler:updateCustomerStatus Scheduler Started Successfully!! at " + LocalDateTime.now());
        customerService.updateStatusIfInactive();
        log.info("CustomerScheduler:updateCustomerStatus Scheduler Ended Successfully!! at " +  LocalDateTime.now());
    }
}
