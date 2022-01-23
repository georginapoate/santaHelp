package main;

import java.util.ArrayList;

public class ElfYellow extends ElfVisitor {
    private ArrayList<Gift> gifts;

    /**
     * @param gifts
     */
    ElfYellow(final ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    /**
     * @param c
     */
    @Override
    public void visit(final Child c) {
        if (c.obtainElf().equals("yellow")) {
            String prefGift = c.getGiftsPreferences().get(0);
            if (c.getReceivedGifts().isEmpty()) {
                Gift best = null;
                for (var g : this.gifts) {
                    if (g.getCategory().equals(prefGift)
                            && (best == null || best.getPrice() > g.getPrice())) {
                        best = g;
                    }
                }
                if (best != null && best.obtainQuantity() > 0) {
                    best.setQuantity(best.obtainQuantity() - 1);
                    c.getReceivedGifts().add(best);
                }
            }
        }
    }
}
