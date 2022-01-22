package main;

import java.util.ArrayList;

public class AnnualChanges {
    private Integer newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<Child> childrenUpdates;
    private String strategy;

    public Integer getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(Integer newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<Child> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(ArrayList<Child> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
