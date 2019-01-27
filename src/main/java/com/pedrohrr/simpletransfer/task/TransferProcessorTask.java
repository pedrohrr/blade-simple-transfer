package com.pedrohrr.simpletransfer.task;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.task.annotation.Schedule;
import com.pedrohrr.simpletransfer.facade.TransferFacade;
import lombok.extern.slf4j.Slf4j;

@Bean
@Slf4j
public class TransferProcessorTask {

    @Inject
    private TransferFacade facade;

    @Schedule(name = "processTransfers", cron = "0 * * * * ?")
    public void processTransfers() {
        log.info("Transfer processing started");
        facade.process();
        log.info("Transfer processing ended");
    }

}