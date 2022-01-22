package main;

import java.util.ArrayList;

public class Flow {
    private ArrayList<Child> children;
    private Parser parser;
    private OutData outData ;
    private Double budgetUnit;
    private ArrayList<Gift> santaGifts;
    private ArrayList<ElfVisitor> elves;

    public Flow(Parser parser) {
        this.parser = parser;
    }

    public void checkForAdults() {
        ArrayList<Child> temp = new ArrayList<>();
        for (var c : this.children) {
            if (c.getAge() <= 18) {
                temp.add(c);
            }
            this.children = temp;
        }
    }

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

    public void calcBudgetUnit(Integer budget) {
        Double avgSum = 0.0;
        ArrayList<Child> sortedChildren = new ArrayList<>(this.children);
        sortedChildren.sort(new idComparator());
        for (var c : sortedChildren) {  // trebuie sa fie copiii cu id-urile in ordine
            c.calcAvgScore();
            avgSum += c.getAverageScore();
        }
        this.budgetUnit = budget / avgSum;
    }

    public void calcAssignedBudget() {
        for (var c : this.children) {
            c.calcAvgScore();
            c.setAssignedBudget(this.budgetUnit * c.getAverageScore());
        }
    }

    public void giftsForKids(ArrayList<Gift> santaGifts) {
        for (var c : this.children) {
            ArrayList<Gift> allPreferences = new ArrayList<>();
            Double kidBudget = c.getAssignedBudget();
            for (var p : c.getGiftsPreferences()) {
                Gift bestGift = null;
                for (var g : santaGifts) {
                    if (p.equals(g.getCategory()) && g.obtainQuantity() > 0 && g.getPrice() <= kidBudget &&
                            (bestGift == null || bestGift.getPrice() > g.getPrice())) {
                        bestGift = g;
                    }
                }
                System.out.println(c.getFirstName() + " " + kidBudget);
                if (bestGift != null) {
                    allPreferences.add(bestGift);
                    bestGift.setQuantity(bestGift.obtainQuantity()-1);
                    kidBudget -= bestGift.getPrice();
                    System.out.println( bestGift.getProductName() + " " + bestGift.obtainQuantity());
                }
            }
            c.setReceivedGifts(allPreferences);
        }
    }

    public void updateNewChildren(AnnualChanges year) {
        this.children.addAll(year.getNewChildren());
    }

    public void updateGifts(AnnualChanges year) {
        this.santaGifts.addAll(year.getNewGifts());
    }

    public void notifyChildren(AnnualChanges year) {
        for (var c : this.children) {
            ArrayList<Child> updChildren = year.getChildrenUpdates();
            for (var upc : updChildren) {
                if (upc.getId().equals(c.getId())) {
                    c.update(upc);
                }
            }
        }
    }

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

    public void strategyChoice(String strategy) {
        ArrayList<Child> sortedChildren = new ArrayList<>(this.children);
        if (strategy.equals("id")) {
            sortedChildren.sort(new idComparator());
        } else if (strategy.equals("niceScore")) {
            sortedChildren.sort(new avgScoreComparator().thenComparing(new idComparator()));
        } else if (strategy.equals("niceScoreCity")) {
            sortedChildren.sort(new cityComparator(this.children).thenComparing(new CityNameComparator()).thenComparing(new idComparator()));
        }
        this.children = sortedChildren;
    }

    public void roundZero() {
        Years yearOne = new Years();
        ArrayList<Child> outChildren = new ArrayList<>();
        this.children = this.parser.getInitialData().getChildren();
        this.santaGifts = this.parser.getInitialData().getSantaGiftsList();
        checkForAdults();
        setNiceScoreList();
        calcBudgetUnit(this.parser.getSantaBudget());
        calcAssignedBudget();
        elfBudget();
        strategyChoice("id");  // pentru primul an, strategia e ID-ul copilului
        giftsForKids(this.santaGifts);
        ElfVisitor yellow = new ElfYellow(this.santaGifts);
        for (var c: this.children) {
            c.acceptElf(yellow);
            outChildren.add(c.copyChild());
        }
        yearOne.setChildren(outChildren);
        System.out.println("___roundzero___");
        this.outData = new OutData();
        this.outData.update(yearOne);
    }

    public void annualRounds() {
        ArrayList<AnnualChanges> years = this.parser.getAnnualChanges();
        for (int i = 0; i < this.parser.getNumberOfYears(); i++) {
            System.out.println("---anul " + (i + 1) + " ---");
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
            updateGifts(y);
            strategyChoice(y.getStrategy());
//            for (var g : this.santaGifts) {
//                System.out.println("before:" + g.getProductName() + g.obtainQuantity());
//            }
            giftsForKids(this.santaGifts);
//            for (var g : this.santaGifts) {
//                System.out.println("after:" + g.getProductName() + g.obtainQuantity());
//            }
            ElfVisitor yellow = new ElfYellow(this.santaGifts);
            for (var c: this.children) {
                c.acceptElf(yellow);
                outChildren.add(c.copyChild());
            }
            addYear.setChildren(outChildren);
            this.outData.update(addYear);
        }
    }

    public OutData getOutData() {
        return outData;
    }

    public void setOutData(OutData outData) {
        this.outData = outData;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }
}
