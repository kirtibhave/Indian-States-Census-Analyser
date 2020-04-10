package com.censusAnalyzer.DTO;

import com.opencsv.bean.CsvBindByName;

public class IndianCensusCsv {

    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Population",required = true)
    public double population;

    @CsvBindByName(column = "AreaInSqKm",required = true)
    public double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public double densityPerSqKm;

    public IndianCensusCsv(String state, double population, double areaInSqKm, double densityPerSqKm){
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    @Override
    public String toString() {
        return "IndiaCensusCsvPojo{" +
                "state='" + state + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }
}
