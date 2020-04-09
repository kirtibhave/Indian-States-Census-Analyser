package com.censusAnalyzer.Service;

import com.censusAnalyzer.Adapter.CensusAdapter;
import com.censusAnalyzer.Builder.CsvBuilderFactory;
import com.censusAnalyzer.Builder.IcsvBuilder;
import com.censusAnalyzer.DAO.CensusDao;
import com.censusAnalyzer.DTO.IndianStateCensusCode;
import com.censusAnalyzer.DTO.USCensusCsv;
import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.DTO.IndianCensusCsv;
import com.google.gson.Gson;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    Map<String, CensusDao> censusMap;
    List<CensusDao> censusDaoList;

    public CensusAnalyzer() {
        censusMap = new HashedMap();
    }

    public int loadIndianCensusData(String csvFilePath) throws CensusAnalyzerException {
        censusMap = new CensusAdapter().loadCensusData(IndianCensusCsv.class, csvFilePath);
        censusDaoList = censusMap.values().stream().collect(Collectors.toList());
        return censusMap.size();
    }

    public int loadUSCensusData(String csvFilePath, Class<USCensusCsv> usCensusCsvClass) throws CensusAnalyzerException {
        return this.loadUSCensusData(csvFilePath, USCensusCsv.class);
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numberOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numberOfEnteries;
    }

    public String getStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No Data");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.state);
        this.sort(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.population);
        this.sort(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No census Data");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.totalDensity);
        this.sort(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getAreaWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No census Data");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.totalArea);
        this.sort(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getUsStateSortedCensusData(String csvFilePath) throws CensusAnalyzerException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No census Data");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.state);
        this.sort(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    private void sort(Comparator<CensusDao> indianCensusCsvComparator) {
        for (int i = 0; i < censusDaoList.size() - 1; i++) {
            for (int j = 0; j < censusDaoList.size() - i - 1; j++) {
                CensusDao census1 = censusDaoList.get(j);
                CensusDao census2 = censusDaoList.get(j + 1);
                if (indianCensusCsvComparator.compare(census1, census2) > 0) {
                    censusDaoList.set(j, census2);
                    censusDaoList.set(j + 1, census1);
                }
            }
        }
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyzerException {
        return new CensusAdapter().loadCensusData(USCensusCsv.class, csvFilePath).size();
    }
}