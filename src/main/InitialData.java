package main;

import java.util.ArrayList;

public class InitialData {
    private ArrayList<Child> children;
    private ArrayList<Gift> santaGiftsList;

    /**
     * @return
     */
    public ArrayList<Child> getChildren() {
        return children;
    }

    /**
     * @param children
     */
    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }

    /**
     * @return
     */
    public ArrayList<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    /**
     * @param santaGiftsList
     */
    public void setSantaGiftsList(final ArrayList<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
