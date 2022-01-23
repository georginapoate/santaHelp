package main;

import static common.Constants.OFF_PERCENT;
import static common.Constants.PERCENT;

public class ElfBlack extends ElfVisitor {
    /**
     * @param
     */
    @Override
    public void visit(final Child c) {
        if (c.obtainElf().equals("black")) {
            c.setAssignedBudget(c.getAssignedBudget() - c.getAssignedBudget()
                    * OFF_PERCENT / PERCENT);
        }
    }
}
