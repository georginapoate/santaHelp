package main;

public class ElfWhite extends ElfVisitor {
    @Override
    public void visit(Child c) {
        if (c.obtainElf().equals("white")) {
            c.setAssignedBudget(c.getAssignedBudget());
        }
    }
}