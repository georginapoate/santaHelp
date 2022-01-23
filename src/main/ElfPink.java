package main;

import static common.Constants.OFF_PERCENT;
import static common.Constants.PERCENT;

public class ElfPink extends ElfVisitor {
    /**
     * @param c
     */
    @Override
    public void visit(final Child c) {
        if (c.obtainElf().equals("pink")) {
            c.setAssignedBudget(c.getAssignedBudget() + (c.getAssignedBudget()
                    * OFF_PERCENT) / PERCENT);
        }
    }
}
