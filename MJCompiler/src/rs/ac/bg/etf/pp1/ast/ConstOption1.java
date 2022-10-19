// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:43


package rs.ac.bg.etf.pp1.ast;

public class ConstOption1 extends ConstOption {

    private Integer valueInt;

    public ConstOption1 (Integer valueInt) {
        this.valueInt=valueInt;
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt=valueInt;
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
        buffer.append("ConstOption1(\n");

        buffer.append(" "+tab+valueInt);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstOption1]");
        return buffer.toString();
    }
}
