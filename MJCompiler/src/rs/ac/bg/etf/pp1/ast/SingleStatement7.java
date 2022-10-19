// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement7 extends SingleStatement {

    private RETURNhelp RETURNhelp;
    private Expr Expr;
    private SEMIhelp SEMIhelp;

    public SingleStatement7 (RETURNhelp RETURNhelp, Expr Expr, SEMIhelp SEMIhelp) {
        this.RETURNhelp=RETURNhelp;
        if(RETURNhelp!=null) RETURNhelp.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SEMIhelp=SEMIhelp;
        if(SEMIhelp!=null) SEMIhelp.setParent(this);
    }

    public RETURNhelp getRETURNhelp() {
        return RETURNhelp;
    }

    public void setRETURNhelp(RETURNhelp RETURNhelp) {
        this.RETURNhelp=RETURNhelp;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SEMIhelp getSEMIhelp() {
        return SEMIhelp;
    }

    public void setSEMIhelp(SEMIhelp SEMIhelp) {
        this.SEMIhelp=SEMIhelp;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RETURNhelp!=null) RETURNhelp.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(SEMIhelp!=null) SEMIhelp.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RETURNhelp!=null) RETURNhelp.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SEMIhelp!=null) SEMIhelp.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RETURNhelp!=null) RETURNhelp.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SEMIhelp!=null) SEMIhelp.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement7(\n");

        if(RETURNhelp!=null)
            buffer.append(RETURNhelp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SEMIhelp!=null)
            buffer.append(SEMIhelp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement7]");
        return buffer.toString();
    }
}
