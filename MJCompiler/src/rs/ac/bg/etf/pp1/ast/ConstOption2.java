// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:43


package rs.ac.bg.etf.pp1.ast;

public class ConstOption2 extends ConstOption {

    private String valueChar;

    public ConstOption2 (String valueChar) {
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
        buffer.append("ConstOption2(\n");

        buffer.append(" "+tab+valueChar);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstOption2]");
        return buffer.toString();
    }
}
