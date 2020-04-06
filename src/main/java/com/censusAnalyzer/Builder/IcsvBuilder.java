package com.censusAnalyzer.Builder;

import java.io.Reader;
import java.util.Iterator;

    public interface IcsvBuilder<E> {
        public Iterator<E> getCSVFileIterator(Reader reader, Class csvclass);
    }

