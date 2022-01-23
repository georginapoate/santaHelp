package main;

import java.util.ArrayList;

public class AnnualChanges {
    private Integer newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<Child> childrenUpdates;
    private String strategy;


    /**
     * @return new santabudget for the year
     */
    public Integer getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * @param newSantaBudget
     */
    public void setNewSantaBudget(final Integer newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /**
     * @return
     */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    /**
     * @param newGifts
     */
    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    /**
     * @return new children for the year
     */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    /**
     * @param newChildren
     */
    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     * @return arrayList of new updates for children
     */
    public ArrayList<Child> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     * @param childrenUpdates
     */
    public void setChildrenUpdates(final ArrayList<Child> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    /**
     * @return gift assignment strategy for this year
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * @param strategy
     */
    public void setStrategy(final String strategy) {
        this.strategy = strategy;
    }
}
