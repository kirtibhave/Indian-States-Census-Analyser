package com.censusAnalyzer.Builder;

import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.Exception.CsvBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import javafx.animation.ScaleTransition;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCsvBuilder implements IcsvBuilder {
    public Iterator<IcsvBuilder> getCSVFileIterator(Reader reader, Class csvClass) {
        return this.getCsvToBean(reader, csvClass).iterator();
    }

    @Override
    public List<IcsvBuilder> getCsvFileList(Reader reader, Class csvClass) {
        return this.getCsvToBean(reader, csvClass).parse();
    }

    private CsvToBean getCsvToBean(Reader reader, Class csvClass) {
        try {
            CsvToBeanBuilder<IcsvBuilder> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            csvToBeanBuilder.withSeparator(',');
            CsvToBean<IcsvBuilder> csvToBean = csvToBeanBuilder.build();
            return csvToBean;
        } catch (IllegalStateException e) {
            throw new CsvBuilderException(CsvBuilderException.ExceptionType.UNABLE_TO_PARSE, e.getMessage());
        }
    }
}