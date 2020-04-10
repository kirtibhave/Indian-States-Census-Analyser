package com.censusAnalyzer;

import com.censusAnalyzer.DTO.IndianCensusCsv;
import com.censusAnalyzer.DTO.USCensusCsv;
import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.Service.CensusAnalyzer;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    private static final String US_CENSUS_CSV_FILE_PATH = "C:/Users/Lenovo/IdeaProjects/IndianCensusAnalyser/src/main/resources/USCensusCsv.csv";


    //1.1
    @Test
    public void givenIndianCensusCSVFile_ShouldReturnsExactCountOfRecords() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            int numberOfRecord = censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIAN_STATE_CENSUS_CSV_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    //1.2
    @Test
    public void givenIndianCensusData_WithWrongFileName_ShouldThrowException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //1.3
    @Test
    public void givenIndianCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //1.4
    @Test
    public void givenIndianCensusData_whenDelimiterIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, WRONG_CSV_DATA_FILE_DELIMITER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //1.5
    @Test
    public void givenIndianCensusData_whenCsvHeaderIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, WRONG_STATE_CENSUS_CSV_HEADER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }


    //2.1
    @Test
    public void givenStateCodeCsvFile_returnCorrectNumberOfRecord() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            int numberOfRecord = censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIAN_STATE_DATA_FILE_PATH);
            Assert.assertEquals(30, numberOfRecord);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    //2.2
    @Test
    public void givenStateCodeCsvFile_WithWrongFileName_ShouldThrowException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, WRONG_STATE_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    //2.3
    @Test
    public void givenStateCodeCsvFile_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA,INDIAN_STATE_CENSUS_CSV_PATH,INDIAN_STATE_CENSUS_CSV_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //2.4
    @Test
    public void givenStateCodeCsvFile_whenDelimiterIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, WRONG_STATE_DATA_FILE_DELIMITER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //2.5
    @Test
    public void givenStateCodeCsvFile_whenCsvHeaderIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, WRONG_STATE_DATA_HEADER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedOnState_shouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIAN_STATE_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.STATE);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndianCensusCsv[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedOnState_shouldReturnSortedData() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIAN_STATE_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.STATE);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndianCensusCsv[].class);
            Assert.assertEquals("West Bengal", censusCsv[28].state);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_whenUnSortedOnState_shouldReturnUnSortedData() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA,INDIAN_STATE_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.STATE);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData,IndianCensusCsv[].class);
            Assert.assertNotEquals("Andra Prades",censusCsv[1].state);
        } catch (CensusAnalyzerException  e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedOnPopulation_shouldReturnSortedResult() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIAN_STATE_CENSUS_CSV_PATH,INDIAN_STATE_DATA_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.STATE);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndianCensusCsv[].class);
            Assert.assertEquals("Utter Prades", censusCsv[0].state);
        } catch (CensusAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_whenUnSortedOnPopulation_shouldReturnUResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIAN_STATE_CENSUS_CSV_PATH, INDIAN_STATE_DATA_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.POPULATION);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndianCensusCsv[].class);
            Assert.assertNotEquals("Bihar", censusCsv[4].state);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedOnDensity_shouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA,INDIAN_STATE_CENSUS_CSV_PATH,INDIAN_STATE_DATA_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.POPULATION);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(String.valueOf(sortedCensusData), IndianCensusCsv[].class);
            Assert.assertEquals("West Bengal", censusCsv[1].state);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedOnArea_shouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIAN_STATE_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.AREA);
            IndianCensusCsv[] censusCsv = new Gson().fromJson(String.valueOf(sortedCensusData), IndianCensusCsv[].class);
            Assert.assertEquals("Rajasthan", censusCsv[0].state);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenCensusData_shouldReturnCorrectData() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.US);
            int numberOfRecord = censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecord);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenUsCensusData_whenSorted_shouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.US);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.STATE);
            USCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, USCensusCsv[].class);
            Assert.assertEquals("Alabama", censusCsv[0].state);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenUsCensusData_whenSorted_shouldReturnLastResult() {
        try {
            CensusAnalyzer censusAnalyzer =new CensusAnalyzer(CensusAnalyzer.Country.INDIA);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyzer.SortingMode.STATE);
            USCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, USCensusCsv[].class);
            Assert.assertEquals("Wyoming", censusCsv[50].state);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnDualSort_populationAndDensity_shouldReturnSortedList() {
        CensusAnalyzer censusAnalyzer = new CensusAnalyzer(CensusAnalyzer.Country.US);
        try {
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getDualSortByPopulationDensity();
            USCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, USCensusCsv[].class);
            Assert.assertEquals("Califonia", censusCsv[0].stateId);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }
}