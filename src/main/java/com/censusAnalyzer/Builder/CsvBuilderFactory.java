package com.censusAnalyzer.Builder;

public class CsvBuilderFactory {
    public static IcsvBuilder CsvBuilder(){
        return new OpenCsvBuilder();
    }

}