// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:44


package rs.ac.bg.etf.pp1.ast;

public class ConstrMethodOption2 extends ConstrMethodOption {

    private ConstrDecl ConstrDecl;

    public ConstrMethodOption2 (ConstrDecl ConstrDecl) {
        this.ConstrDecl=ConstrDecl;
        if(ConstrDecl!=null) ConstrDecl.setParent(this);
    }

    public ConstrDecl getConstrDecl() {
        return ConstrDecl;
    }

    public void setConstrDecl(ConstrDecl ConstrDecl) {
        this.ConstrDecl=ConstrDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstrDecl!=null) ConstrDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstrDecl!=null) ConstrDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstrDecl!=null) ConstrDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstrMethodOption2(\n");

        if(ConstrDecl!=null)
            buffer.append(ConstrDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstrMethodOption2]");
        return buffer.toString();
    }
}
