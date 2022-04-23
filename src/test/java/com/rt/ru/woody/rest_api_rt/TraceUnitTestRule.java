package com.rt.ru.woody.rest_api_rt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class TraceUnitTestRule implements AfterEachCallback, BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("**************************************************");
        log.info("********* Starting test execution: {} ***** ", context.getDisplayName());
        log.info("**************************************************");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        log.info("**************************************************");
        log.info("********* Ending test execution: {} ***** ", context.getDisplayName());
        log.info("**************************************************");
    }
}
