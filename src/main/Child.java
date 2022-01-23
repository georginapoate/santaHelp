package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static common.Constants.KID_AGE;
import static common.Constants.TEEN_AGE;
import static common.Constants.ADULT_AGE;
import static common.Constants.PERCENT;
import static common.Constants.HIGHEST_SCORE;

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


    // adding these fields for output:
    private Double averageScore;

    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    /**
     * @return kid elf
     */
    public String obtainElf() {
        return elf;
    }  // changed name so it doesn't show at output (bcs getter)

    /**
     * @param elf
     */
    public void setElf(final String elf) {
        this.elf = elf;
    }

    /**
     * @return child's ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(final Integer age) {
        this.age = age;
    }

    /**
     * @return
     */
    public Double obtainNiceScore() {
        return niceScore;
    }

    /**
     * @param niceScore
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * @return
     */
    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * @param giftsPreferences
     */
    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * @return
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * @param averageScore
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * @return ScoreHistory
     */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * @param niceScoreHistory
     */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * @return niceBonus
     */
    public Integer obtainNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * @param niceScoreBonus
     */
    public void setNiceScoreBonus(final Integer niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    /**
     * @return
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * @param assignedBudget
     */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * @return
     */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * @param receivedGifts
     */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * @return a copy of a child, used for the output
     */
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

    /**
     * removes repeating giftPreferences for a child
     */
    public void removeDups() {
        ArrayList<String> gifts = new ArrayList<>();
        for (var gp : this.giftsPreferences) {
            if (!gifts.contains(gp)) {
                gifts.add(gp);
            }
        }
        this.giftsPreferences = gifts;
    }

    /**
     * calculates average score of child based on the niceScoreHistory (and also niceBonus)
     */
    public void calcAvgScore() {
        if (this.age < KID_AGE) {
            this.averageScore = HIGHEST_SCORE;
        } else if (this.age >= KID_AGE && this.age < TEEN_AGE) {
            Double avg = 0.0;
            for (var a : this.getNiceScoreHistory()) {
                avg += a;
            }
            this.averageScore = avg / this.getNiceScoreHistory().size();
        } else if (this.age >= TEEN_AGE && this.age <= ADULT_AGE) {
            Double avg = 0.0;
            Integer sum = 0;
            for (int i = 0; i < this.getNiceScoreHistory().size(); i++) {
                avg += (this.getNiceScoreHistory().get(i) * (i + 1));
                sum += i + 1;
            }
            this.averageScore = avg / sum;
        }
        this.setAverageScore(this.getAverageScore() + this.getAverageScore()
                * this.obtainNiceScoreBonus() / PERCENT);
        if (this.getAverageScore() > HIGHEST_SCORE) {
            this.setAverageScore(HIGHEST_SCORE);
        }
    }

    // Visitor:

    /**
     * @param elf visitor
     */
    public void acceptElf(final ElfVisitor elf) {
        elf.visit(this);
    }

    /**
     *
     */
    public void updateAge() {
        this.age += 1;
    }

    /**
     * @param updatedChild child with yearly update
     */
    public void update(final Child updatedChild) {
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

class IdComparator implements Comparator<Child> {
    @Override
    public int compare(final Child o1, final Child o2) {
        return o1.getId().compareTo(o2.getId());
    }
}

class AvgScoreComparator implements Comparator<Child> {

    @Override
    public int compare(final Child o1, final Child o2) {
        return o2.getAverageScore().compareTo(o1.getAverageScore());
    }
}

class CityComparator implements Comparator<Child> {
    private HashMap<String, Double> cityScore;
    CityComparator(final ArrayList<Child> ch) {
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
        for (var entry : cityScore.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            cityScore.put(key, value / nrKidsInCity.get(key));
        }
    }

    @Override
    public int compare(final Child o1, final Child o2) {
        return cityScore.get(o2.getCity()).compareTo(cityScore.get(o1.getCity()));
    }
}

class CityNameComparator implements Comparator<Child> {

    @Override
    public int compare(final Child o1, final Child o2) {
        return o1.getCity().compareTo(o2.getCity());
    }
}
