package com.marketplace.couponMarketplace.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class InMemoryLogHandler extends Handler {

    private final List<LogRecord> logRecords = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void publish(LogRecord record) {
        logRecords.add(record);
    }

    @Override
    public void flush() {
        // No need to implement for InMemoryLogHandler
    }

    @Override
    public void close() throws SecurityException {
        logRecords.clear();
    }

    public List<LogRecord> getLogRecords() {
        return logRecords;
    }
}
