// generated with ast extension for cup
// version 0.8
// 26/7/2022 18:42:45


package rs.ac.bg.etf.pp1.ast;

public class Expr2 extends Expr {

    private Expr Expr;
    private DoubleQuestion DoubleQuestion;
    private EndedHelp EndedHelp;
    private MinusOption MinusOption;
    private Term Term;
    private AddopTermList AddopTermList;

    public Expr2 (Expr Expr, DoubleQuestion DoubleQuestion, EndedHelp EndedHelp, MinusOption MinusOption, Term Term, AddopTermList AddopTermList) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DoubleQuestion=DoubleQuestion;
        if(DoubleQuestion!=null) DoubleQuestion.setParent(this);
        this.EndedHelp=EndedHelp;
        if(EndedHelp!=null) EndedHelp.setParent(this);
        this.MinusOption=MinusOption;
        if(MinusOption!=null) MinusOption.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.AddopTermList=AddopTermList;
        if(AddopTermList!=null) AddopTermList.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public DoubleQuestion getDoubleQuestion() {
        return DoubleQuestion;
    }

    public void setDoubleQuestion(DoubleQuestion DoubleQuestion) {
        this.DoubleQuestion=DoubleQuestion;
    }

    public EndedHelp getEndedHelp() {
        return EndedHelp;
    }

    public void setEndedHelp(EndedHelp EndedHelp) {
        this.EndedHelp=EndedHelp;
    }

    public MinusOption getMinusOption() {
        return MinusOption;
    }

    public void setMinusOption(MinusOption MinusOption) {
        this.MinusOption=MinusOption;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public AddopTermList getAddopTermList() {
        return AddopTermList;
    }

    public void setAddopTermList(AddopTermList AddopTermList) {
        this.AddopTermList=AddopTermList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(DoubleQuestion!=null) DoubleQuestion.accept(visitor);
        if(EndedHelp!=null) EndedHelp.accept(visitor);
        if(MinusOption!=null) MinusOption.accept(visitor);
        if(Term!=null) Term.accept(visitor);
        if(AddopTermList!=null) AddopTermList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DoubleQuestion!=null) DoubleQuestion.traverseTopDown(visitor);
        if(EndedHelp!=null) EndedHelp.traverseTopDown(visitor);
        if(MinusOption!=null) MinusOption.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(AddopTermList!=null) AddopTermList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DoubleQuestion!=null) DoubleQuestion.traverseBottomUp(visitor);
        if(EndedHelp!=null) EndedHelp.traverseBottomUp(visitor);
        if(MinusOption!=null) MinusOption.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(AddopTermList!=null) AddopTermList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr2(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DoubleQuestion!=null)
            buffer.append(DoubleQuestion.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndedHelp!=null)
            buffer.append(EndedHelp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MinusOption!=null)
            buffer.append(MinusOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddopTermList!=null)
            buffer.append(AddopTermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr2]");
        return buffer.toString();
    }
}
