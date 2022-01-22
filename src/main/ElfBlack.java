package main;

public class ElfBlack extends ElfVisitor {
    @Override
    public void visit(Child c) {
        if (c.obtainElf().equals("black")) {
            c.setAssignedBudget(c.getAssignedBudget() - c.getAssignedBudget() * 30 / 100);
        }
    }
}