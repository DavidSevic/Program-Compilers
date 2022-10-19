// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class Factor7 extends Factor {

    private Type Type;
    private LSQRDhelp1 LSQRDhelp1;
    private Expr Expr;

    public Factor7 (Type Type, LSQRDhelp1 LSQRDhelp1, Expr Expr) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.LSQRDhelp1=LSQRDhelp1;
        if(LSQRDhelp1!=null) LSQRDhelp1.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public LSQRDhelp1 getLSQRDhelp1() {
        return LSQRDhelp1;
    }

    public void setLSQRDhelp1(LSQRDhelp1 LSQRDhelp1) {
        this.LSQRDhelp1=LSQRDhelp1;
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
        if(Type!=null) Type.accept(visitor);
        if(LSQRDhelp1!=null) LSQRDhelp1.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(LSQRDhelp1!=null) LSQRDhelp1.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(LSQRDhelp1!=null) LSQRDhelp1.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor7(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LSQRDhelp1!=null)
            buffer.append(LSQRDhelp1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor7]");
        return buffer.toString();
    }
}
