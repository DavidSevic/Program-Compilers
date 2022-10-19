// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStmtOptions2 extends DesignatorStmtOptions {

    private LPARENpars LPARENpars;
    private ActParsOption ActParsOption;
    private RPARENpars RPARENpars;

    public DesignatorStmtOptions2 (LPARENpars LPARENpars, ActParsOption ActParsOption, RPARENpars RPARENpars) {
        this.LPARENpars=LPARENpars;
        if(LPARENpars!=null) LPARENpars.setParent(this);
        this.ActParsOption=ActParsOption;
        if(ActParsOption!=null) ActParsOption.setParent(this);
        this.RPARENpars=RPARENpars;
        if(RPARENpars!=null) RPARENpars.setParent(this);
    }

    public LPARENpars getLPARENpars() {
        return LPARENpars;
    }

    public void setLPARENpars(LPARENpars LPARENpars) {
        this.LPARENpars=LPARENpars;
    }

    public ActParsOption getActParsOption() {
        return ActParsOption;
    }

    public void setActParsOption(ActParsOption ActParsOption) {
        this.ActParsOption=ActParsOption;
    }

    public RPARENpars getRPARENpars() {
        return RPARENpars;
    }

    public void setRPARENpars(RPARENpars RPARENpars) {
        this.RPARENpars=RPARENpars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LPARENpars!=null) LPARENpars.accept(visitor);
        if(ActParsOption!=null) ActParsOption.accept(visitor);
        if(RPARENpars!=null) RPARENpars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LPARENpars!=null) LPARENpars.traverseTopDown(visitor);
        if(ActParsOption!=null) ActParsOption.traverseTopDown(visitor);
        if(RPARENpars!=null) RPARENpars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LPARENpars!=null) LPARENpars.traverseBottomUp(visitor);
        if(ActParsOption!=null) ActParsOption.traverseBottomUp(visitor);
        if(RPARENpars!=null) RPARENpars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStmtOptions2(\n");

        if(LPARENpars!=null)
            buffer.append(LPARENpars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsOption!=null)
            buffer.append(ActParsOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RPARENpars!=null)
            buffer.append(RPARENpars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStmtOptions2]");
        return buffer.toString();
    }
}
