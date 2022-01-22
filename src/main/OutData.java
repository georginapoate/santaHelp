package main;

import java.util.ArrayList;

public class OutData {
    private ArrayList<Years> annualChildren;

    public ArrayList<Years> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(ArrayList<Years> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public void update(Years years) {
        if (this.annualChildren == null) {
            this.annualChildren = new ArrayList<Years>();
        }
        this.annualChildren.add(years);
    }
}
