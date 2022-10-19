// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:44


package rs.ac.bg.etf.pp1.ast;

public class GlobVarDeclList1 extends GlobVarDeclList {

    private String varName;
    private SqrdOptionVar SqrdOptionVar;
    private GlobVarDeclList GlobVarDeclList;

    public GlobVarDeclList1 (String varName, SqrdOptionVar SqrdOptionVar, GlobVarDeclList GlobVarDeclList) {
        this.varName=varName;
        this.SqrdOptionVar=SqrdOptionVar;
        if(SqrdOptionVar!=null) SqrdOptionVar.setParent(this);
        this.GlobVarDeclList=GlobVarDeclList;
        if(GlobVarDeclList!=null) GlobVarDeclList.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public SqrdOptionVar getSqrdOptionVar() {
        return SqrdOptionVar;
    }

    public void setSqrdOptionVar(SqrdOptionVar SqrdOptionVar) {
        this.SqrdOptionVar=SqrdOptionVar;
    }

    public GlobVarDeclList getGlobVarDeclList() {
        return GlobVarDeclList;
    }

    public void setGlobVarDeclList(GlobVarDeclList GlobVarDeclList) {
        this.GlobVarDeclList=GlobVarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SqrdOptionVar!=null) SqrdOptionVar.accept(visitor);
        if(GlobVarDeclList!=null) GlobVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SqrdOptionVar!=null) SqrdOptionVar.traverseTopDown(visitor);
        if(GlobVarDeclList!=null) GlobVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SqrdOptionVar!=null) SqrdOptionVar.traverseBottomUp(visitor);
        if(GlobVarDeclList!=null) GlobVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobVarDeclList1(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SqrdOptionVar!=null)
            buffer.append(SqrdOptionVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobVarDeclList!=null)
            buffer.append(GlobVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobVarDeclList1]");
        return buffer.toString();
    }
}
