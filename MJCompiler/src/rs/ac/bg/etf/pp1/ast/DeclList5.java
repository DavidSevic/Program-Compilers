// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:43


package rs.ac.bg.etf.pp1.ast;

public class DeclList5 extends DeclList {

    public DeclList5 () {
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
        buffer.append("DeclList5(\n");

        buffer.append(tab);
        buffer.append(") [DeclList5]");
        return buffer.toString();
    }
}
