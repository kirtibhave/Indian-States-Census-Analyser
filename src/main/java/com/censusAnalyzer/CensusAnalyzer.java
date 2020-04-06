package com.censusAnalyzer;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    public int loadCensusData(String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndianCensusCsvPojo> censusCsvIterator = getCsvFileIterator(reader, IndianCensusCsvPojo.class);
            Iterable<IndianCensusCsvPojo> csvIterable = () -> censusCsvIterator;
            int numberOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numberOfEnteries;
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
        }
    }

    public int loadStateCodeData (String csvFilePath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndianCensusCsvPojo> censusCsvIterator = getCsvFileIterator(reader, IndianCensusCsvPojo.class);
            Iterable<IndianCensusCsvPojo> csvIterable = () -> censusCsvIterator;
            int numberOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numberOfEnteries;
        } catch (IOException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.getMessage());
        }
    }

    private <E > Iterator < E > getCsvFileIterator(Reader reader, Class csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        csvToBeanBuilder.withSeparator(',');
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
    }
}
