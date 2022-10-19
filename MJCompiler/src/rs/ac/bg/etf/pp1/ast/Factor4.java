// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class Factor4 extends Factor {

    private String valueChar;

    public Factor4 (String valueChar) {
        this.valueChar=valueChar;
    }

    public String getValueChar() {
        return valueChar;
    }

    public void setValueChar(String valueChar) {
        this.valueChar=valueChar;
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
        buffer.append("Factor4(\n");

        buffer.append(" "+tab+valueChar);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor4]");
        return buffer.toString();
    }
}
