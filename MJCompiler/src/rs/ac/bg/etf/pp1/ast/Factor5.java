// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class Factor5 extends Factor {

    private String valueBool;

    public Factor5 (String valueBool) {
        this.valueBool=valueBool;
    }

    public String getValueBool() {
        return valueBool;
    }

    public void setValueBool(String valueBool) {
        this.valueBool=valueBool;
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
        buffer.append("Factor5(\n");

        buffer.append(" "+tab+valueBool);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor5]");
        return buffer.toString();
    }
}
