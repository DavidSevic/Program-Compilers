// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement2 extends SingleStatement {

    private Condition Condition;
    private Statement Statement;
    private ElseStmtOption ElseStmtOption;

    public SingleStatement2 (Condition Condition, Statement Statement, ElseStmtOption ElseStmtOption) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseStmtOption=ElseStmtOption;
        if(ElseStmtOption!=null) ElseStmtOption.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ElseStmtOption getElseStmtOption() {
        return ElseStmtOption;
    }

    public void setElseStmtOption(ElseStmtOption ElseStmtOption) {
        this.ElseStmtOption=ElseStmtOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseStmtOption!=null) ElseStmtOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseStmtOption!=null) ElseStmtOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseStmtOption!=null) ElseStmtOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement2(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseStmtOption!=null)
            buffer.append(ElseStmtOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement2]");
        return buffer.toString();
    }
}
