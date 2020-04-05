package com.censusAnalyzer;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyzerTest {

    private static final String INDIAN_STATE_CENSUS_CSV_PATH ="C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCensusData.csv";

    @Test
    public void givenIndian_CensusCSVFile_ShouldReturnsExactCountOfRecords(){
        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int numberOfRecord = censusAnalyzer.loadCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
        Assert.assertEquals(29,numberOfRecord);
    }
}
