package com.censusAnalyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {

    private static final String INDIAN_STATE_CENSUS_CSV_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/test/resources/StateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCensusData.java";
    private static final String WRONG_CSV_DATA_FILE_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndianStateCensusWrongData.csv";

    CensusAnalyzer censusAnalyzer;

    @Before
    public void initialize()
    {
        censusAnalyzer = new CensusAnalyzer();
    }

    //1.1
    @Test
    public void givenIndianCensusCSVFile_ShouldReturnsExactCountOfRecords() {
        try{
            int numberOfRecord = censusAnalyzer.loadCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //1.2
    @Test
    public void givenIndianCensusData_WithWrongFileName_ShouldThrowException() {
        try{
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //1.3
    @Test
    public void givenIndianCensusData_WithWrongFileType_ShouldThrowException() {
        try{
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //1.4
    @Test
    public void givenIndianCensusData_whenDelimiterIncorrect_shouldReturnException() {
        try {
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(WRONG_CSV_DATA_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }
}