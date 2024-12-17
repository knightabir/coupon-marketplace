package com.marketplace.couponMarketplace.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.LogRecord;

@Service
public class LogServiceImpl implements LogService{
    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Override
    public List<LogRecord> getAllLogs() {
        log.info("Retrieving all logs");
        return LoggerConfig.getInMemoryLogHandler().getLogRecords();
    }
}
