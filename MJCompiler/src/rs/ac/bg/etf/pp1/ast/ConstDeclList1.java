// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:44


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclList1 extends ConstDeclList {

    private String varName;
    private ConstOption ConstOption;
    private ConstDeclList ConstDeclList;

    public ConstDeclList1 (String varName, ConstOption ConstOption, ConstDeclList ConstDeclList) {
        this.varName=varName;
        this.ConstOption=ConstOption;
        if(ConstOption!=null) ConstOption.setParent(this);
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ConstOption getConstOption() {
        return ConstOption;
    }

    public void setConstOption(ConstOption ConstOption) {
        this.ConstOption=ConstOption;
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstOption!=null) ConstOption.accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstOption!=null) ConstOption.traverseTopDown(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstOption!=null) ConstOption.traverseBottomUp(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclList1(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ConstOption!=null)
            buffer.append(ConstOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclList1]");
        return buffer.toString();
    }
}
