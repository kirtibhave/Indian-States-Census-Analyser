package com.censusAnalyzer.Builder;

public class CsvBuilderFactory {

    public static IcsvBuilder CreateCsvBuilder(){
        return new OpenCsvBuilder();
    }
}
