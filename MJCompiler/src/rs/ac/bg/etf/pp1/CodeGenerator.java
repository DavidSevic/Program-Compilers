package rs.ac.bg.etf.pp1;

import java.util.List;
import java.util.Stack;
import java.util.LinkedList;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
	Obj currDsgArray = null;
	
	boolean negativeTerm = false;
	
	List<String> labelNames = new LinkedList<String>();
	List<Integer> labelPcs = new LinkedList<Integer>();
	
	List<String> labelNamesNotFound = new LinkedList<String>();
	List<Integer> labelPcsNotFound = new LinkedList<Integer>();
	
	Stack<Integer> pcToJumpFromStack = new Stack<Integer>();
	
	public void visit(ProgName progName) { // nmp cemu ovo sve
		
//		Obj o = MyTab.lenObj;
//		o.setAdr(Code.pc);
//		Code.put(Code.enter);
//		Code.put(o.getLevel());
//		Code.put(o.getLocalSymbols().size());
//		Code.put(Code.load_n);
//		Code.put(Code.arraylength);
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//		
//		o = MyTab.chrObj;
//		o.setAdr(Code.pc);
//		Code.put(Code.enter);
//		Code.put(o.getLevel());
//		Code.put(o.getLocalSymbols().size());
//		Code.put(Code.load_n);
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//		
//		o = MyTab.ordObj;
//		o.setAdr(Code.pc);
//		Code.put(Code.enter);
//		Code.put(o.getLevel());
//		Code.put(o.getLocalSymbols().size());
//		Code.put(Code.load_n);
//		Code.put(Code.exit);
//		Code.put(Code.return_);
		
	}
	
	
	public void visit(SingleStatement9 singleStmt) { // print
		
		if(singleStmt.getExpr().struct.getKind() == MyTab.intType.getKind()) {
			
			Code.loadConst(0);
			Code.put(Code.print);
			
		} else if(singleStmt.getExpr().struct.getKind() == MyTab.charType.getKind()) {
			
			Code.loadConst(0);
			Code.put(Code.bprint);
			
		} else if(singleStmt.getExpr().struct.getKind() == MyTab.boolType.getKind()) {
			
			Code.loadConst(0);
			Code.put(Code.print);	
		}
	}
	
	public void visit(SingleStatement10 singleStmt) { // print sa numconst 
	
		if(singleStmt.getExpr().struct.getKind() == MyTab.intType.getKind()) {
			
			Code.loadConst(singleStmt.getN2());
			Code.put(Code.print);
			
		} else if(singleStmt.getExpr().struct.getKind() == MyTab.charType.getKind()) {
			
			Code.loadConst(singleStmt.getN2());
			Code.put(Code.bprint);
			
		} else if(singleStmt.getExpr().struct.getKind() == MyTab.boolType.getKind()) {
			
			Code.loadConst(singleStmt.getN2());
			Code.put(Code.print);	
		}
		
	}
	
	public void visit(Factor3 factor) { // intconst
		
		Obj con = MyTab.insert(Obj.Con, "$", factor.struct);
		con.setLevel(0);
		con.setAdr(factor.getValueInt());
		
		Code.load(con);
	}
	
	public void visit(Factor4 factor) { // charconst
		
		Obj con = MyTab.insert(Obj.Con, "$", factor.struct);
		con.setLevel(0);
		con.setAdr(factor.getValueChar().charAt(1));
		
		Code.load(con);
	}
 	
	public void visit(Factor5 factor){ // boolconst
		Obj con = MyTab.insert(Obj.Con, "$", factor.struct);
		con.setLevel(0);
		if(factor.getValueBool().equals("true")) {
			con.setAdr(1);
		}else {
			con.setAdr(0);
		}
		
		Code.load(con);
	}
	
	public void visit(TypeIdentMeth meth) {

		meth.obj.setAdr(Code.pc);

		// dohvatanje argumenata i lokalnih var
		SyntaxNode methNode = meth.getParent();

		VarCounter varCnt = new VarCounter();
		methNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methNode.traverseTopDown(fpCnt);

		// generisanje ulaza
		Code.put(Code.enter);
		Code.put(fpCnt.count);
		Code.put(fpCnt.count + varCnt.count);

	}
	
	public void visit(VoidIdentMeth meth) {
	
		if(meth.getMethName().equals("main")) 
			mainPc = Code.pc;
		
		meth.obj.setAdr(Code.pc);
		
		// dohvatanje argumenata i lokalnih var
		SyntaxNode methNode = meth.getParent();
		
		VarCounter varCnt = new VarCounter();
		methNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methNode.traverseTopDown(fpCnt);
		
		// generisanje ulaza
		Code.put(Code.enter);
		Code.put(fpCnt.count);
		Code.put(fpCnt.count + varCnt.count);
		
	}
	
	public void visit(MethodDecl1 methDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MethodDecl2 methDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(Designator designator) { // prebacio sam logiku u DesignatorIdent, jer ce se on obici pre zagrada i ovih cuda
		
		if(designator.getParent() instanceof Factor1) { // ako je factor samo designator
			
			if(designator.getDesignatorList() instanceof DesignatorList2) { // ako je adresiranje niza
				
				Code.load(new Obj(Obj.Elem, "", designator.obj.getType().getElemType())); // aload
				
//				if(designator.obj.getType().getKind() == Struct.Char)
//					Code.put(Code.const_3);
//				else
//					Code.put(Code.const_4);
				
			}
			
		}
		
		
	}
	
	public void visit(DesignatorIdent designatorIdent) {
		
		SyntaxNode parent = designatorIdent.getParent(); // parent je Designator

		if (Factor1.class == parent.getParent().getClass()) { // ako je Factor1 parent od Designatora
			
//			if(designatorIdent.getDesignator().getDesignatorList() instanceof DesignatorList3) {
//				Code.store(dsgStmt.getDesignator().obj);
//				//Code.put(Code.const_3);
//			}
//			else if (designatorIdent.getDesignator().getDesignatorList() instanceof DesignatorList2) {
//				Code.store(new Obj(Obj.Elem, "", dsgStmt.getDesignator().obj.getType().getElemType()));	
//				//Code.put(Code.const_4);
//			}

			Code.load(((Designator)designatorIdent.getParent()).obj); // load Designator.obj
			
			//Code.put(Code.const_3);

			return;
		}

		if (DesignatorStatement.class == parent.getParent().getClass()) { // ako je DesigStatement parent od Designatora

			if (((Designator)designatorIdent.getParent()).obj.getType().getKind() == Struct.Array) { // ako je Designator 
																		// niz bez zagrada , ovo radim za assign slucaj,
																		// proveri posle za record
				
				currDsgArray = ((Designator)designatorIdent.getParent()).obj;

				//Code.put(Code.const_5);

			}

			if (((Designator)designatorIdent.getParent()).obj.getKind() == Obj.Elem) { // ako je niz sa zagradama , ovo radim za assign
														// slucaj,
														// proveri posle za record
				
				// OVO SE IZGLEDA NIKAD NE DESAVA JER SAM IZBACIO DODAVANJE ELEM TIPA U SEMANTICKOJ
				
				currDsgArray = ((Designator)designatorIdent.getParent()).obj;

				//Code.put(Code.const_4);

			}

		}

	}
	
	public void visit(Factor2 factor) { // poziv funkcije
		
		Obj functionObj = factor.getDesignator().obj;
		
		int offset = functionObj.getAdr() - Code.pc;
		
		Code.put(Code.call);
		
		Code.put2(offset);
		
	}
	
	public void visit(DesignatorStatement dsgStmt) {
		
		if(dsgStmt.getDesignatorStmtOptions() instanceof DesignatorStmtOptions1) { // assign
		
				if (dsgStmt.getDesignator().getDesignatorList() instanceof DesignatorList3) {
				
					Code.store(dsgStmt.getDesignator().obj);
				
				} else if (dsgStmt.getDesignator().getDesignatorList() instanceof DesignatorList2) {
					
					Code.store(new Obj(Obj.Elem, "", dsgStmt.getDesignator().obj.getType().getElemType())); // astore
				}

				return;
		}
		
		if(dsgStmt.getDesignatorStmtOptions() instanceof DesignatorStmtOptions2) { // poziv procedure(funkcije van izraza)
			
			Obj procObj = dsgStmt.getDesignator().obj;
			
			int offset = procObj.getAdr() - Code.pc;
			
			Code.put(Code.call);
			
			Code.put2(offset);
			
			if(procObj.getType().getKind() != MyTab.noType.getKind()) {
				Code.put(Code.pop); // skida se ako je imala neki return
			}
			
			return;
		}
		
		if(dsgStmt.getDesignatorStmtOptions() instanceof DesignatorStmtOptions3) { // ++ FALI ZA RECORD
			
			if(dsgStmt.getDesignator().getDesignatorList() instanceof DesignatorList2) { // ako je niz
				
				//dsgStmt.getDesignator().obj = dsgStmt.getDesignator().getDesignatorList().obj;
				
				Code.put(Code.dup2); // dupliramo zbog aload
				
				//Code.load(dsgStmt.getDesignator().obj);
				
				Code.load(new Obj(Obj.Elem, "", dsgStmt.getDesignator().obj.getType().getElemType())); // aload
				
				Code.put(Code.const_1);
				
				Code.put(Code.add);
				
				Code.store(new Obj(Obj.Elem, "", dsgStmt.getDesignator().obj.getType().getElemType()));
				
			} else if(dsgStmt.getDesignator().getDesignatorList() instanceof DesignatorList3) { // ako je samo dsg
				
				Code.load(dsgStmt.getDesignator().obj);
				
				Code.put(Code.const_1);
				
				Code.put(Code.add);
				
				Code.store(dsgStmt.getDesignator().obj);
				
			}
			
			return;
		}
		
		if(dsgStmt.getDesignatorStmtOptions() instanceof DesignatorStmtOptions4) { // -- FALI ZA RECORD
			
			if(dsgStmt.getDesignator().getDesignatorList() instanceof DesignatorList2) { // ako je niz
				
				Code.put(Code.dup2); // sta je ovo ???
				
				Code.load(dsgStmt.getDesignator().obj);
				
				Code.put(Code.const_1);
				
				Code.put(Code.sub);
				
				Code.store(dsgStmt.getDesignator().obj);
				
			} else if(dsgStmt.getDesignator().getDesignatorList() instanceof DesignatorList3) { // ako je samo dsg
				
				Code.load(dsgStmt.getDesignator().obj);
				
				Code.put(Code.const_1);
				
				Code.put(Code.sub);
				
				Code.store(dsgStmt.getDesignator().obj);
				
			}
			
			return;
		}
		
	}
	
	public void visit(LSQRDhelp lsqrd) {
		
		//Code.put(Code.const_2);
		
		if(currDsgArray != null) {
			
			Code.load(currDsgArray);
			
			//Code.put(Code.const_5);
			
			currDsgArray = null;
		}
	}
 	
	public void visit(Factor7 factor) { // new tip[]
		
		Code.put(Code.newarray);
		if(factor.getType().struct == MyTab.charType) {
			Code.put(0);
		}else {
			Code.put(1);
		}
		
	}
	
	public void visit(SingleStatement6 singleStatement) { // return; // ZASTO OVO PISEMO OPET, VEC SMO PISALI KOD OBILASKA SMENE ZA DEKL. METODE
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(SingleStatement7 singleStatement) { // return expr;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(AddopTermList1 atl) {
		
		if(atl.getAddop() instanceof Addop1)
			Code.put(Code.add);
		else if(atl.getAddop() instanceof Addop2)
			Code.put(Code.sub);
		
	}
	
	public void visit(MulopFactorList1 mfl) {
		
		if(mfl.getMulop() instanceof Mulop1)
			Code.put(Code.mul);
		else if(mfl.getMulop() instanceof Mulop2)
			Code.put(Code.div);
		else if(mfl.getMulop() instanceof Mulop3)
			Code.put(Code.rem);
		
	}
	
	public void visit(SingleStatement8 singleStatement) { // read
		
		if(singleStatement.getDesignator().obj.getType().getKind() == Struct.Array) { // ako se readuje u element niza
			
			if(singleStatement.getDesignator().obj.getType().getElemType() == MyTab.charType) {
				
				Code.put(Code.bread);
			
			}else {
				
				Code.put(Code.read);
			}
			
			Code.store(new Obj(Obj.Elem, "", singleStatement.getDesignator().obj.getType().getElemType())); // astore, ovde puca ako se readuje u elem niza charova
			
		} else { // ako se readuje u obicnu promenljivu

			if(singleStatement.getDesignator().obj.getType() == MyTab.charType) { 
				
				Code.put(Code.bread);

			} else {

				Code.put(Code.read);
			}
			
			Code.store(singleStatement.getDesignator().obj); // store
		}
	}
	
	public void visit(MinusOption1 minus) {
		
		negativeTerm = true;
		
	}
	
	public void visit(MinusOption2 minus) {
		
		negativeTerm = false;
		
	}
	
	public void visit(Term term) {
		
		if(term.getParent() instanceof Expr && negativeTerm == true)
			Code.put(Code.neg);
			
		
	}
	
	
	public void visit(Label label) { // labela:
		
		while (labelNamesNotFound.contains(label.getLabelName())) {
			
			int pom=labelNamesNotFound.indexOf(label.getLabelName());
			
			int zaofs=labelPcsNotFound.get(pom);
			
			Code.fixup(zaofs); // za onaj dole zabelezen trenutni pc od kog si hteo da skocis, on izracuna skok 
			
			labelNamesNotFound.remove(pom);
			
			labelPcsNotFound.remove(pom);
		}
		
		labelNames.add(label.getLabelName());
		labelPcs.add(Code.pc);
	}
	
	public void visit(SingleStatement11 stat) { // goto
		
		if(labelNames.contains(stat.getLabelCheck().getLabelName())) {
			
			int skk=labelNames.indexOf(stat.getLabelCheck().getLabelName());
			
			Code.putJump(labelPcs.get(skk)); // izmenio
		
		}else {
			labelNamesNotFound.add(stat.getLabelCheck().getLabelName());
			
			labelPcsNotFound.add(Code.pc + 1); // bez 1 puca
			
			Code.putJump(0); // nebitna vrednost, on kao zabelezi negde da si ti hteo sa trenutnog pc da skaces
			
			//Code.fixup(labelPcsNotFound.get(0)); // ne radi nista pametno
		}
	}
	
	public void visit(DoubleQuestion doubleQuestion) {
		
		Code.put(Code.dup); // dupliramo Expr1 na steku, jer ce putFalseJump da skine vrednost sa steka
		
		Code.put(Code.const_1);
		
		Code.put(Code.const_1);
		
		Code.put(Code.sub);
		
//		Obj con = MyTab.insert(Obj.Con, "$", null);
//		con.setLevel(0);
//		con.setAdr(Code.pc % 10);
//		Code.load(con);
		
		Code.putFalseJump(Code.eq, 0); // pod uslovom da Expr1 ne(!=) 0, zelimo da skocimo negde, ali jos ne znamo gde, to cemo posle kad saznamo da fixUpujemo
		
		//pcToJumpFrom = Code.pc - 2; // jer je inst: jneq s , gde jneq zauzima 1 bajt, a s zauzima 2 bajta, i onda pc - 2 nas vraca na pocetak s operanda da tu fixupujemo vrednost
		
		pcToJumpFromStack.push(Code.pc - 2);
		
	}
	public void visit(EndedHelp endedHelp) { // ako smo uopste dosli ovde, znaci da je Expr1 == 0, i sad treba da popujemo Expr1 sa steka
		
		Code.put(Code.pop); // skidamo Expr1 sa steka
		
	}
	public void visit(Expr2 Expr) {
		
		//Code.fixup(pcToJumpFrom); // onom skoku kazemo da skace na trenutni pc, tjs na zavrsetak ovog operatora
		
		int popPc = pcToJumpFromStack.pop();
		
		Code.fixup(popPc);
		
		
//		Obj secondObj = new Obj(Obj.Con, "", MyTab.intType);
//		
//		secondObj.setLevel(0);
//		
//		Code.store(secondObj);
//		
//		Obj firstObj = new Obj(Obj.Con, "", MyTab.intType);
//		
//		firstObj.setLevel(0);
//		
//		Code.store(firstObj);
//		
//		Code.put(Code.sub);
		
//		Code.load(firstObj);
//		Code.load(secondObj);
		
		
		
//		if(firstObj.getAdr() == secondObj.getAdr())
//			Code.put(Code.const_2);
//		else
//			Code.put(Code.const_1);
		
//		if(firstObj.getAdr() > secondObj.getAdr())
//			Code.put(Code.const_4);
//		else
//			Code.put(Code.const_3);
		
//		Code.put(firstObj.getAdr());
//		Code.put(secondObj.getAdr());
	}
	
	
	
	public int getMainPc() {
		
		return mainPc;
		
	}

}
