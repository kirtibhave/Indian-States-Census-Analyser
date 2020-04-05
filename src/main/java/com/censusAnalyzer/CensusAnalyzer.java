package com.censusAnalyzer;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyzer {
    public int loadCensusData(String csvFilePath)  {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndianCensusCsvPojo>  csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndianCensusCsvPojo.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndianCensusCsvPojo> csvToBean = csvToBeanBuilder.build();
            Iterator<IndianCensusCsvPojo> censusCsvIterator = csvToBean.iterator();
            int numberOfEntries = 0;
            while (censusCsvIterator.hasNext()) {
                numberOfEntries++;
                IndianCensusCsvPojo  censusData = censusCsvIterator.next();
            }
            return numberOfEntries;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
