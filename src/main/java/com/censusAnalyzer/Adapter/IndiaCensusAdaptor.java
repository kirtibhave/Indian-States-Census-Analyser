package com.censusAnalyzer.Adapter;

import com.censusAnalyzer.Builder.CsvBuilderFactory;
import com.censusAnalyzer.Builder.IcsvBuilder;
import com.censusAnalyzer.DAO.CensusDao;
import com.censusAnalyzer.DTO.IndianCensusCsv;
import com.censusAnalyzer.DTO.IndianStateCensusCode;
import com.censusAnalyzer.Exception.CensusAnalyzerException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdaptor extends CensusAdapter {

    Map<String, CensusDao> censusMap;
    List<CensusDao> censusDaoList;

    @Override
    public Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyzerException {
        censusMap = super.loadCensusData(IndianCensusCsv.class, csvFilePath[0]);
        this.loadStateCodeData(csvFilePath[1]);
        return censusMap;
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder csvBuilder = CsvBuilderFactory.CsvBuilder();
            Iterator<IndianStateCensusCode> stateCodeCSVIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCensusCode.class);
            Iterable<IndianStateCensusCode> csvIterable = () -> stateCodeCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusMap.get(csvState.state) != null)
                    .forEach(csvState -> censusMap.get(csvState.state).stateCode = csvState.stateCode);
            return censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
        }
    }

}