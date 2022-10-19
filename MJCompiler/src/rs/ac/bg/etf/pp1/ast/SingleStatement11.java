// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement11 extends SingleStatement {

    private LabelCheck LabelCheck;

    public SingleStatement11 (LabelCheck LabelCheck) {
        this.LabelCheck=LabelCheck;
        if(LabelCheck!=null) LabelCheck.setParent(this);
    }

    public LabelCheck getLabelCheck() {
        return LabelCheck;
    }

    public void setLabelCheck(LabelCheck LabelCheck) {
        this.LabelCheck=LabelCheck;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LabelCheck!=null) LabelCheck.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LabelCheck!=null) LabelCheck.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LabelCheck!=null) LabelCheck.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement11(\n");

        if(LabelCheck!=null)
            buffer.append(LabelCheck.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement11]");
        return buffer.toString();
    }
}
