package com.censusAnalyzer.DTO;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCensusCodePojo {

    @CsvBindByName(column = "SrNo",required = true)
    private int srNo;

    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Name",required = true)
    private String name;

    @CsvBindByName(column = "TIN",required = true)
    private int tin;

    @CsvBindByName(column = "StateCode")
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaStateCodePojo{" +
                "srNo=" + srNo +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", tin=" + tin +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }

}
