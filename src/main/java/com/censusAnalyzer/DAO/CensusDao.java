package com.censusAnalyzer.DAO;

import com.censusAnalyzer.DTO.IndianCensusCsv;
import com.censusAnalyzer.DTO.USCensusCsv;

public class CensusDao {
    public String state;
    public double population;
    public double totalArea;
    public double totalDensity;
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
}
