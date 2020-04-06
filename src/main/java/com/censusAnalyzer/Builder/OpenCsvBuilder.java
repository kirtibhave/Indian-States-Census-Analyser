package com.censusAnalyzer.Builder;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCsvBuilder implements IcsvBuilder {
    public Iterator<IcsvBuilder> getCSVFileIterator(Reader reader, Class csvclass) {
        CsvToBeanBuilder<IcsvBuilder> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvclass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        IcsvBuilder csvBuilder = CsvBuilderFactory.CreateCsvBuilder();
        CsvToBean<IcsvBuilder> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();

    }
}