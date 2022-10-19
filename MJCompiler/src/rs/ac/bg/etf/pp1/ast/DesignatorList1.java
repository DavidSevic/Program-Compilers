// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class DesignatorList1 extends DesignatorList {

    private DesignatorList DesignatorList;
    private String dsgIdent;

    public DesignatorList1 (DesignatorList DesignatorList, String dsgIdent) {
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.dsgIdent=dsgIdent;
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public String getDsgIdent() {
        return dsgIdent;
    }

    public void setDsgIdent(String dsgIdent) {
        this.dsgIdent=dsgIdent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorList1(\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+dsgIdent);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorList1]");
        return buffer.toString();
    }
}
