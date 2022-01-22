package main;

import java.util.ArrayList;

public class ElfYellow extends ElfVisitor {
    private ArrayList<Gift> gifts;

    ElfYellow(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }
    @Override
    public void visit(Child c) {
        if (c.obtainElf().equals("yellow")) {
            String prefGift = c.getGiftsPreferences().get(0);
            if (c.getReceivedGifts().isEmpty()) {
                Gift best = null;
                for (var g : this.gifts) {
                    if (g.getCategory().equals(prefGift) && g.obtainQuantity() > 0
                            && (best == null || best.getPrice() > g.getPrice())) {
                        best = g;
                    }
                }
                if (best != null) {
                    System.out.println("yellow" + c.getFirstName() + " " + best.getProductName());
                    best.setQuantity(best.obtainQuantity()-1);
                    c.getReceivedGifts().add(best);
                }
            }
        }
    }
}