package com.censusAnalyzer.DAO;

import com.censusAnalyzer.DTO.IndianCensusCsv;

public class IndianCensusDao {
    public String state;
    public long population;
    public long areaInSqKm;
    public long densityPerSqKm;
    public String stateCode;

    public IndianCensusDao(IndianCensusCsv indianCensusCsv){
        state = indianCensusCsv.state;
        population = indianCensusCsv.population;
        areaInSqKm = indianCensusCsv.areaInSqKm;
        densityPerSqKm = indianCensusCsv.densityPerSqKm;
    }
}
