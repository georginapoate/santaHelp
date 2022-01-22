package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private String city;
    private Integer age;
    private Double niceScore;
    private ArrayList<String> giftsPreferences;
    private Integer niceScoreBonus;
    private String elf;


    public String obtainElf() {
        return elf;
    }  // changed name so it doesn't show at output (bcs getter)

    public void setElf(String elf) {
        this.elf = elf;
    }

    // adding these fields for output:

    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double obtainNiceScore() {
        return niceScore;
    }  // changed name from getter, so it doesn't show

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Integer obtainNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(Integer niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public Child copyChild() {
        Child copiedChild = new Child();
        copiedChild.setId(this.getId());
        copiedChild.setLastName(this.getLastName());
        copiedChild.setFirstName(this.getFirstName());
        copiedChild.setCity(this.getCity());
        copiedChild.setAge(this.getAge());
        copiedChild.setGiftsPreferences(new ArrayList<>(this.getGiftsPreferences()));
        copiedChild.setAverageScore(this.getAverageScore());
        copiedChild.setNiceScoreHistory(new ArrayList<>(this.getNiceScoreHistory()));
        copiedChild.setAssignedBudget(this.getAssignedBudget());
        copiedChild.setReceivedGifts(new ArrayList<>(this.getReceivedGifts()));
        return copiedChild;
    }

    public void removeDups() {
        ArrayList<String> gifts = new ArrayList<>();
        for (var gp : this.giftsPreferences) {
            if (!gifts.contains(gp)) {
                gifts.add(gp);
            }
        }
        this.giftsPreferences = gifts;
    }

    public void calcAvgScore() {
        if (this.age < 5) {
            this.averageScore = 10.0;
        } else if (this.age >= 5 && this.age < 12) {
            Double avg = 0.0;
            for (var a : this.getNiceScoreHistory()) {
                avg += a;
            }
            this.averageScore = avg / this.getNiceScoreHistory().size();
        } else if (this.age >= 12 && this.age <= 18) {
            Double avg = 0.0;
            Integer sum = 0;
            for (int i = 0; i < this.getNiceScoreHistory().size(); i++) {
                avg += (this.getNiceScoreHistory().get(i) * (i + 1));
                sum += i + 1;
            }
            this.averageScore = avg / sum;
        }
        this.setAverageScore(this.getAverageScore() + this.getAverageScore() * this.obtainNiceScoreBonus() / 100);
        if (this.getAverageScore() > 10) {
            this.setAverageScore(10.0);
        }
    }

    // Visitor:

    public void acceptElf(ElfVisitor elf) {
        elf.visit(this);
    }

    public void updateAge() {
        this.age += 1;
    }

    public void update(Child updatedChild) {
        if (updatedChild.obtainNiceScore() != null) {
            this.niceScoreHistory.add(updatedChild.obtainNiceScore());
        }
        for (var g : getGiftsPreferences()) {
            if (!updatedChild.getGiftsPreferences().contains(g)) {
                updatedChild.getGiftsPreferences().add(g);
            }
            this.setGiftsPreferences(updatedChild.getGiftsPreferences());
            removeDups();
        }
        this.elf = updatedChild.obtainElf();
        calcAvgScore();
    }
}

class idComparator implements Comparator<Child> {
    @Override
    public int compare(final Child o1, final Child o2) {
        return o1.getId().compareTo(o2.getId());
    }
}

class avgScoreComparator implements Comparator<Child> {

    @Override
    public int compare(final Child o1, final Child o2) {
        return o2.getAverageScore().compareTo(o1.getAverageScore());
    }
}

class cityComparator implements Comparator<Child> {
    private HashMap<String, Double> cityScore;
    cityComparator(ArrayList<Child> ch) {
        HashMap<String, Integer> nrKidsInCity = new HashMap<>();
        cityScore = new HashMap<>();
        for (var c : ch) {
            if (cityScore.containsKey(c.getCity())) {
                cityScore.put(c.getCity(), c.getAverageScore() + cityScore.get(c.getCity()));
                nrKidsInCity.put(c.getCity(), 1 + nrKidsInCity.get(c.getCity()));
            } else {
                cityScore.put(c.getCity(), c.getAverageScore());
                nrKidsInCity.put(c.getCity(), 1);
            }
        }
        for(var entry : cityScore.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            cityScore.put(key, value / nrKidsInCity.get(key));
        }
    }

    @Override
    public int compare(Child o1, Child o2) {
        return cityScore.get(o2.getCity()).compareTo(cityScore.get(o1.getCity()));
    }
}

class CityNameComparator implements Comparator<Child> {

    @Override
    public int compare(Child o1, Child o2) {
        return o1.getCity().compareTo(o2.getCity());
    }
}