// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class RecordDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private RecordIdent RecordIdent;
    private VarDeclOption VarDeclOption;

    public RecordDecl (RecordIdent RecordIdent, VarDeclOption VarDeclOption) {
        this.RecordIdent=RecordIdent;
        if(RecordIdent!=null) RecordIdent.setParent(this);
        this.VarDeclOption=VarDeclOption;
        if(VarDeclOption!=null) VarDeclOption.setParent(this);
    }

    public RecordIdent getRecordIdent() {
        return RecordIdent;
    }

    public void setRecordIdent(RecordIdent RecordIdent) {
        this.RecordIdent=RecordIdent;
    }

    public VarDeclOption getVarDeclOption() {
        return VarDeclOption;
    }

    public void setVarDeclOption(VarDeclOption VarDeclOption) {
        this.VarDeclOption=VarDeclOption;
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
        if(RecordIdent!=null) RecordIdent.accept(visitor);
        if(VarDeclOption!=null) VarDeclOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordIdent!=null) RecordIdent.traverseTopDown(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordIdent!=null) RecordIdent.traverseBottomUp(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordDecl(\n");

        if(RecordIdent!=null)
            buffer.append(RecordIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOption!=null)
            buffer.append(VarDeclOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordDecl]");
        return buffer.toString();
    }
}
