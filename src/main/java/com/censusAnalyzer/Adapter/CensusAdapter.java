package com.censusAnalyzer.Adapter;

import com.censusAnalyzer.Builder.CsvBuilderFactory;
import com.censusAnalyzer.Builder.IcsvBuilder;
import com.censusAnalyzer.DAO.CensusDao;
import com.censusAnalyzer.DTO.IndianCensusCsv;
import com.censusAnalyzer.DTO.USCensusCsv;
import com.censusAnalyzer.Exception.CensusAnalyzerException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAdapter {
    Map<String, CensusDao> censusMap;
    List<CensusDao> censusDaoList;
    public <E> List<CensusDao> loadCensusData(String csvFilePath, Class<E> censusCsvClass) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder icsvBuilder = CsvBuilderFactory.CreateCsvBuilder();
            Iterator<E> censusCsvIterator = icsvBuilder.getCSVFileIterator(reader, censusCsvClass);
            Iterable<E> csvIterable = () -> censusCsvIterator;
            if (censusCsvClass.getName().equals("com.censusAnalyzer.DTO.IndianCensusCsv")){
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IndianCensusCsv.class::cast)
                    .forEach(censusCsv -> censusMap.put(censusCsv.state, new CensusDao(censusCsv)));
        }
            else if (censusCsvClass.getName().equals("com.censusAnalyzer.DTO.UsCensusCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USCensusCsv.class::cast)
                        .forEach(censusCsv -> censusMap.put(censusCsv.state, new CensusDao(censusCsv)));
            }
            censusDaoList = censusMap.values().stream().collect(Collectors.toList());
            return censusDaoList;
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        }catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM,e.getMessage());
        }
    }
}
