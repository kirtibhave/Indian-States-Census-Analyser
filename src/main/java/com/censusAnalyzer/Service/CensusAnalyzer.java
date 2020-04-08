package com.censusAnalyzer.Service;

import com.censusAnalyzer.Builder.CsvBuilderFactory;
import com.censusAnalyzer.Builder.IcsvBuilder;
import com.censusAnalyzer.DTO.IndianCensusDto;
import com.censusAnalyzer.DTO.IndianStateCensusCodePojo;
import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.DTO.IndianCensusCsvPojo;
import com.censusAnalyzer.Exception.CsvBuilderException;
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
    Map<String, IndianCensusDto> censusMap;
    List<IndianCensusDto> indianCensusDtoList;

    public CensusAnalyzer() {
        censusMap = new HashedMap();
    }

    public int loadCensusData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder csvBuilder = CsvBuilderFactory.CreateCsvBuilder();
            Iterator<IndianCensusCsvPojo> censusCsvIterator = csvBuilder.getCSVFileIterator(reader, IndianCensusCsvPojo.class);
            while (censusCsvIterator.hasNext()) {
                IndianCensusCsvPojo indianCensusCsv = censusCsvIterator.next();
                censusMap.put(indianCensusCsv.state, new IndianCensusDto(indianCensusCsv));
            }
            indianCensusDtoList = censusMap.values().stream().collect(Collectors.toList());
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
            Iterator<IndianStateCensusCodePojo> stateCodeCSVIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCensusCodePojo.class);
            while (stateCodeCSVIterator.hasNext()) {
                IndianStateCensusCodePojo indiaStateCodeCSV = stateCodeCSVIterator.next();
                IndianCensusDto indiaCensusDTO = censusMap.get(indiaStateCodeCSV.state);
                if (indiaCensusDTO == null)
                    continue;
                }
                return censusMap.size();
            } catch (IOException e) {
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
            } catch (RuntimeException e){
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
        }
    }

        private <E > int getCount (Iterator < E > iterator) {
            Iterable<E> csvIterable = () -> iterator;
            int numberOfEnteries= (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numberOfEnteries;
        }

        public String getStateWiseSortedCensusData (String csvFilePath) throws CensusAnalyzerException {
            if (indianCensusDtoList.size() == 0 || indianCensusDtoList == null)
                throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No Data");
            Comparator<IndianCensusDto> indiaCensusCsvComparator = Comparator.comparing(census -> census.state);
            this.sort(indiaCensusCsvComparator);
            String sortedCensusJson = new Gson().toJson(indianCensusDtoList);
            return sortedCensusJson;
        }

        private void sort (Comparator < IndianCensusDto > indianCensusCsvComparator) {
            for (int i = 0; i < indianCensusDtoList.size() - 1; i++) {
                for (int j = 0; j < indianCensusDtoList.size() - i - 1; j++) {
                    IndianCensusDto census1 = indianCensusDtoList.get(j);
                    IndianCensusDto census2 = indianCensusDtoList.get(j + 1);
                    if (indianCensusCsvComparator.compare(census1,census2)>0){
                        indianCensusDtoList.set(j,census2);
                        indianCensusDtoList.set(j+1,census1);
                    }
                }
            }
        }
    }
