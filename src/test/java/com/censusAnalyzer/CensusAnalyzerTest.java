package com.censusAnalyzer;

import com.censusAnalyzer.DTO.IndianCensusCsv;
import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.Service.CensusAnalyzer;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CensusAnalyzerTest {

    private static final String INDIAN_STATE_CENSUS_CSV_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/test/resources/StateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCensusData.java";
    private static final String WRONG_CSV_DATA_FILE_DELIMITER = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/test/resource/IndianStateCensusWrongData.csv";
    private static final String WRONG_STATE_CENSUS_CSV_HEADER = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCensusData.csv";

    private static final String INDIAN_STATE_DATA_FILE_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCode.csv";
    private static final String WRONG_STATE_FILE_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/test/resources/StateCode.csv";
    private static final String WRONG_STATE_DATA_FILE_TYPE = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCode.html";
    private static final String WRONG_STATE_DATA_FILE_DELIMITER = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/test/resource/WrongStateCodeData.csv";
    private static final String WRONG_STATE_DATA_HEADER = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/StateCode.csv";

    CensusAnalyzer censusAnalyzer;

    @Before
    public void initialize() {
        censusAnalyzer = new CensusAnalyzer();
    }

    //1.1
    @Test
    public void givenIndianCensusCSVFile_ShouldReturnsExactCountOfRecords() {
        try {
            int numberOfRecord = censusAnalyzer.loadCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //1.2
    @Test
    public void givenIndianCensusData_WithWrongFileName_ShouldThrowException() {
        try {
            censusAnalyzer.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //1.3
    @Test
    public void givenIndianCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            censusAnalyzer.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //1.4
    @Test
    public void givenIndianCensusData_whenDelimiterIncorrect_shouldReturnException() {
        try {
            censusAnalyzer.loadCensusData(WRONG_CSV_DATA_FILE_DELIMITER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //1.5
    @Test
    public void givenStateCensusCsvFile_whenCsvHeaderIncorrect_shouldReturnException() {
        try {
            censusAnalyzer.loadCensusData(WRONG_STATE_CENSUS_CSV_HEADER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //2.1
    @Test
    public void givenStateCodeCsvFile_returnCorrectNumberOfRecord() {
        try {
            int numberOfRecord = censusAnalyzer.loadStateCodeData(INDIAN_STATE_DATA_FILE_PATH);
            Assert.assertEquals(30, numberOfRecord);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //2.2
    @Test
    public void givenStateCodeCsvFile_WithWrongFileName_ShouldThrowException() {
        try {
            censusAnalyzer.loadStateCodeData(WRONG_STATE_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //2.3
    @Test
    public void givenStateCodeCsvFile_WithWrongFileType_ShouldThrowException() {
        try {
            censusAnalyzer.loadCensusData(WRONG_STATE_DATA_FILE_TYPE);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //2.4
    @Test
    public void givenStateCodeCsvFile_whenDelimiterIncorrect_shouldReturnException() {
        try {
            censusAnalyzer.loadCensusData(WRONG_STATE_DATA_FILE_DELIMITER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //2.5
    @Test
    public void givenStateCodeCsvFile_whenCsvHeaderIncorrect_shouldReturnException() {
        try {
            censusAnalyzer.loadCensusData(WRONG_STATE_DATA_HEADER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataOfStartState() {
        try {
            censusAnalyzer.loadCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyzer.getStateWiseSortedCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndianCensusCsv[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataOfEndState() {
        try {
            censusAnalyzer.loadCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyzer.getStateWiseSortedCensusData(INDIAN_STATE_CENSUS_CSV_PATH);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndianCensusCsv[].class);
            Assert.assertEquals("West Bengal", censusCsv[28].state);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
    }

}