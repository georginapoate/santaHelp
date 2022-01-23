package main;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Parser {

    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Integer numberOfYears;
    private Integer santaBudget;
    private InitialData initialData;
    private ArrayList<AnnualChanges> annualChanges;

    /**
     * @return
     */
    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * @param numberOfYears
     */
    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     * @return
     */
    public Integer getSantaBudget() {
        return santaBudget;
    }

    /**
     * @param santaBudget
     */
    public void setSantaBudget(final Integer santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     * @return
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     * @param initialData
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     * @return
     */
    public ArrayList<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    /**
     * @param annualChanges
     */
    public void setAnnualChanges(final ArrayList<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
