package com.censusAnalyzer.Builder;
import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.Exception.CsvBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder<E>{
        public Iterator<E> getCSVFileIterator(Reader reader, Class csvclass)throws CsvBuilderException, CensusAnalyzerException;
        public List<E> getCsvFileList(Reader reader, Class csvClass);
    }

