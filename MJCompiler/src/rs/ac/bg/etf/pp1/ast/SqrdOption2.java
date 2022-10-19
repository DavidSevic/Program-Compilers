// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:44


package rs.ac.bg.etf.pp1.ast;

public class SqrdOption2 extends SqrdOption {

    public SqrdOption2 () {
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
        buffer.append("SqrdOption2(\n");

        buffer.append(tab);
        buffer.append(") [SqrdOption2]");
        return buffer.toString();
    }
}
