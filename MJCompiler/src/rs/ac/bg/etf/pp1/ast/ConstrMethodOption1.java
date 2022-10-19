// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:44


package rs.ac.bg.etf.pp1.ast;

public class ConstrMethodOption1 extends ConstrMethodOption {

    public ConstrMethodOption1 () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstrMethodOption1(\n");

        buffer.append(tab);
        buffer.append(") [ConstrMethodOption1]");
        return buffer.toString();
    }
}
