package com.censusAnalyzer.Exception;

public class CsvBuilderException extends RuntimeException {

    public enum ExceptionType {
        UNABLE_TO_PARSE
    }

    public static ExceptionType type;

    public CsvBuilderException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
