// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class ConstrMethodOption4 extends ConstrMethodOption {

    private ConstrDecl ConstrDecl;
    private MethodDecl MethodDecl;
    private MethodDeclList MethodDeclList;

    public ConstrMethodOption4 (ConstrDecl ConstrDecl, MethodDecl MethodDecl, MethodDeclList MethodDeclList) {
        this.ConstrDecl=ConstrDecl;
        if(ConstrDecl!=null) ConstrDecl.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public ConstrDecl getConstrDecl() {
        return ConstrDecl;
    }

    public void setConstrDecl(ConstrDecl ConstrDecl) {
        this.ConstrDecl=ConstrDecl;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstrDecl!=null) ConstrDecl.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstrDecl!=null) ConstrDecl.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstrDecl!=null) ConstrDecl.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstrMethodOption4(\n");

        if(ConstrDecl!=null)
            buffer.append(ConstrDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstrMethodOption4]");
        return buffer.toString();
    }
}
