package main;

import java.util.ArrayList;

public class Years {
    /**
     * @return
     */
    public ArrayList<Child> getChildren() {
        return children;
    }

    private ArrayList<Child> children;

    /**
     * @param ch
     */
    public void setChildren(final ArrayList<Child> ch) {
        ch.sort(new IdComparator());
        this.children = ch;
    }
}
