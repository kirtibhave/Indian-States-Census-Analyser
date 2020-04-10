package com.censusAnalyzer.DAO;

import com.censusAnalyzer.DTO.IndianCensusCsv;
import com.censusAnalyzer.DTO.USCensusCsv;
import com.censusAnalyzer.Service.CensusAnalyzer;

import java.util.Comparator;

public class CensusDao {
    public String state;
    public double population;
    public double totalArea;
    public static double totalDensity;
    public String stateCode;
    public double populationDensity;

    public CensusDao(IndianCensusCsv indianCensusCsv){
        state = indianCensusCsv.state;
        population = indianCensusCsv.population;
        totalArea = indianCensusCsv.areaInSqKm;
        totalDensity = indianCensusCsv.densityPerSqKm;
    }

    public CensusDao(USCensusCsv usCensusCsv){
        state = usCensusCsv.state;
        stateCode = usCensusCsv.stateId;
        population = usCensusCsv.population;
        totalArea = usCensusCsv.totalArea;
        populationDensity = usCensusCsv.populationDensity;
    }

    public static Comparator<CensusDao> getSortComparator(CensusAnalyzer.SortingMode mode){
        if (mode.equals(CensusAnalyzer.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        else if (mode.equals(CensusAnalyzer.SortingMode.POPULATION))
            return Comparator.comparing(CensusDao::getPopulation).reversed();
        else if (mode.equals(CensusAnalyzer.SortingMode.DENSITY))
            return Comparator.comparing(CensusDao::getPopulationDensity).reversed();
        else if (mode.equals(CensusAnalyzer.SortingMode.AREA))
            return Comparator.comparing(census -> census.totalArea);
        return null;
    }

    public Object getCensusDTO(CensusAnalyzer.Country country){
        if (country.equals(CensusAnalyzer.Country.INDIA))
            return new IndianCensusCsv(state,population,totalArea,populationDensity);
        else if (country.equals(CensusAnalyzer.Country.US))
            return new USCensusCsv(state,stateCode,population,totalArea,totalDensity);
        return null;
    }

    public double getPopulation() {
        return population;
    }

    public double getPopulationDensity() {
        return totalDensity;
    }

}
