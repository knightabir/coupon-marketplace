package com.marketplace.couponMarketplace.controller.log;

import com.marketplace.couponMarketplace.helper.LogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public List<String> getAllLogs() {
        List<LogRecord> logRecords = logService.getAllLogs(); // Call the service method to get the log records>
        log.info("Retrieved {} log records", logRecords.size());
        return logRecords.stream()
                .map(record -> record.getMillis() + " - " + record.getMessage())
                        .collect(Collectors.toList());
    }
}
