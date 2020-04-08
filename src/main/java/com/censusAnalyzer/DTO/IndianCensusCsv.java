package com.censusAnalyzer.DTO;

import com.opencsv.bean.CsvBindByName;

public class IndianCensusCsv {

    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Population",required = true)
    public long population;

    @CsvBindByName(column = "AreaInSqKm",required = true)
    public long areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public long densityPerSqKm;

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
