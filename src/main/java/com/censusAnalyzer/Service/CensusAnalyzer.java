package com.censusAnalyzer.Service;

import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.IndianCensusCsvPojo;
import com.censusAnalyzer.Builder.CsvBuilderFactory;
import com.censusAnalyzer.Builder.IcsvBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    List<IndianCensusCsvPojo> censusCsvList = null;
    public int loadCensusData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder csvBuilder = CsvBuilderFactory.CreateCsvBuilder();
            censusCsvList = csvBuilder.getCsvFileList(reader,IndianCensusCsvPojo.class);
            return censusCsvList.size();
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
        }
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder csvBuilder= CsvBuilderFactory.CreateCsvBuilder();
            censusCsvList = csvBuilder.getCsvFileList(reader,IndianCensusCsvPojo.class);
            return censusCsvList.size();
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numberOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numberOfEnteries;
    }

    public String getStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException {
        if(censusCsvList.size()==0 || censusCsvList==null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA,"No Data");
            Comparator<IndianCensusCsvPojo> indianCensusCsvComparator = Comparator.comparing(census -> census.state);
            this.sort(indianCensusCsvComparator);
            String sortedCensusJson = new Gson().toJson(censusCsvList);
            return sortedCensusJson;
        }

    private void sort(Comparator<IndianCensusCsvPojo> indianCensusCsvComparator) {
        for (int i = 0; i < censusCsvList.size()-1; i++){
            for (int j=0; j < censusCsvList.size()-i-1; j++){
                IndianCensusCsvPojo census1 = censusCsvList.get(j);
                IndianCensusCsvPojo census2 = censusCsvList.get(j+1);
                if (indianCensusCsvComparator.compare(census1,census2)>0){
                    censusCsvList.set(j,census2);
                    censusCsvList.set(j+1,census1);
                }
            }
        }
    }
}

