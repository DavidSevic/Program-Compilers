// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:44


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String varName;
    private ExtendsOption ExtendsOption;
    private VarDeclOption VarDeclOption;
    private ConstrMethodOption ConstrMethodOption;

    public ClassDecl (String varName, ExtendsOption ExtendsOption, VarDeclOption VarDeclOption, ConstrMethodOption ConstrMethodOption) {
        this.varName=varName;
        this.ExtendsOption=ExtendsOption;
        if(ExtendsOption!=null) ExtendsOption.setParent(this);
        this.VarDeclOption=VarDeclOption;
        if(VarDeclOption!=null) VarDeclOption.setParent(this);
        this.ConstrMethodOption=ConstrMethodOption;
        if(ConstrMethodOption!=null) ConstrMethodOption.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ExtendsOption getExtendsOption() {
        return ExtendsOption;
    }

    public void setExtendsOption(ExtendsOption ExtendsOption) {
        this.ExtendsOption=ExtendsOption;
    }

    public VarDeclOption getVarDeclOption() {
        return VarDeclOption;
    }

    public void setVarDeclOption(VarDeclOption VarDeclOption) {
        this.VarDeclOption=VarDeclOption;
    }

    public ConstrMethodOption getConstrMethodOption() {
        return ConstrMethodOption;
    }

    public void setConstrMethodOption(ConstrMethodOption ConstrMethodOption) {
        this.ConstrMethodOption=ConstrMethodOption;
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
        if(ExtendsOption!=null) ExtendsOption.accept(visitor);
        if(VarDeclOption!=null) VarDeclOption.accept(visitor);
        if(ConstrMethodOption!=null) ConstrMethodOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsOption!=null) ExtendsOption.traverseTopDown(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseTopDown(visitor);
        if(ConstrMethodOption!=null) ConstrMethodOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsOption!=null) ExtendsOption.traverseBottomUp(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseBottomUp(visitor);
        if(ConstrMethodOption!=null) ConstrMethodOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ExtendsOption!=null)
            buffer.append(ExtendsOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOption!=null)
            buffer.append(VarDeclOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstrMethodOption!=null)
            buffer.append(ConstrMethodOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
