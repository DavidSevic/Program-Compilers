// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class DesignatorList2 extends DesignatorList {

    private DesignatorList DesignatorList;
    private LSQRDhelp LSQRDhelp;
    private Expr Expr;

    public DesignatorList2 (DesignatorList DesignatorList, LSQRDhelp LSQRDhelp, Expr Expr) {
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.LSQRDhelp=LSQRDhelp;
        if(LSQRDhelp!=null) LSQRDhelp.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public LSQRDhelp getLSQRDhelp() {
        return LSQRDhelp;
    }

    public void setLSQRDhelp(LSQRDhelp LSQRDhelp) {
        this.LSQRDhelp=LSQRDhelp;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
        if(LSQRDhelp!=null) LSQRDhelp.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
        if(LSQRDhelp!=null) LSQRDhelp.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        if(LSQRDhelp!=null) LSQRDhelp.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorList2(\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LSQRDhelp!=null)
            buffer.append(LSQRDhelp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorList2]");
        return buffer.toString();
    }
}
