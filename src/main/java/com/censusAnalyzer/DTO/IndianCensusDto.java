package com.censusAnalyzer.DTO;

public class IndianCensusDto {
    public String state;
    public long population;
    public long areaInSqKm;
    public long densityPerSqKm;
    public String stateCode;

    public IndianCensusDto(IndianCensusCsv indianCensusCsv){
        state = indianCensusCsv.state;
        population = indianCensusCsv.population;
        areaInSqKm = indianCensusCsv.areaInSqKm;
        densityPerSqKm = indianCensusCsv.densityPerSqKm;
    }
}
