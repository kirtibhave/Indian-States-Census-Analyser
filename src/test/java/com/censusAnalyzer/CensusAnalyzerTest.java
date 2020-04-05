package com.censusAnalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {

    private static final String INDIAN_STATE_CENSUS_CSV_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/test/resources/StateCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ShouldReturnsExactCountOfRecords() {
        try{
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            int numberOfRecord = censusAnalyzer.loadCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
            Assert.assertEquals(29,numberOfRecord);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WithWrongFile_ShouldThrowException() {
        try{
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }
}
