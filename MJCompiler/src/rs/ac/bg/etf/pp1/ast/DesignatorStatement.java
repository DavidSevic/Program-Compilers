// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatement implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Designator Designator;
    private DesignatorStmtOptions DesignatorStmtOptions;

    public DesignatorStatement (Designator Designator, DesignatorStmtOptions DesignatorStmtOptions) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorStmtOptions=DesignatorStmtOptions;
        if(DesignatorStmtOptions!=null) DesignatorStmtOptions.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorStmtOptions getDesignatorStmtOptions() {
        return DesignatorStmtOptions;
    }

    public void setDesignatorStmtOptions(DesignatorStmtOptions DesignatorStmtOptions) {
        this.DesignatorStmtOptions=DesignatorStmtOptions;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorStmtOptions!=null) DesignatorStmtOptions.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorStmtOptions!=null) DesignatorStmtOptions.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorStmtOptions!=null) DesignatorStmtOptions.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatement(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtOptions!=null)
            buffer.append(DesignatorStmtOptions.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatement]");
        return buffer.toString();
    }
}
