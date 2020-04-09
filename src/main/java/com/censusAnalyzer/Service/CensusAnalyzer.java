package com.censusAnalyzer.Service;

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

    public int loadCensusData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder csvBuilder = CsvBuilderFactory.CreateCsvBuilder();
            Iterator<IndianCensusCsv> censusCsvIterator = csvBuilder.getCSVFileIterator(reader, IndianCensusCsv.class);
            while (censusCsvIterator.hasNext()) {
                IndianCensusCsv indianCensusCsv = censusCsvIterator.next();
                censusMap.put(indianCensusCsv.state, new CensusDao(indianCensusCsv));
            }
            censusDaoList = censusMap.values().stream().collect(Collectors.toList());
            return censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
        }
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder csvBuilder = CsvBuilderFactory.CreateCsvBuilder();
            Iterator<IndianStateCensusCode> stateCodeCSVIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCensusCode.class);
            Iterable<IndianStateCensusCode> csvIterable = () -> stateCodeCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .filter(csvState -> censusMap.get(csvState.state) != null)
                    .forEach(csvState -> censusMap.get(csvState.state).stateCode = csvState.stateCode);
                return censusMap.size();
            } catch (IOException e) {
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
            } catch (RuntimeException e){
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
            }
        }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder csvBuilder = CsvBuilderFactory.CreateCsvBuilder();
            Iterator<USCensusCsv> censusCsvIterator = csvBuilder.getCSVFileIterator(reader, USCensusCsv.class);
            while (censusCsvIterator.hasNext()) {
                USCensusCsv usCensusCsv = censusCsvIterator.next();
                censusMap.put(usCensusCsv.state, new CensusDao(usCensusCsv));
            }
            censusDaoList = censusMap.values().stream().collect(Collectors.toList());
            return censusMap.size();
        } catch (IOException e){
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        }
    }

        private <E > int getCount (Iterator < E > iterator) {
            Iterable<E> csvIterable = () -> iterator;
            int numberOfEnteries= (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numberOfEnteries;
        }

        public String getStateWiseSortedCensusData (String csvFilePath) throws CensusAnalyzerException {
            if (censusDaoList.size() == 0 || censusDaoList == null)
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No Data");
            Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.state);
            this.sort(censusCsvComparator);
            String sortedCensusJson = new Gson().toJson(censusDaoList);
            return sortedCensusJson;
        }

        public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException{
            if (censusDaoList.size() == 0 || censusDaoList == null)
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
            Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.population);
            this.sort(censusCsvComparator);
            String sortedCensusJson = new Gson().toJson(censusDaoList);
            return sortedCensusJson;
        }

        public String getDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException{
            if (censusDaoList.size() == 0 || censusDaoList == null)
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No census Data");
            Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.totalDensity);
            this.sort(censusCsvComparator);
            String sortedCensusJson = new Gson().toJson(censusDaoList);
            return sortedCensusJson;
        }

    public String getAreaWiseSortedCensusData(String csvFilePath) throws CensusAnalyzerException{
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No census Data");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(census -> census.totalArea);
        this.sort(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    private void sort (Comparator <CensusDao> indianCensusCsvComparator) {
        for (int i = 0; i < censusDaoList.size() - 1; i++) {
            for (int j = 0; j < censusDaoList.size() - i - 1; j++) {
                CensusDao census1 = censusDaoList.get(j);
                CensusDao census2 = censusDaoList.get(j + 1);
                if (indianCensusCsvComparator.compare(census1,census2)>0){
                    censusDaoList.set(j,census2);
                    censusDaoList.set(j+1,census1);
                }
            }
        }
    }
}
