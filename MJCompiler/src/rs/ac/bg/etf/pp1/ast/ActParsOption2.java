// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class ActParsOption2 extends ActParsOption {

    public ActParsOption2 () {
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
        buffer.append("ActParsOption2(\n");

        buffer.append(tab);
        buffer.append(") [ActParsOption2]");
        return buffer.toString();
    }
}
