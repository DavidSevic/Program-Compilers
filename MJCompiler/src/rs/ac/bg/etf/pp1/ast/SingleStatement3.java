// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement3 extends SingleStatement {

    private DOeasy DOeasy;
    private Statement Statement;
    private WHILEeasy WHILEeasy;
    private Condition Condition;

    public SingleStatement3 (DOeasy DOeasy, Statement Statement, WHILEeasy WHILEeasy, Condition Condition) {
        this.DOeasy=DOeasy;
        if(DOeasy!=null) DOeasy.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.WHILEeasy=WHILEeasy;
        if(WHILEeasy!=null) WHILEeasy.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
    }

    public DOeasy getDOeasy() {
        return DOeasy;
    }

    public void setDOeasy(DOeasy DOeasy) {
        this.DOeasy=DOeasy;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public WHILEeasy getWHILEeasy() {
        return WHILEeasy;
    }

    public void setWHILEeasy(WHILEeasy WHILEeasy) {
        this.WHILEeasy=WHILEeasy;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DOeasy!=null) DOeasy.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(WHILEeasy!=null) WHILEeasy.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DOeasy!=null) DOeasy.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(WHILEeasy!=null) WHILEeasy.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DOeasy!=null) DOeasy.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(WHILEeasy!=null) WHILEeasy.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement3(\n");

        if(DOeasy!=null)
            buffer.append(DOeasy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WHILEeasy!=null)
            buffer.append(WHILEeasy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement3]");
        return buffer.toString();
    }
}
