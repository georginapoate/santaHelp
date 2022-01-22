package main;

import java.util.ArrayList;

public class Years {
    public ArrayList<Child> getChildren() {
        return children;
    }

    private ArrayList<Child> children;

    public void setChildren(ArrayList<Child> ch) {
        ch.sort(new idComparator());
        this.children = ch;
    }

//    public void setChildren(ArrayList<Child> children) {
//        this.children = children;
//    }
}