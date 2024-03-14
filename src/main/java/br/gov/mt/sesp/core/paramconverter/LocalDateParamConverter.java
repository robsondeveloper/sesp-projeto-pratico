package br.gov.mt.sesp.core.paramconverter;

import jakarta.ws.rs.ext.ParamConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateParamConverter implements ParamConverter<LocalDate> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate fromString(String param) {
        try {
            return LocalDate.parse(param, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString(LocalDate localDate) {
        return localDate.format(DATE_TIME_FORMATTER);
    }
}