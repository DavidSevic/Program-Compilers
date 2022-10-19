// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class FormParsList1 extends FormParsList {

    private FormParsList FormParsList;
    private Type Type;
    private String varName;
    private SqrdOption SqrdOption;

    public FormParsList1 (FormParsList FormParsList, Type Type, String varName, SqrdOption SqrdOption) {
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.SqrdOption=SqrdOption;
        if(SqrdOption!=null) SqrdOption.setParent(this);
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
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

    public SqrdOption getSqrdOption() {
        return SqrdOption;
    }

    public void setSqrdOption(SqrdOption SqrdOption) {
        this.SqrdOption=SqrdOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(SqrdOption!=null) SqrdOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SqrdOption!=null) SqrdOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SqrdOption!=null) SqrdOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParsList1(\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SqrdOption!=null)
            buffer.append(SqrdOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParsList1]");
        return buffer.toString();
    }
}
