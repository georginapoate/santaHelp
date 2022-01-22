package main;

public class ElfPink extends ElfVisitor {
    @Override
    public void visit(Child c) {
        if (c.obtainElf().equals("pink")) {
            c.setAssignedBudget(c.getAssignedBudget() + (c.getAssignedBudget() * 30) / 100);
        }
    }
}
