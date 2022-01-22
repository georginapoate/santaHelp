package main;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Parser {

    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Integer numberOfYears;
    private Integer santaBudget;
    private InitialData initialData;
    private ArrayList<AnnualChanges> annualChanges;

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Integer getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Integer santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(ArrayList<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }
}