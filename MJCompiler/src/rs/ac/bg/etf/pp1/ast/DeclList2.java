// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:43


package rs.ac.bg.etf.pp1.ast;

public class DeclList2 extends DeclList {

    private DeclList DeclList;
    private GlobVarDecl GlobVarDecl;

    public DeclList2 (DeclList DeclList, GlobVarDecl GlobVarDecl) {
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
        this.GlobVarDecl=GlobVarDecl;
        if(GlobVarDecl!=null) GlobVarDecl.setParent(this);
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public GlobVarDecl getGlobVarDecl() {
        return GlobVarDecl;
    }

    public void setGlobVarDecl(GlobVarDecl GlobVarDecl) {
        this.GlobVarDecl=GlobVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclList!=null) DeclList.accept(visitor);
        if(GlobVarDecl!=null) GlobVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
        if(GlobVarDecl!=null) GlobVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        if(GlobVarDecl!=null) GlobVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclList2(\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobVarDecl!=null)
            buffer.append(GlobVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclList2]");
        return buffer.toString();
    }
}
