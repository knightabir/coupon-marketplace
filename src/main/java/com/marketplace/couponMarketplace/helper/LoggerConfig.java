package com.marketplace.couponMarketplace.helper;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class LoggerConfig {

    public static final Logger log = Logger.getLogger(LoggerConfig.class.getName());
    private static final InMemoryLogHandler inMemoryLogHandler = new InMemoryLogHandler();

    @PostConstruct
    public void init() {
        log.addHandler(inMemoryLogHandler);
    }

    public static InMemoryLogHandler getInMemoryLogHandler() {
        return inMemoryLogHandler;
    }
}
