package com.censusAnalyzer;

public class CensusAnalyzerException extends Throwable {

    public enum ExceptionType{
        CSV_FILE_PROBLEM;
    }

    public ExceptionType type;

    public CensusAnalyzerException(ExceptionType type, String message){
        super(message);
        this.type = type;
    }

}
