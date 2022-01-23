package main;

import java.util.ArrayList;

public class OutData {
    private ArrayList<Years> annualChildren;

    /**
     * @return
     */
    public ArrayList<Years> getAnnualChildren() {
        return annualChildren;
    }

    /**
     * @param annualChildren
     */
    public void setAnnualChildren(final ArrayList<Years> annualChildren) {
        this.annualChildren = annualChildren;
    }

    /**
     * @param years
     */
    public void update(final Years years) {
        if (this.annualChildren == null) {
            this.annualChildren = new ArrayList<Years>();
        }
        this.annualChildren.add(years);
    }
}
