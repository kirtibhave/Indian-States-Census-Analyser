package com.censusAnalyzer.Adapter;

import com.censusAnalyzer.DAO.CensusDao;
import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.censusAnalyzer.Service.CensusAnalyzer;

import java.util.Map;

public class CensusAdapterFactory {
    public static Map<String, CensusDao> getCensusData(CensusAnalyzer.Country country, String[] csvFilePath) throws CensusAnalyzerException {
        if (country.equals(CensusAnalyzer.Country.INDIA))
            return new IndiaCensusAdaptor().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyzer.Country.US))
            return new UsCensusAdapter().loadCensusData(csvFilePath);
        throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.INVALID_COUNTRY, "Invalid Country");
    }
}