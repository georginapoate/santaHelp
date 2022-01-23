package main;

public class ElfWhite extends ElfVisitor {
    /**
     * @param c is the visitable child
     */
    @Override
    public void visit(final Child c) {
        if (c.obtainElf().equals("white")) {
            c.setAssignedBudget(c.getAssignedBudget());
        }
    }
}
