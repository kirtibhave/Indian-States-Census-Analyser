package com.censusAnalyzer.Builder;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder<E> {
    Iterator<E> getCSVFileIterator(Reader reader, Class csvClass);
    List<E> getCsvFileList(Reader reader, Class csvClass);
}

