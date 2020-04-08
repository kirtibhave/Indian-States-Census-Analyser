package com.censusAnalyzer.DTO;

public class IndianCensusDto {
    public String state;
    public long population;
    public long areaInSqKm;
    public long densityPerSqKm;

    public IndianCensusDto(IndianCensusCsvPojo indianCensusCsv){
        state = indianCensusCsv.state;
        population = indianCensusCsv.population;
        areaInSqKm = indianCensusCsv.areaInSqKm;
        densityPerSqKm = indianCensusCsv.densityPerSqKm;
    }
}
