// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class Statement1 extends Statement {

    private WithLabel WithLabel;
    private SingleStatement SingleStatement;

    public Statement1 (WithLabel WithLabel, SingleStatement SingleStatement) {
        this.WithLabel=WithLabel;
        if(WithLabel!=null) WithLabel.setParent(this);
        this.SingleStatement=SingleStatement;
        if(SingleStatement!=null) SingleStatement.setParent(this);
    }

    public WithLabel getWithLabel() {
        return WithLabel;
    }

    public void setWithLabel(WithLabel WithLabel) {
        this.WithLabel=WithLabel;
    }

    public SingleStatement getSingleStatement() {
        return SingleStatement;
    }

    public void setSingleStatement(SingleStatement SingleStatement) {
        this.SingleStatement=SingleStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WithLabel!=null) WithLabel.accept(visitor);
        if(SingleStatement!=null) SingleStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WithLabel!=null) WithLabel.traverseTopDown(visitor);
        if(SingleStatement!=null) SingleStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WithLabel!=null) WithLabel.traverseBottomUp(visitor);
        if(SingleStatement!=null) SingleStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement1(\n");

        if(WithLabel!=null)
            buffer.append(WithLabel.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleStatement!=null)
            buffer.append(SingleStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement1]");
        return buffer.toString();
    }
}
