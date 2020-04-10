package com.censusAnalyzer.Service;

import com.censusAnalyzer.Adapter.CensusAdapterFactory;
import com.censusAnalyzer.DAO.CensusDao;
import com.censusAnalyzer.Exception.CensusAnalyzerException;
import com.google.gson.Gson;
import org.apache.commons.collections.map.HashedMap;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

public class CensusAnalyzer {
    public enum Country {INDIA, US}

    public enum SortingMode {STATE, POPULATION, DENSITY, AREA}

    Map<String, CensusDao> censusMap;
    List<CensusDao> censusDaoList;
    private Country country;

    public CensusAnalyzer(Country country) {
        censusMap = new HashMap<>();
        this.country = country;
    }

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyzerException {
        censusMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        return censusMap.size();
    }

    public String getSortedCensusData(SortingMode mode) throws CensusAnalyzerException {
        if (censusMap.size() == 0 || censusMap == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No Data");
        ArrayList censusDTO = censusMap.values().stream()
                .sorted(CensusDao.getSortComparator(mode))
                .map(censusDao -> censusDao.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }

    public String getDualSortByPopulationDensity() throws CensusAnalyzerException {
        if (censusMap.size() == 0 || censusMap == null)
            throw new CensusAnalyzerException(CensusAnalyzerException.ExceptionType.NO_CENSUS_DATA, "No Data");
        ArrayList censusDTO = censusMap.values().stream()
                .sorted(Comparator.comparingDouble(CensusDao::getPopulation)
                        .thenComparingDouble(CensusDao::getPopulationDensity).reversed())
                .map(censusDao -> censusDao.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }
}