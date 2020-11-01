package com.ohdocha.admin.service;

import com.ohdocha.admin.config.ErrorCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ServiceExtension implements ErrorCode {

    protected String createIdx() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

}