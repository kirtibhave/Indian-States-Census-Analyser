package com.censusAnalyzer;

public class CensusAnalyzerException extends Throwable {

    public CensusAnalyzerException(ExceptionType type, String message){
        super(message);
        this.type = type;
    }

    enum ExceptionType{
        CSV_FILE_PROBLEM
    }
    ExceptionType type;
}
