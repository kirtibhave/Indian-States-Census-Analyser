package com.censusAnalyzer.Exception;

public class CensusAnalyzerException extends Exception {

    public enum ExceptionType{
        CSV_FILE_PROBLEM,CSV_TEMPLATE_PROBLEM,NO_CENSUS_DATA
    }

    public ExceptionType type;

    public CensusAnalyzerException(ExceptionType type, String message){
        super(message);
        this.type = type;
    }
}
