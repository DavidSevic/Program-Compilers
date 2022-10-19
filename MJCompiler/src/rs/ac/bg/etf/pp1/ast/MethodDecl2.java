// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl2 extends MethodDecl {

    private VoidIdentMeth VoidIdentMeth;
    private FormParsOption FormParsOption;
    private VarDeclOption VarDeclOption;
    private StatementList StatementList;

    public MethodDecl2 (VoidIdentMeth VoidIdentMeth, FormParsOption FormParsOption, VarDeclOption VarDeclOption, StatementList StatementList) {
        this.VoidIdentMeth=VoidIdentMeth;
        if(VoidIdentMeth!=null) VoidIdentMeth.setParent(this);
        this.FormParsOption=FormParsOption;
        if(FormParsOption!=null) FormParsOption.setParent(this);
        this.VarDeclOption=VarDeclOption;
        if(VarDeclOption!=null) VarDeclOption.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public VoidIdentMeth getVoidIdentMeth() {
        return VoidIdentMeth;
    }

    public void setVoidIdentMeth(VoidIdentMeth VoidIdentMeth) {
        this.VoidIdentMeth=VoidIdentMeth;
    }

    public FormParsOption getFormParsOption() {
        return FormParsOption;
    }

    public void setFormParsOption(FormParsOption FormParsOption) {
        this.FormParsOption=FormParsOption;
    }

    public VarDeclOption getVarDeclOption() {
        return VarDeclOption;
    }

    public void setVarDeclOption(VarDeclOption VarDeclOption) {
        this.VarDeclOption=VarDeclOption;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VoidIdentMeth!=null) VoidIdentMeth.accept(visitor);
        if(FormParsOption!=null) FormParsOption.accept(visitor);
        if(VarDeclOption!=null) VarDeclOption.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VoidIdentMeth!=null) VoidIdentMeth.traverseTopDown(visitor);
        if(FormParsOption!=null) FormParsOption.traverseTopDown(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VoidIdentMeth!=null) VoidIdentMeth.traverseBottomUp(visitor);
        if(FormParsOption!=null) FormParsOption.traverseBottomUp(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl2(\n");

        if(VoidIdentMeth!=null)
            buffer.append(VoidIdentMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsOption!=null)
            buffer.append(FormParsOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOption!=null)
            buffer.append(VarDeclOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl2]");
        return buffer.toString();
    }
}
