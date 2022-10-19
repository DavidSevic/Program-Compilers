// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:44


package rs.ac.bg.etf.pp1.ast;

public class VarDecl1 extends VarDecl {

    private Type Type;
    private String varName;
    private SqrdOptionVar SqrdOptionVar;
    private VarDeclList VarDeclList;

    public VarDecl1 (Type Type, String varName, SqrdOptionVar SqrdOptionVar, VarDeclList VarDeclList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.SqrdOptionVar=SqrdOptionVar;
        if(SqrdOptionVar!=null) SqrdOptionVar.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(SqrdOptionVar!=null) SqrdOptionVar.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SqrdOptionVar!=null) SqrdOptionVar.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SqrdOptionVar!=null) SqrdOptionVar.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl1(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SqrdOptionVar!=null)
            buffer.append(SqrdOptionVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl1]");
        return buffer.toString();
    }
}
