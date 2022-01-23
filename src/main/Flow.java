package main;

import java.util.ArrayList;

import static common.Constants.ADULT_AGE;

public class Flow {
    private ArrayList<Child> children;
    private Parser parser;
    private OutData outData;
    private Double budgetUnit;
    private ArrayList<Gift> santaGifts;
    private ArrayList<ElfVisitor> elves;

    /**
     * @param parser
     */
    public Flow(final Parser parser) {
        this.parser = parser;
    }

    /**
     *
     */
    public void checkForAdults() {
        ArrayList<Child> temp = new ArrayList<>();
        for (var c : this.children) {
            if (c.getAge() <= ADULT_AGE) {
                temp.add(c);
            }
            this.children = temp;
        }
    }

    /**
     *
     */
    public void setNiceScoreList() {
        for (var c : this.children) {
            if (c.getNiceScoreHistory() == null) {
                ArrayList<Double> niceScores = new ArrayList<>();
                niceScores.add(c.obtainNiceScore());
                c.setNiceScoreHistory(niceScores);
            } else {
                c.setNiceScoreHistory(c.getNiceScoreHistory());
            }
            c.calcAvgScore();
        }
    }

    /**
     * @param budget
     */
    public void calcBudgetUnit(final Integer budget) {
        Double avgSum = 0.0;
        ArrayList<Child> sortedChildren = new ArrayList<>(this.children);
        sortedChildren.sort(new IdComparator());
        for (var c : sortedChildren) {  // trebuie sa fie copiii cu id-urile in ordine
            c.calcAvgScore();
            avgSum += c.getAverageScore();
        }
        this.budgetUnit = budget / avgSum;
    }

    /**
     *
     */
    public void calcAssignedBudget() {
        for (var c : this.children) {
            c.calcAvgScore();
            c.setAssignedBudget(this.budgetUnit * c.getAverageScore());
        }
    }

    /**
     * @param santaGifts
     */
    public void giftsForKids(final ArrayList<Gift> santaGifts) {
        for (var c : this.children) {
            ArrayList<Gift> allPreferences = new ArrayList<>();
            Double kidBudget = c.getAssignedBudget();
            for (var p : c.getGiftsPreferences()) {
                Gift bestGift = null;
                for (var g : santaGifts) {
                    if (p.equals(g.getCategory()) && g.obtainQuantity() > 0
                            && g.getPrice() <= kidBudget
                            && (bestGift == null || bestGift.getPrice() > g.getPrice())) {
                        bestGift = g;
                    }
                }
                if (bestGift != null) {
                    allPreferences.add(bestGift);
                    bestGift.setQuantity(bestGift.obtainQuantity() - 1);
                    kidBudget -= bestGift.getPrice();
                }
            }
            c.setReceivedGifts(allPreferences);
        }
    }

    /**
     * @param year
     */
    public void updateNewChildren(final AnnualChanges year) {
        this.children.addAll(year.getNewChildren());
    }

    /**
     * @param year
     */
    public void updateGifts(final AnnualChanges year) {
        this.santaGifts.addAll(year.getNewGifts());
    }

    /**
     * @param year
     */
    public void notifyChildren(final AnnualChanges year) {
        for (var c : this.children) {
            ArrayList<Child> updChildren = year.getChildrenUpdates();
            for (var upc : updChildren) {
                if (upc.getId().equals(c.getId())) {
                    c.update(upc);  // Observer
                }
            }
        }
    }

    /**
     *
     */
    public void elfBudget() {
        if (this.elves == null) {
            this.elves = new ArrayList<>();
            this.elves.add(new ElfWhite());
            this.elves.add(new ElfBlack());
            this.elves.add(new ElfPink());
        }
        for (var c : this.children) {
            for (var e : this.elves) {
                c.acceptElf(e);
            }
        }
    }

    /**
     * @param strategy
     */
    public void strategyChoice(final String strategy) {
        ArrayList<Child> sortedChildren = new ArrayList<>(this.children);
        if (strategy.equals("id")) {
            sortedChildren.sort(new IdComparator());
        } else if (strategy.equals("niceScore")) {
            sortedChildren.sort(new AvgScoreComparator().thenComparing(new IdComparator()));
        } else if (strategy.equals("niceScoreCity")) {
            sortedChildren.sort(new CityComparator(this.children).
                    thenComparing(new CityNameComparator()).thenComparing(new IdComparator()));
        }
        this.children = sortedChildren;
    }

    /**
     *
     */
    public void roundZero() {
        Years yearOne = new Years();
        ArrayList<Child> outChildren = new ArrayList<>();
        this.children = this.parser.getInitialData().getChildren();
        this.santaGifts = this.parser.getInitialData().getSantaGiftsList();
        checkForAdults();  // eliminates 18+ people
        setNiceScoreList(); // initializes the niceScoreHistory for the children
        calcBudgetUnit(this.parser.getSantaBudget());
        calcAssignedBudget();  // calculates the assigned budget for each kid
        elfBudget();  // adjusted budget based on elfType
        strategyChoice("id");  // first year - idComparator
        giftsForKids(this.santaGifts);  // gives children their gifts
        ElfVisitor yellow = new ElfYellow(this.santaGifts);
        for (var c: this.children) {
            c.acceptElf(yellow);  // visitor here
            outChildren.add(c.copyChild());
        }
        yearOne.setChildren(outChildren);
        this.outData = new OutData();
        this.outData.update(yearOne);
    }

    /**
     *
     */
    public void annualRounds() {
        ArrayList<AnnualChanges> years = this.parser.getAnnualChanges();
        for (int i = 0; i < this.parser.getNumberOfYears(); i++) {
            var y = years.get(i);
            Years addYear = new Years();
            ArrayList<Child> outChildren = new ArrayList<>();  // aici se vor scrie copiii pt output
            for (var c: this.children) {
                c.updateAge();
            }
            updateNewChildren(y);
            checkForAdults();
            notifyChildren(y);
            setNiceScoreList();
            calcBudgetUnit(y.getNewSantaBudget());
            calcAssignedBudget();
            elfBudget();
            updateGifts(y);  // add to the previous gifts new ones from the y year
            strategyChoice(y.getStrategy());
            giftsForKids(this.santaGifts);
            ElfVisitor yellow = new ElfYellow(this.santaGifts);
            for (var c: this.children) {
                c.acceptElf(yellow);
                outChildren.add(c.copyChild());
            }
            addYear.setChildren(outChildren);
            this.outData.update(addYear);
        }
    }

    /**
     * @return
     */
    public OutData getOutData() {
        return outData;
    }

    /**
     * @return
     */
    public ArrayList<Child> getChildren() {
        return children;
    }
}
