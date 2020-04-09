package com.censusAnalyzer.DTO;

import com.opencsv.bean.CsvBindByName;

public class USCensusCsv {
    @CsvBindByName(column = "State Id", required = true)
    public String stateId;
    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "Population")
    public double population;
    @CsvBindByName(column = "Housing units")
    public double housingUnit;
    @CsvBindByName(column = "Total area")
    public double totalArea;
    @CsvBindByName(column = "Land area")
    public double landArea;
    @CsvBindByName(column = "Population Density")
    public double populationDensity;
    @CsvBindByName(column = "Housing Density")
    public double housingDensity;

    @Override
    public String toString() {
        return "UsCensusCsv{" +
                "stateId='" + stateId + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housingUnit=" + housingUnit +
                ", totalArea=" + totalArea +
                ", landArea=" + landArea +
                ", populationDensity=" + populationDensity +
                ", housingDensity=" + housingDensity +
                '}';
    }
}
