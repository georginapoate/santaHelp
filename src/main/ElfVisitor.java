package main;
import enums.ElvesType;

public abstract class ElfVisitor {
    public void visit(Child c) {
        System.out.printf("abstract");
    }
}