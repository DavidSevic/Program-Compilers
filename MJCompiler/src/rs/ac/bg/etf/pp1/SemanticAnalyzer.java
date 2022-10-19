package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import java.util.*;
import java.util.function.ObjDoubleConsumer;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	int nVars;

	int printCallCount = 0;
	int varDeclCount = 0;
	int globVarDeclCount = 0;
	int constDeclCount = 0;

	boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());

	boolean sqrd = false;
	
	List<String> varNames = new LinkedList<String>();
	List<Boolean> varSqrd = new LinkedList<Boolean>();
	
	List<String> globAndConstNames = new LinkedList<String>(); // posle mogu da sredim da bude lepse, sa manje listi
	List<Struct> constTypes = new LinkedList<Struct>();
	List<Integer> constValuesAdr = new LinkedList<Integer>();
	
	int dsgSmena = 0;
	int dsgZbir = 0;
	String dsgTip = "inicijalno";
	//String dsgPrviIdent = "";
	int dsgStmtOption = 0;
	
	boolean mainMeth = false;
	boolean mainCurrent = false;
	
	boolean recordCurrent = false;
	List<String> varsInRecord = new LinkedList<String>();
	
	Struct returnExpr = MyTab.noType;
	
	Obj usraniRecx = null;
	
	List<Obj> sviObj = new LinkedList<Obj>();
	
	String currentDsgRecord = "init";
	Obj lastRecordField = null;
	
	List<String> recordNames = new LinkedList<String>();
	List<List<String>> recordFieldNames = new LinkedList<List<String>>();
	
	Struct exprStruct = null;
	
	List<String> labele = new LinkedList<String>();
	List<String> labeleNotFound = new LinkedList<String>();
	
	boolean breakContinue = false;
	
	boolean condNEorE = false;
	Obj lastArrays[] = {null, null};
	int indexLastArrays = 0;
	

	List<String> methNames = new LinkedList<String>();
	List<List<Obj>> methFormPars = new LinkedList<List<Obj>>();
	boolean sqrdFormPars = false;
	List<Struct> actParsTypes = new LinkedList<Struct>();
	boolean actParsNow = false;
	
	boolean termMustInt = false;
	
	int factorSmena = 0;
	
	boolean adresirano = false;
	
	Struct dsgStruct = null;
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(ProgName progName) {
		
		progName.obj = MyTab.insert(Obj.Prog, progName.getProgName(), MyTab.noType); // dodavanjem onog nonterminal etf.pp1... ispred ProgName, smo dodali 
		//ovoj klasi/smeni da ima polje obj koje je referenca na objektni cvor, i ovde stavimo da ta referenca pokazuje na objektni cvor koji je nastao za 
		//ovu smenu
		
		//novo
		sviObj.add(progName.obj);
		
		MyTab.openScope();
	}
	
	public void visit(Program program) {
		
		Obj objNode = MyTab.find("m");
		
//		if(objNode == MyTab.noObj)
//			report_info("NEMA", null);
//		else
//			report_info("IMA", null);
		
		nVars = MyTab.currentScope().getnVars();
		
		MyTab.chainLocalSymbols(program.getProgName().obj); // dohvatamo referencu na objektni cvor za koji zelimo da zatvorimo scope
		MyTab.closeScope();
		
		if(mainMeth == false)
			report_error("Greska: Program mora imati main metodu!", null);
		
		
	}

	// za scope metoda
	
	public void visit(TypeIdentMeth typeIdentMeth) {
		
		if(typeIdentMeth.getMethName().equals("main")) {
			
			mainMeth = true;
			
			mainCurrent = true;
			
			report_error("Greska: main metoda mora imati povratni tip void!", typeIdentMeth);
		} else
			mainCurrent = false;
	
		returnExpr = null;
		
		for(int i = 0; i < sviObj.size(); i++) 
			if(sviObj.get(i).getName().equals(typeIdentMeth.getMethName()))
				report_error("Greska: Element sa imenom: " + typeIdentMeth.getMethName() + " je vec deklarisan! ", null);
			
		typeIdentMeth.obj = MyTab.insert(Obj.Meth, typeIdentMeth.getMethName(), typeIdentMeth.getType().struct);
		
		globAndConstNames.add(typeIdentMeth.getMethName());
		
		methNames.add(typeIdentMeth.getMethName());
		methFormPars.add(new LinkedList<Obj>());
		
		//novo
		sviObj.add(typeIdentMeth.obj);
		
		MyTab.openScope(); 
		
	}
	
	public void visit(VoidIdentMeth voidIdentMeth) {
		
		if(voidIdentMeth.getMethName().equals("main")) {
			
			mainMeth = true;
			
			mainCurrent = true;
		
		} else
			mainCurrent = false;
		
		returnExpr = null;
		
		for(int i = 0; i < sviObj.size(); i++) 
			if(sviObj.get(i).getName().equals(voidIdentMeth.getMethName()))
				report_error("Greska: Element sa imenom: " + voidIdentMeth.getMethName() + " je vec deklarisan! ", null);
		
		voidIdentMeth.obj = MyTab.insert(Obj.Meth, voidIdentMeth.getMethName(), MyTab.noType); // noType sam stavio za void
		
		globAndConstNames.add(voidIdentMeth.getMethName());
		
		methNames.add(voidIdentMeth.getMethName());
		methFormPars.add(new LinkedList<Obj>());
		
		//novo
		sviObj.add(voidIdentMeth.obj);
		
		MyTab.openScope();
	}
	
	public void visit(FormParsOption1 formParsOption) {
		
		if(mainCurrent == true)
			report_error("Greska: main metoda mora biti bez formalnih parametara!", formParsOption);
		
//		for(Obj o : methFormPars.get(methFormPars.size() - 1))
//			if(o.getType().getKind() == 3)
//				report_info("ime: " + o.getName() + " tip: " + o.getType().getKind() + " elem: " + o.getType().getElemType().getKind(), formParsOption);
//			else
//				report_info("ime: " + o.getName() + " tip: " + o.getType().getKind(), formParsOption); //+ " elem: " + o.getType().getElemType().getKind(), formParsOption);
//    	
	}
	
	public void visit(MethodDecl1 methodDecl) {
		
		if(returnExpr == null)
			report_error("Greska: Metoda ciji povratni tip nije void, mora imati odgovarajucu return naredbu!", methodDecl);
		else if(methodDecl.getTypeIdentMeth().getType().struct.getKind() != returnExpr.getKind())
			report_error("Greska: Povratna vrednost metode mora biti odgovarajuca povratnom tipu!", methodDecl);
		else if(methodDecl.getTypeIdentMeth().getType().struct.getKind() == Struct.Array && methodDecl.getTypeIdentMeth().getType().struct.getElemType() != returnExpr.getElemType())
			report_error("Greska: Povratna vrednost metode mora biti odgovarajuca povratnom tipu!", methodDecl);
			
		Obj o = findSviObj(methodDecl.getTypeIdentMeth().getMethName());
		
		if(o != null)  
			o.setLevel(methFormPars.get(methFormPars.size() - 1).size());
		
//		report_info("METODA " + o.getName() + " IMA " + o.getLevel() + " PARAMETARA", methodDecl);
//		
		for(String s1 : labeleNotFound)
			if(labele.contains(s1) == false)
				report_error("Greska: Labela: " + s1 + " nije definisana u ovoj metodi!", methodDecl);	
		
		labele.clear();
		labele.clear();
		
		MyTab.chainLocalSymbols(methodDecl.getTypeIdentMeth().obj);
		MyTab.closeScope();
	}
	
	public void visit(MethodDecl2 methodDecl) {
		
		if(returnExpr != null)
			report_error("Greska: Metoda koja ima povratni tip void, ne sme imati izraz u return naredbi!", methodDecl);
			
		Obj o = findSviObj(methodDecl.getVoidIdentMeth().getMethName());
		
		if(o != null)  
			o.setLevel(methFormPars.get(methFormPars.size() - 1).size());
		
		//report_info("METODA " + o.getName() + " IMA " + o.getLevel() + " PARAMETARA", methodDecl);
		
		for(String s1 : labeleNotFound)
			if(labele.contains(s1) == false)
				report_error("Greska: Labela: " + s1 + " nije definisana u ovoj metodi!", methodDecl);	
		
		labele.clear();
		labele.clear();
		
		MyTab.chainLocalSymbols(methodDecl.getVoidIdentMeth().obj);
		MyTab.closeScope();
	}
	
	public void visit(SingleStatement7 singleStatement) {
		
		returnExpr = singleStatement.getExpr().struct;
		
	}
	
	public void visit(VarDecl1 vardecl){ // DA LI SME LOKALNA DA SE ZOVE ISTO KAO GLOBALNA? ako da, onda mogu da pamtim u listama sve 
										// deklarisane globalne i lokalne i tako da proveravam za nove

		varNames.add(vardecl.getVarName());

		Collections.reverse(varNames);

		for (int i = 0; i < varNames.size(); i++)
			if (MyTab.currentScope().findSymbol(varNames.get(i)) != null)
				report_error("Greska: Element sa imenom: " + varNames.get(i) + " je vec deklarisan! ", null);
			else {
				
				if(recordCurrent == true) {
					
					globAndConstNames.add(varNames.get(i));
					
					recordFieldNames.get(recordFieldNames.size() - 1).add(varNames.get(i)); // u poslednju listu, tjs trenutni record
																							// dodajemo ime novog polja
					if(varSqrd.get(i) == true) {
						
						//MyTab.insert(Obj.Fld, varNames.get(i), new Struct(Struct.Array, vardecl.getType().struct));
						
						sviObj.add(MyTab.insert(Obj.Fld, varNames.get(i), new Struct(Struct.Array, vardecl.getType().struct)));
						
						report_info("Deklarisan niz kao polje " + varNames.get(i), vardecl);
					}
					else {
						
						//MyTab.insert(Obj.Fld, varNames.get(i), vardecl.getType().struct);
						
						sviObj.add(MyTab.insert(Obj.Fld, varNames.get(i), vardecl.getType().struct));
						
						report_info("Deklarisana promenljiva kao polje " + varNames.get(i), vardecl);
					}
				}
				else { // recordCurrent == false
					
					if(varSqrd.get(i) == true) {
						
						//MyTab.insert(Obj.Var, varNames.get(i), new Struct(Struct.Array, vardecl.getType().struct));
						
						sviObj.add(MyTab.insert(Obj.Var, varNames.get(i), new Struct(Struct.Array, vardecl.getType().struct)));

						report_info("Deklarisan lokalni niz " + varNames.get(i), vardecl);
					}
					else {
						
						//MyTab.insert(Obj.Var, varNames.get(i), vardecl.getType().struct);

						sviObj.add(MyTab.insert(Obj.Var, varNames.get(i), vardecl.getType().struct));
						
						report_info("Deklarisana lokalna promenljiva " + varNames.get(i), vardecl);
						varDeclCount++;
					}
				}
			}

		varNames.clear();
		varSqrd.clear();
	}
	
	public void visit(VarDeclList1 vardecl){
		
		varNames.add(vardecl.getVarName());
	}
	
	public void visit(GlobVarDecl1 globvardecl){
		
		varNames.add(globvardecl.getVarName());

		globAndConstNames.add(globvardecl.getVarName()); // zbog provere globalnih kod designatora i jos nekih vrv

		Collections.reverse(varNames);

		for (int i = 0; i < varNames.size(); i++)
			if (MyTab.currentScope().findSymbol(varNames.get(i)) != null)
				report_error("Greska: Element sa imenom: " + varNames.get(i) + " je vec deklarisan! ", null);
			else {
				
				if (varSqrd.get(i) == true) {

					report_info("Deklarisan globalni niz " + varNames.get(i), globvardecl);
					
//					MyTab.insert(Obj.Var, varNames.get(i), new Struct(Struct.Array, globvardecl.getType().struct));
					sviObj.add(MyTab.insert(Obj.Var, varNames.get(i), new Struct(Struct.Array, globvardecl.getType().struct)));
					
					//globVarDeclCount++; da li se niz racuna kao promenljiva?

				} else {

					report_info("Deklarisana globalna promenljiva " + varNames.get(i), globvardecl);
					
//					MyTab.insert(Obj.Var, varNames.get(i), globvardecl.getType().struct);
					
					sviObj.add(MyTab.insert(Obj.Var, varNames.get(i), globvardecl.getType().struct));
					

					globVarDeclCount++;
				}
			}
		varNames.clear();
		varSqrd.clear();
	}
	
	public void visit(GlobVarDeclList1 globvardecl){
		
		varNames.add(globvardecl.getVarName());
		
		globAndConstNames.add(globvardecl.getVarName()); // zbog provere globalnih kod designatora i jos nekih vrv
	}
	
	public void visit(Type type) {
		
		Obj typeNode = MyTab.find(type.getTypeName()); // tipovi type se ne dodaju u tabelu simbola kao objektni cvorovi, nego su oni inicijalno dodati vec
		//i to su oni int, char, ...
		
		if(typeNode == MyTab.noObj) {
			
			report_error("Greska: Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			
			type.struct = MyTab.noType;
			
		} else {
			
			if(Obj.Type == typeNode.getKind()) {
				
				type.struct = typeNode.getType(); //proveravamo da li taj objektni cvor koji smo pronasli ima kind = Type, sto imaju ovi inicijalni
				//int, char, ... , a ne npr. kind = Var kao sto imaju deklarisane promenljive, i ako jeste onda ovoj nasoj klasi, dodamo da referenca struct
				//pokazuje na odgovarajuci struct cvor za taj tip
			
			} else {
				
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip! ", type);
				type.struct = MyTab.noType;
			}
		}
	}
	
	public void visit(SqrdOptionVar1 sqrdOptionVar) {
		
		sqrd = true;
		
		varSqrd.add(true);
		
	}
	
	public void visit(SqrdOptionVar2 sqrdOptionVar) {
	
		varSqrd.add(false);
		
	}
	
    public void visit(SingleStatement9 print) {
		printCallCount++;
		
		if(print.getExpr().struct.getKind() != Struct.Int && print.getExpr().struct.getKind() != Struct.Char && print.getExpr().struct.getKind() != Struct.Bool)
			report_error("Greska: Izraz koji se koristi za print mora biti int, char ili bool tipa! ", print);
    	
	}
    
    public void visit(SingleStatement10 print) {
		printCallCount++;
		
		if(print.getExpr().struct.getKind() != Struct.Int && print.getExpr().struct.getKind() != Struct.Char && print.getExpr().struct.getKind() != Struct.Bool)
			report_error("Greska: Izraz koji se koristi za print mora biti int, char ili bool tipa! ", print);
    	
	}
    
    //za pristup nizu
    
    public void visit(DesignatorList2 designatorList) {
    	
    	dsgSmena = 2;
    	
    	//++dsgZbir; NOVA IZMENA
    	
//    	if(designatorList.getDesignatorList() instanceof DesignatorList1) // sad dodao // jebes ovo
//    		adresiranLastField = true;
    	
    	
    	//provera da li je int adresiranje
    	if(designatorList.getExpr().struct != MyTab.intType) {
    		
    		report_error("Greska: Izraz koji se koristi za adresiranje niza nije ceo broj! ", designatorList); // los broj linije ???
    	
    	} else {
    		
    		report_info("Za adresiranje koriscen izraz koji je ceo broj", designatorList); // i ovde los broj linije
    	}
   
    }
    
    public void visit(DesignatorList1 designatorList) {
    	
    	dsgSmena = 1;
    	
    	++dsgZbir;
    	
    	report_info("DsgIdent : " + designatorList.getDsgIdent() + ", a curr : " + currentDsgRecord, designatorList);
   
    	
    	for(int i = 0; i < sviObj.size(); i++) {  // prvo proveravamo da li je polje
    		
    		Obj objCurr = sviObj.get(i);
    		
    		if(objCurr.getKind() == Obj.Fld && objCurr.getName().equals(designatorList.getDsgIdent())) { // pronasli smo nase polje
    																				// i za sad kazemo da je okej, a posle cu da 
    																				// radim proveru recorda
    			lastRecordField = objCurr; // cuvamo za proveru kasnije
    			
    			designatorList.obj = objCurr;
    			
    			for(int j = 0; j < recordNames.size(); j++) // trazimo currentDsgRecord u listi svih recorda
    				if(recordNames.get(j).equals(currentDsgRecord))
    					for(int k = 0; k < recordFieldNames.get(j).size(); k++) // trazimo ovo polje u listi polja bas ovog recorda
    						if(recordFieldNames.get(j).get(k).equals(designatorList.getDsgIdent())) {
    							
    							report_info("DsgIdent : " + designatorList.getDsgIdent() + " je prepoznat kao polje recorda: " + currentDsgRecord, designatorList);
    						
    							currentDsgRecord = designatorList.getDsgIdent();
    			    			
    			    			return;
    						}
    			
    			
    			report_error("Greska: Element sa imenom : " + designatorList.getDsgIdent() + " je polje nekog drugog recorda!", designatorList);
    			
    			currentDsgRecord = designatorList.getDsgIdent();
    			
    			return;
    		}
    	}
    	
    	
    	currentDsgRecord = designatorList.getDsgIdent(); // ako se ne uradi iznad pre returna, onda ovde
    	
    	lastRecordField = null; // cisto u slucaju ako bude greske za ovo polje, onda da tamo dole u proveri ne bude null exeption
    	
    	
    	for(int i = 0; i < sviObj.size(); i++) {  // ako nije polje, proveravamo da li je globVar/const/locVar
    		
    		Obj objCurr = sviObj.get(i);
    		
    		if((objCurr.getKind() == Obj.Con || objCurr.getKind() == Obj.Var) && objCurr.getName().equals(designatorList.getDsgIdent())) {
    																				 
    			report_error("Greska: Element sa imenom : " + designatorList.getDsgIdent() + " nije polje!", designatorList);
        		
    			return;		
    		}
    	}
   
    }
    
    public void visit(DesignatorList3 designatorList) {
    	
    	//dsgSmena = 3;
    	
    }
    
//    public void visit(Expr expr) { // stari expr
//    	
//    	expr.struct = expr.getTerm().struct;
//    	
//    	if(termMustInt == true && expr.getTerm().struct.getKind() != Struct.Int)
//    		report_error("Greska1: Svi elementi u racunanju izraza moraju biti tipa int : ", expr);
//    		
//    	//report_info("" + expr.getTerm().struct.getKind() + " + sve ono", expr);
//    	
//    	termMustInt = false;
//    }
    
    public void visit(Expr1 expr) {
    	
    	expr.struct = expr.getTerm().struct;
    	
    	if(termMustInt == true && expr.getTerm().struct.getKind() != Struct.Int)
    		report_error("Greska1: Svi elementi u racunanju izraza moraju biti tipa int : ", expr);
    		
    	//report_info("" + expr.getTerm().struct.getKind() + " + sve ono", expr);
    	
    	termMustInt = false;
    }
    
    public void visit(Expr2 expr) {
    	
    	expr.struct = expr.getTerm().struct; // nije ni bitno ovo, jer svakako u CodeGenerator cu da ispitujem vrednosti
    	
    	if(expr.getTerm().struct.getKind() != Struct.Int) // svakako mora int, a termMustInt cu da stavim na false svakako da ne pokvarim nesto
    		report_error("Greska1: Svi elementi u racunanju izraza moraju biti tipa int : ", expr);
    	
    	termMustInt = false;
    }
    
    public void visit(Term term) {
    	
    	term.struct = term.getFactor().struct;
    	
    	if(termMustInt == true) {

			if (term.struct.getKind() == Struct.Array && termMustInt) {
				if (term.struct.getElemType().getKind() != Struct.Int) {
					report_error("Greska: Svi elementi u racunanju izraza moraju biti tipa int : , ustvari je "
							+ term.struct.getKind(), term);
					return;
				}

			} else if (term.struct.getKind() != Struct.Int)
				report_error("Greska: Svi elementi u racunanju izraza moraju biti tipa int : , ustvari je "
						+ term.struct.getKind(), term);
		}	
    }
    
    public void visit(Factor3 factor) { // obilazak NUMCONST
    	
    	factor.struct = MyTab.intType;
    	
    }
    
    public void visit(Factor4 factor) { // obilazak CHARCONST
    	
    	factor.struct = MyTab.charType;
    	
    }

    public void visit(Factor5 factor) { // obilazak BOOLCONST
	
	factor.struct = MyTab.boolType;
	
}

    public void visit(Factor1 factor) { // obilazak Designator koji moze biti int
    	
    	//factor.struct = factor.getDesignator().obj.getType();
    	
    	factor.struct = dsgStruct;
    	
    	// sad ovo dole je u slucaju da se sa desne strane jednakosti nalazi referenca na niz
    	// onda ne zelim da uzmem ovaj dsgStruct, u kome ce se nalaziti Struct elementa tog niza
    	
//    	if(factor.getDesignator().getDesignatorIdent().getDsgIdent().equals("eol")) // da ne udje dole u proveru zbog exceptiona
//    		return; 
    	
    	//report_info("SAD JE " + factor.getDesignator().getDesignatorIdent().getDsgIdent(), factor);
    	
    	if(factor.getDesignator().obj.getType().getKind() == Struct.Array && factor.getDesignator().getDesignatorList() instanceof DesignatorList3)
    		factor.struct = factor.getDesignator().obj.getType(); 
    	
//    	if(factor.getDesignator().getDesignatorIdent().getDsgIdent().equals("bodovi"))
//    		report_info("FACTOR 1 BODOVI, struct je: " + factor.struct.getKind(), factor);
//    	
//    	if(factor.getDesignator().getDesignatorIdent().equals("bodovi"))
//			report_info("STRUCT dsg  JE " + factor.struct.getKind(), null);
    	
    }	
    
    public void visit(Factor8 factor) {
    	
    	factor.struct = factor.getExpr().struct;
    	
    }
    
    
    public void visit(Factor2 factor) {
    	
    	if(dsgZbir != 0) {
			report_error("Greska: Nije moguce pozvati metodu na taj nacin!", factor);
			dsgZbir = 0;
			return;
		}
    	
    	if(factor.getDesignator().getDesignatorIdent().getDsgIdent().equals("ord")) { // funckija chr
    		
    		if(actParsTypes.size() != 1) 
    			report_error("Greska: Funckija ord mora primati tacno jedan parametar!", factor);
    		else if(actParsTypes.get(0).getKind() != Struct.Char)
    			report_error("Greska: Funckija ord mora primati element tipa char!", factor);
    		
    		actParsTypes.clear();
			
			report_info("Poziv funckije: ord", factor);
		
			factor.struct = MyTab.intType;

			return;
    	}
    	
    	if(factor.getDesignator().getDesignatorIdent().getDsgIdent().equals("chr")) { // funckija chr
    		
    		if(actParsTypes.size() != 1) 
    			report_error("Greska: Funckija chr mora primati tacno jedan parametar!", factor);
    		else if(actParsTypes.get(0).getKind() != Struct.Int)
    			report_error("Greska: Funckija chr mora primati element tipa int!", factor);
    		
    		actParsTypes.clear();
			
			report_info("Poziv funckije: chr", factor);
		
			factor.struct = MyTab.charType;

			return;
    	}
		
    	// za prave metode
    	
		for(int i = 0; i < sviObj.size(); i++) 
			if(sviObj.get(i).getName().equals(factor.getDesignator().getDesignatorIdent().getDsgIdent())) {
				
				if(sviObj.get(i).getKind() == Obj.Meth) {
					
					for(int j = 0; j < methNames.size(); j++)
						if(methNames.get(j).equals(sviObj.get(i).getName())) 
							if(methFormPars.get(j).size() > 0 && actParsTypes.size() == 0)
								report_error("Greska: Nije prosledjen nijedan stvarni parametar metodi " + sviObj.get(i).getName() + " !", factor);
							else if(methFormPars.get(j).size() == 0 && actParsTypes.size() > 0)
								report_error("Greska: Metoda  " + sviObj.get(i).getName() + " ne prima nijedan formalni parametar!", factor);
							else if(methFormPars.get(j).size() != actParsTypes.size())
								report_error("Greska: Broj stvarnih parametara ne odgovara broju formalnih pri pozivu metode " + sviObj.get(i).getName() + " !", factor);
							else {
								// dobar broj prosledjenih, sad proveravamo tipove
								
								for(int p = 0; p < methFormPars.get(j).size(); p++)
									if(methFormPars.get(j).get(p).getType().getKind() != actParsTypes.get(p).getKind())
										report_error("Greska: Pri pozivu metode " + sviObj.get(i).getName() + " , stvarni paramatar na poziciji: " + (p + 1) + " ne odgovara formalnom parametru na toj poziciji!", factor);
									else if(methFormPars.get(j).get(p).getType().getKind() == Struct.Array && methFormPars.get(j).get(p).getType().getElemType().getKind() != actParsTypes.get(p).getElemType().getKind())
										report_error("Greska: Pri pozivu metode " + sviObj.get(i).getName() + " , stvarni paramatar na poziciji: " + (p + 1) + " ne odgovara formalnom parametru na toj poziciji!", factor);
								
							}
					
					
//					for(Struct s : actParsTypes)
//						report_info("tip: " + s.getKind(), factor);
					
					
					actParsTypes.clear();
					
					report_info("Poziv funckije:  " + factor.getDesignator().getDesignatorIdent().getDsgIdent(), factor);
				
					factor.struct = sviObj.get(i).getType();
					
					//report_info("METODA " + sviObj.get(i).getName() + " TYPE: " + sviObj.get(i).getType().getKind(), factor);
					
				} else
					report_error("Greska: Element sa imenom: " + factor.getDesignator().getDesignatorIdent().getDsgIdent() + " nije metoda! ", factor);
				return;
			}
    	
    }
    
    public void visit(DesignatorIdent designatorIdent) {
    	
    	currentDsgRecord = designatorIdent.getDsgIdent();
    	
    }
    
    public void visit(Designator designator) {
    	
    	//dsgPrviIdent = new String(designator.getDsgIdent());
    	
    	if(designator.getDesignatorIdent().getDsgIdent().equals("chr") || designator.getDesignatorIdent().getDsgIdent().equals("ord"))
    		return;
    	
    	if(designator.getDesignatorIdent().getDsgIdent().equals("eol")) {
    		dsgStruct = MyTab.charType;
    		designator.obj = new Obj(Obj.Var, "", MyTab.charType); // zbog provere posle u CodeGenerator koja trazi obj designatora
    		return;
    	}
    	
    	Obj objNode = MyTab.currentScope().findSymbol(designator.getDesignatorIdent().getDsgIdent()); // prvo probamo da nadjemo u lokalnom opsegu
    	
    	if(objNode != null) {
    		
    		designator.obj = objNode;
    		
    		if(objNode.getType().getKind() == Struct.Array && (actParsNow == false)) {
				dsgStruct = objNode.getType().getElemType();
				lastArrays[indexLastArrays++ % 2] = objNode; // zbog provere u condition, da se dva niza porede
    		} else { 
				dsgStruct = objNode.getType();
				lastArrays[indexLastArrays++ % 2] = objNode;
    		}
    		
    		if(objNode.getType().getKind() == Struct.Array && actParsNow == true && dsgSmena == 2)  // moze i lepse da se ubaci gore 
				dsgStruct = objNode.getType().getElemType();
    		
    		if(objNode.getType().getKind() == Struct.Array &&  dsgSmena == 2)  // moze i lepse da se ubaci gore 
				dsgStruct = objNode.getType().getElemType();
    		
    		if(dsgZbir != 0) 
    			dsgStruct = lastRecordField.getType();
    		
    		if(dsgSmena == 2 && dsgZbir == 0) { // dodao sam i ovo za zbir, jer bez toga, u slucaju da ima recorda i polje koje je niz
    											// , on na kraju ocekuje da je prvi dsg neki niz, a moze biti i record
    			
    			if(objNode.getType().getKind() != Struct.Array)
    				report_error("Greska1: Element " + designator.getDesignatorIdent().getDsgIdent() + " nije niz i ne moze se adresirati!", designator);
    			else
    				report_info("Pristup nizu " + designator.getDesignatorIdent().getDsgIdent(), designator); // i ovde los broj linije
    			 
    			
//    			dsgSmena = 0; // MOZDA
//    			adresirano = true; // MOZDA
    			
    			
    			// za generisanje koda
    			
    			//designator.getDesignatorList().obj = new Obj(Obj.Elem, designator.getDesignatorIdent().getDsgIdent(), designator.obj.getType().getElemType());
			
    			//designator.obj = new Obj(Obj.Elem, designator.getDesignatorIdent().getDsgIdent(), designator.obj.getType().getElemType());

    			//report_info("URADIOOOOOOOOOOOOOOOOOOOOOOOOO", null);
    			
    		}
    		
    		//report_info("gleda se dsg: " + objNode.getName(), null);
    		
    		if(objNode.getType().getKind() == Struct.Array)
    			if(dsgSmena == 2) {
    				adresirano = true;
    				dsgSmena = 0;
    			} else
    				adresirano = false;
    		
//    		if(objNode.getName().equals("bodovi"))
//    			report_info("DSG bodovi", designator);
    			
			return;
    	}
    	
    	// ako nije nadjen u lokalnom opsegu, probamo globalni i const(tu ne spadaju oni inicijalni tipovi, sto je super) i record,
    	// imena metoda ne proveravam ovde, tako da ako bude neki dsg sa imenom metode kao promenljivom, reci ce da nije deklarisan
    	// mogu i za polje recorda da kazem da je nedeklarisano, ali sam stavio da bude ovako specificno napisano 
    	// dodao sam i metode ovde

    	
    	for(int i = 0; i < globAndConstNames.size(); i++)
			if (designator.getDesignatorIdent().getDsgIdent().equals(globAndConstNames.get(i))) {

																// ovde cu imati mali problem za dohvatanje polja recorda sa istim
																// imenom kao neko globalno ime
				
				for(int j = 0; j < sviObj.size(); j++) {
					
					if(sviObj.get(j).getName().equals(designator.getDesignatorIdent().getDsgIdent())) {
						
						objNode = sviObj.get(j);
						
						designator.obj = objNode;
						
//						if(objNode.getName().equals("recX"))
//							report_info("HAAHAHAH, kind je : " + objNode.getKind() + " zbir je : " + dsgZbir, designator); // i ovde los broj linije
//						
						if(objNode.getKind() == Obj.Fld && dsgZbir == 0) { // ako polje recorda stoji samo
							
							report_error("Greska: Element " + designator.getDesignatorIdent().getDsgIdent() + " je polje rekorda i ne sme stajati samo!", designator);
							
						}
						
						if(objNode.getType().getKind() == Struct.Array && actParsNow == false) {
							dsgStruct = objNode.getType().getElemType();
							lastArrays[indexLastArrays++ % 2] = objNode;
			    		} else { 
							dsgStruct = objNode.getType();
							lastArrays[indexLastArrays++ % 2] = objNode;
			    		}
						
						if(objNode.getType().getKind() == Struct.Array && actParsNow == true && dsgSmena == 2)  // moze i lepse da se ubaci gore 
							dsgStruct = objNode.getType().getElemType();
						
						if(objNode.getType().getKind() == Struct.Array && dsgSmena == 2)  // moze i lepse da se ubaci gore 
							dsgStruct = objNode.getType().getElemType();
						
						if(dsgZbir != 0 && lastRecordField != null) {
			    			if(lastRecordField.getType().getKind() == Struct.Array) // DODAO SAD, mozda nije dobro
			    				dsgStruct = lastRecordField.getType().getElemType();
			    			else
			    				dsgStruct = lastRecordField.getType();
			    			
						}
				
						if(dsgSmena == 2 && dsgZbir == 0) { // dodao sam i ovo za zbir, jer bez toga, u slucaju da ima recorda i polje koje je niz
															// , on na kraju ocekuje da je prvi dsg neki niz, a moze biti i record
			    			
			    			if(objNode.getType().getKind() != Struct.Array)
			    				report_error("Greska2: Element " + designator.getDesignatorIdent().getDsgIdent() + " nije niz i ne moze se adresirati!", designator);
			    			else
			    				report_info("Pristup nizu " + designator.getDesignatorIdent().getDsgIdent(), designator); // i ovde los broj linije
			    			 
			    			
//			    			dsgSmena = 0; // MOZDA
//			    			adresirano = true; // MOZDA
			    			
			    			
			    			// za generisanje koda
			    			
			    			//designator.getDesignatorList().obj = new Obj(Obj.Elem, designator.getDesignatorIdent().getDsgIdent(), designator.obj.getType().getElemType());
						
			    			//designator.obj = new Obj(Obj.Elem, designator.getDesignatorIdent().getDsgIdent(), designator.obj.getType().getElemType());

			    			//report_info("URADIOOOOOOOOOOOOOOOOOOOOOOOOO", null);
						}
						
						//report_info("gleda se dsg " + objNode.getName(), null);
						
						if(objNode.getType().getKind() == Struct.Array)
			    			if(dsgSmena == 2) {
			    				adresirano = true;
			    				dsgSmena = 0;
			    			} else
			    				adresirano = false;
						
						break;
					}
				}
				
				return;
			}
		
		//ako nije nadjen ni u globalnom
		
    	designator.obj = null; // ovo nije ni bitno jer je greska
    	
		report_error("Greska: Element " + designator.getDesignatorIdent().getDsgIdent() + " nije deklarisan3!", designator);
    }
    
    //provera ponovnih deklaracija
    
    //za konstante
    
    public void visit(ConstDecl constDecl){ // fali provera da li je vec deklarisano sa find
		
		varNames.add(constDecl.getVarName());

		globAndConstNames.add(constDecl.getVarName()); // zbog provere globalnih kod designatora i jos nekih vrv

		Collections.reverse(varNames); // ne moraju da se reversuju constTypes
		
		//report_info("varNames size: " + varNames.size() + ", constTypes size: " + constTypes.size(), constDecl);
		
//		for(String s : varNames)
//			report_info("ime: " + s, constDecl);
//		
//		for(Struct s : constTypes)
//			report_info("tip: " + s.getKind(), constDecl);

		for (int i = 0; i < varNames.size(); i++)
			if (MyTab.currentScope().findSymbol(varNames.get(i)) != null)
				report_error("Greska: Element sa imenom: " + varNames.get(i) + " je vec deklarisan! ", constDecl);
			else {
				
				//MyTab.insert(Obj.Con, varNames.get(i), constDecl.getType().struct); // treba da se sredi za tip
				
				Obj novi = MyTab.insert(Obj.Con, varNames.get(i), constDecl.getType().struct);
				
				novi.setAdr(constValuesAdr.get(i));
				
				sviObj.add(novi);
				
				//report_info("varName: " + novi.getName() + ", adr: " + novi.getAdr(), null);
				
				if(constTypes.get(i) != constDecl.getType().struct)
					report_error("Greska: Konstanta sa imenom: " + varNames.get(i) + " nije odgovarajuceg tipa! ", constDecl);
				else
					report_info("Deklarisana konstanta " + varNames.get(i), constDecl);

				constDeclCount++;
			}
		
		
//		for(String s : varNames)
//			report_info("varName: " + s, null);
//		
//		for(Integer s : constValuesAdr)
//			report_info("varName: " + s, null);
		
		varNames.clear();
		constTypes.clear();
		constValuesAdr.clear();
    }
	
	public void visit(ConstDeclList1 constdecl){
		
		varNames.add(constdecl.getVarName());
		
		globAndConstNames.add(constdecl.getVarName()); // zbog provere globalnih kod designatora i jos nekih vrv
	}
    
    public void visit(ConstOption1 constOption) {
    	
    	constTypes.add(MyTab.intType);
    	
    	constValuesAdr.add(constOption.getValueInt());
    	
    } 
    
    public void visit(ConstOption2 constOption) {
    	
    	constTypes.add(MyTab.charType);
    	
    	constValuesAdr.add((int)constOption.getValueChar().charAt(1)); // 'a' -> 97
    } 

    public void visit(ConstOption3 constOption) {
	
    	constTypes.add(MyTab.boolType);
    	
    	constValuesAdr.add((constOption.getValueBool().equals("true") ? 1 : 0));
    } 
	
    // za record
    
    public void visit(RecordIdent recordIdent) {
    	
    	if(MyTab.currentScope().findSymbol(recordIdent.getVarName()) != null)
    		report_error("Greska: Element sa imenom: " + recordIdent.getVarName() + " je vec deklarisan! ", recordIdent);
    	else {
    		
    		//recordIdent.obj = MyTab.insert(Obj.Type, recordIdent.getVarName(), MyTab.noType); // koji kind i koji type???
    		
    		sviObj.add(recordIdent.obj = MyTab.insert(Obj.Type, recordIdent.getVarName(), MyTab.noType)); 
    		
    		globAndConstNames.add(recordIdent.getVarName());
    		
    		recordNames.add(recordIdent.getVarName());
    		recordFieldNames.add(new LinkedList<String>());
    	}
    	
    	recordCurrent = true;
    	
    	MyTab.openScope();
    	
    }
    
    // za designator assign
    
    public void visit(DesignatorStatement designatorStatement) {
    	
//    	if(designatorStatement.getDesignatorStmtOptions() instanceof DesignatorStmtOptions2) { OVAKO JE 100 PUTA BOLJE ALI NISAM ZNAO DA IMA TO    	
    	
    	if(dsgStmtOption == 2) {
    	
    		if(dsgZbir != 0) {
    			report_error("Greska: Nije moguce pozvati metodu na taj nacin!", designatorStatement);
    			dsgZbir = 0;
    			factorSmena = 0;
    			return;
    		}
    		
    		if(designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent().equals("ord")) { // funckija chr
        		
        		if(actParsTypes.size() != 1) 
        			report_error("Greska: Funckija ord mora primati tacno jedan parametar!", designatorStatement);
        		else if(actParsTypes.get(0).getKind() != Struct.Char)
        			report_error("Greska: Funckija ord mora primati element tipa char!", designatorStatement);
        		
        		actParsTypes.clear();
    			
    			report_info("Poziv funckije: ord", designatorStatement);
    			
    			factorSmena = 0;
    			
				dsgSmena = 0;
				
    			return;
        	}
        	
        	if(designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent().equals("chr")) { // funckija chr
        		
        		if(actParsTypes.size() != 1) 
        			report_error("Greska: Funckija chr mora primati tacno jedan parametar!", designatorStatement);
        		else if(actParsTypes.get(0).getKind() != Struct.Int)
        			report_error("Greska: Funckija chr mora primati element tipa int!", designatorStatement);
        		
        		actParsTypes.clear();
    			
    			report_info("Poziv funckije: chr", designatorStatement);
    			
    			factorSmena = 0;
    			
				dsgSmena = 0;
    			return;
        	}
    		
    		for(int i = 0; i < sviObj.size(); i++) 
    			if(sviObj.get(i).getName().equals(designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent())) {
    				
    				if(sviObj.get(i).getKind() == Obj.Meth) {
    					
    					for(int j = 0; j < methNames.size(); j++)
    						if(methNames.get(j).equals(sviObj.get(i).getName())) 
    							if(methFormPars.get(j).size() > 0 && actParsTypes.size() == 0)
    								report_error("Greska: Nije prosledjen nijedan stvarni parametar metodi " + sviObj.get(i).getName() + " !", designatorStatement);
    							else if(methFormPars.get(j).size() == 0 && actParsTypes.size() > 0)
    								report_error("Greska: Metoda  " + sviObj.get(i).getName() + " ne prima nijedan formalni parametar!", designatorStatement);
    							else if(methFormPars.get(j).size() != actParsTypes.size())
    								report_error("Greska: Broj stvarnih parametara ne odgovara broju formalnih pri pozivu metode " + sviObj.get(i).getName() + " !", designatorStatement);
    							else {
    								// dobar broj prosledjenih, sad proveravamo tipove
    								
    								for(int p = 0; p < methFormPars.get(j).size(); p++)
    									if(methFormPars.get(j).get(p).getType().getKind() != actParsTypes.get(p).getKind())
    										report_error("Greska: Pri pozivu metode " + sviObj.get(i).getName() + " , stvarni paramatar na poziciji: " + (p + 1) + " ne odgovara formalnom parametru na toj poziciji!", designatorStatement);
    									else if(methFormPars.get(j).get(p).getType().getKind() == Struct.Array && methFormPars.get(j).get(p).getType().getElemType().getKind() != actParsTypes.get(p).getElemType().getKind())
    										report_error("Greska: Pri pozivu metode " + sviObj.get(i).getName() + " , stvarni paramatar na poziciji: " + (p + 1) + " ne odgovara formalnom parametru na toj poziciji!", designatorStatement);
    								
    							}
    					
    					
//    					for(Struct s : actParsTypes)
//    						report_info("tip: " + s.getKind(), designatorStatement);
    					
    					
    					actParsTypes.clear();
    					
    					report_info("Poziv funckije:  " + designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent(), designatorStatement);
    				
    				} else
    					report_error("Greska: Element sa imenom: " + designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent() + " nije metoda! ", designatorStatement);
    				
    				factorSmena = 0;
    				dsgSmena = 0;
    				return;
    			}
    		}
    	
//    	if(dsgStmtOption != 1) // ovo ispod vazi samo za assign
//    		return;
    	
    	if(dsgZbir == 0) { // ako nema record.polje.polje...
    	
    		Obj objDsg = MyTab.currentScope().findSymbol(designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent());
    		
    		if(objDsg == null) { // ako nije lokalni, mora biti u svim
    			
				for (int i = 0; i < sviObj.size(); i++) 
					if (sviObj.get(i).getName().equals(designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent())) {
						objDsg = sviObj.get(i);
						break;
					}
			}
			// prvo provera da li to moze da stoji sa leve strane jednakosti
    		
    		if(objDsg == null) // ako je nedeklarisan, msm nisam ja sad pravio ovde savrseni oporavak od gresaka, bas me briga ako  
    			return;		   // nekad pukne program zbog neke greske, koju ja pritom prijavim lepo

			if (objDsg.getKind() == Obj.Con)
				report_error("Greska: Element sa imenom: "
						+ designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent() + " je konstanta! ",
						designatorStatement);

			if (objDsg.getKind() == Obj.Type)
				report_error("Greska: Element sa imenom: "
						+ designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent() + " je ime recorda! ",
						designatorStatement);

			if (objDsg.getKind() == Obj.Var && objDsg.getType().getKind() == Struct.Array && (adresirano != true && factorSmena != 7 && exprStruct.getKind() != Struct.Array)) 
				report_error("Greska1: Element sa imenom: "
						+ designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent()
						+ " je ime niza, mora se adresirati!, dsgSmena je: " + dsgSmena + "exprStruct je " + exprStruct.getKind(), designatorStatement);

			if (objDsg.getKind() == Obj.Meth)
				report_error("Greska: Element sa imenom: "
						+ designatorStatement.getDesignator().getDesignatorIdent().getDsgIdent() + " je metoda! ",
						designatorStatement);

			// ovo je provera da li samo polje recorda stoji na levoj strani assign, ali tu
			// proveru cu da ubacim iznad u Dsg

//			if (objDsg.getKind() == Obj.Fld)
//				report_error("Greska: Element sa imenom: " + designatorStatement.getDesignator().getDsgIdent() + " je polje recorda, ne sme stajati samo! ", designatorStatement);
//					

			// sada provera da li su kompatibilni tipovi

			if (dsgStmtOption == 1) {
				
				if(factorSmena == 7) { // ako se dodeljuje array
					
					Obj thisArray = MyTab.currentScope().findSymbol(objDsg.getName());
					
					if(thisArray == null) {
						for(Obj o : sviObj)
							if(o.getName().equals(objDsg.getName()))
									thisArray = o;
					}
					
					if(thisArray == null)
						return;
					
					if(thisArray.getType().getKind() == Struct.Array) {
						if(thisArray.getType().getElemType().getKind() != exprStruct.getElemType().getKind()) {
							
							//report_info("niz: " + thisArray.getName() + "kind : " + thisArray.getType().getKind() + "kind elema:" + thisArray.getType().getElemType().getKind(), designatorStatement);
							
							report_error(
									"Greska: Nekompatibilna dodela, ocekivani tip: " + thisArray.getType().getElemType().getKind()
											+ " a dobijeni tip je: " + exprStruct.getKind() + " !",
									designatorStatement);
							
						} 
					} else {
						
						report_error(
								"Greska: Nekompatibilna dodela, ocekivani tip: " + thisArray.getType().getKind()
										+ " a dobijeni tip je: " + exprStruct.getKind() + " !",
								designatorStatement);
						
					}
							
					factorSmena = 0;
					dsgSmena = 0;
					return;
				}

				if (objDsg.getType().getKind() != Struct.Array) {
					
					if (objDsg.getType().getKind() != exprStruct.getKind())
						report_error("Greska ho: Nekompatibilna dodela, ocekivani tip: " + objDsg.getType().getKind()
								+ " a dobijeni tip je: " + exprStruct.getKind() + " !", designatorStatement);
				} else if (objDsg.getType().getElemType().getKind() != exprStruct.getKind() && exprStruct.getKind() != Struct.Array)
					report_error(
							"Greska hi: Nekompatibilna dodela, ocekivani tip: " + objDsg.getType().getElemType().getKind()
									+ " a dobijeni tip je: " + exprStruct.getKind() + " !",
							designatorStatement);
				
				if (objDsg.getType().getKind() == Struct.Array && exprStruct.getKind() == Struct.Array) // ovo sam sad dodao ako se radi dodela niz1 = niz2, a nisu istog tipa, ma gluposti...
					if(objDsg.getType().getElemType().getKind() != exprStruct.getElemType().getKind())
						report_error(
								"Greska hj: Nekompatibilna dodela, ocekivani tip: " + objDsg.getType().getElemType().getKind()
										+ " a dobijeni tip je: " + exprStruct.getKind() + " !",
								designatorStatement);

			} else if (dsgStmtOption == 3 || dsgStmtOption == 4) {

				if (objDsg.getType().getKind() != Struct.Array) {
					if (objDsg.getType().getKind() != Struct.Int)
						report_error("Greska: Post inkrementiranje i dekrementiranje je moguce samo za int tipove" + objDsg.getName(),
								designatorStatement);
				} else if (objDsg.getType().getElemType().getKind() != Struct.Int)
					report_error("Greska: Post inkrementiranje i dekrementiranje je moguce samo za int tipove : " + objDsg.getName(),
							designatorStatement);
			}
			
			factorSmena = 0;

			return;
	}
    	else { // ako je bilo record.polje.polje...
    		
    		if(lastRecordField != null) { // u slucaju da bila greska kod provere polja
    			
    			// prvo provera da li to moze da stoji sa leve strane jednakosti
    			
				if (lastRecordField.getType().getKind() == Struct.Array && (adresirano != true && factorSmena != 7 && exprStruct.getKind() != Struct.Array))
					report_error("Greska2: Element sa imenom: " + lastRecordField.getName() + " je ime niza, mora se adresirati! " + " adresirano " + adresirano + " factsmena " + factorSmena + " dsgsmena " + dsgSmena, designatorStatement);
    			
				// sada provera da li su kompatibilni tipovi
				
				if(dsgStmtOption == 1) {
					
					if(factorSmena == 7) { // ako se dodeljuje array
						
						Obj thisArray = MyTab.currentScope().findSymbol(lastRecordField.getName());
						
						if(thisArray == null) {
							for(Obj o : sviObj)
								if(o.getName().equals(lastRecordField.getName()))
										thisArray = o;
						}
						
						if(thisArray == null)
							return;
						
						if(thisArray.getType().getKind() == Struct.Array) {
							if(thisArray.getType().getElemType().getKind() != exprStruct.getElemType().getKind()) {
								
								//report_info("niz: " + thisArray.getName() + "kind : " + thisArray.getType().getKind() + "kind elema:" + thisArray.getType().getElemType().getKind(), designatorStatement);
								
								report_error(
										"Greska: Nekompatibilna dodela, ocekivani tip: " + thisArray.getType().getElemType().getKind()
												+ " a dobijeni tip je: " + exprStruct.getKind() + " !",
										designatorStatement);
								
							} 
						} else {
							
							report_error(
									"Greska: Nekompatibilna dodela, ocekivani tip: " + thisArray.getType().getKind()
											+ " a dobijeni tip je: " + exprStruct.getKind() + " !",
									designatorStatement);
							
						}
								
						factorSmena = 0;
						
						dsgZbir = 0;
						
						return;
					}
				
					if(lastRecordField.getType().getKind() != Struct.Array) {
						if(lastRecordField.getType().getKind() != exprStruct.getKind())
							report_error("Greska EJ: Nekompatibilna dodela, ocekivani tip: " + lastRecordField.getType().getKind() + " a dobijeni tip je: " + exprStruct.getKind() + " !", designatorStatement);
					}
					else if(lastRecordField.getType().getElemType().getKind() != exprStruct.getKind())
						report_error("Greska x: Nekompatibilna dodela, ocekivani tip: " + lastRecordField.getType().getElemType().getKind() + " a dobijeni tip je: " + exprStruct.getKind() + " !", designatorStatement);
				
					
					
					
				} else if(dsgStmtOption == 3 || dsgStmtOption == 4) {
					
					if(lastRecordField.getType().getKind() != Struct.Array) {
						if(lastRecordField.getType().getKind() != Struct.Int)
							report_error("Greska: Post inkrementiranje i dekrementiranje je moguce samo za int tipove", designatorStatement);
					}
					else if(lastRecordField.getType().getElemType().getKind() != Struct.Int)
						report_error("Greska: Post inkrementiranje i dekrementiranje je moguce samo za int tipove", designatorStatement);
				
					
				}
				
    		}
    		
    		dsgZbir = 0;
    	}
    	
    	dsgSmena = 0; // msm da je nepotrebno
    	factorSmena = 0;
    }
    
    public void visit(DesignatorStmtOptions1 dsgStmtOpt) {
    	
    	dsgStmtOption = 1;
    	
    	exprStruct = dsgStmtOpt.getExpr().struct;
    	
    }
    
    public void visit(DesignatorStmtOptions2 dsgStmtOpt) { 
    	
    	dsgStmtOption = 2;
    	
    }

    public void visit(DesignatorStmtOptions3 dsgStmtOpt) { 
	
    	dsgStmtOption = 3;
	
    }

    public void visit(DesignatorStmtOptions4 dsgStmtOpt) { 
	
    	dsgStmtOption = 4;
	
    }
    
    public void visit(RecordDecl recordDecl) {
    	
    	recordCurrent = false;
    	
    	MyTab.chainLocalSymbols(recordDecl.getRecordIdent().obj);
    	
    	MyTab.closeScope();
    }
    
    public void visit(Label label) {
    	
    	//report_info("LABELA DEFINICIJA", label);
    	
    	for(String curr : labele)
    		if(curr.equals(label.getLabelName())) {
    			report_error("Greska: Labela: " + label.getLabelName() + " je vec definisana u ovoj metodi!", label);		
    			return;
    		}
    	
    	labele.add(label.getLabelName());
    	
    	//report_info("DODAO", label);
    }
    
    public void visit(LabelCheck labelCheck) {
    	
    	//report_info("LABELA GOTO", labelCheck);
    	
    	for(String curr : labele)
    		if(curr.equals(labelCheck.getLabelName())) {	
    			return;
    		}
    	
    	//report_error("Greska: Labela: " + labelCheck.getLabelName() + " nije definisana u ovoj metodi!", labelCheck);	
    	
    	labeleNotFound.add(labelCheck.getLabelName());
    	
    	//report_info("DODAO", labelCheck);
    }
    
    public void visit(DOeasy doEasy) {
    	
    	breakContinue = true;
    	
    }
    
    
    public void visit(WHILEeasy whileEasy) {
    	
    	breakContinue = false;
    	
    }
    
    public void visit(SingleStatement4 singleStatement) {
    	
    	if(breakContinue == false)
    		report_error("Greska: break naredba mora stajati unutar DO WHILE petlje !", singleStatement);	
    	
    }
    
    public void visit(SingleStatement5 singleStatement) {
    	
    	if(breakContinue == false)
    		report_error("Greska: continue naredba mora stajati unutar DO WHILE petlje !", singleStatement);	
    	
    }
    
    public void visit(SingleStatement8 singleStatement) {
    
    	
    	if(dsgZbir == 0) { // ako nema record.polje.polje...
    	
    		Obj objDsg = MyTab.currentScope().findSymbol(singleStatement.getDesignator().getDesignatorIdent().getDsgIdent());
    		
    		if(objDsg == null) { // ako nije lokalni, mora biti u svim
    			
				for (int i = 0; i < sviObj.size(); i++) 
					if (sviObj.get(i).getName().equals(singleStatement.getDesignator().getDesignatorIdent().getDsgIdent())) {
						objDsg = sviObj.get(i);
						break;
					}
			}
			// prvo provera da li to moze da stoji sa leve strane jednakosti
    		
    		if(objDsg == null)
    			return;

			if (objDsg.getKind() == Obj.Con)
				report_error("Greska: Element sa imenom: "
						+ singleStatement.getDesignator().getDesignatorIdent().getDsgIdent() + " je konstanta! ",
						singleStatement);

			if (objDsg.getKind() == Obj.Type)
				report_error("Greska: Element sa imenom: "
						+ singleStatement.getDesignator().getDesignatorIdent().getDsgIdent() + " je ime recorda! ",
						singleStatement);

			if (objDsg.getKind() == Obj.Var && objDsg.getType().getKind() == Struct.Array && adresirano != true)
				report_error("Greska3: Element sa imenom: "
						+ singleStatement.getDesignator().getDesignatorIdent().getDsgIdent()
						+ " je ime niza, mora se adresirati! ", singleStatement);

			if (objDsg.getKind() == Obj.Meth)
				report_error("Greska: Element sa imenom: "
						+ singleStatement.getDesignator().getDesignatorIdent().getDsgIdent() + " je metoda! ",
						singleStatement);
			
			// provera za tip
			
			if (objDsg.getType().getKind() != Struct.Array) {
				
				if(objDsg.getType().getKind() != Struct.Int && objDsg.getType().getKind() != Struct.Char && objDsg.getType().getKind() != Struct.Bool)
					report_error("Greska: Element unutar read mora biti int, char ili bool tipa: ", singleStatement);
				
			} else 
				if(objDsg.getType().getElemType().getKind() != Struct.Int && objDsg.getType().getElemType().getKind() != Struct.Char && objDsg.getType().getElemType().getKind() != Struct.Bool)
					report_error("Greska: Element unutar read mora biti int, char ili bool tipa: ", singleStatement);
				
				

			// ovo je provera da li samo polje recorda stoji na levoj strani assign, ali tu
			// proveru cu da ubacim iznad u Dsg

//			if (objDsg.getKind() == Obj.Fld)
//				report_error("Greska: Element sa imenom: " + designatorStatement.getDesignator().getDsgIdent() + " je polje recorda, ne sme stajati samo! ", designatorStatement);
//					

    	}
    	else { // ako je bilo record.polje.polje...
    		
    		if(lastRecordField != null) { // u slucaju da bila greska kod provere polja
    			
    			// prvo provera da li to moze da stoji sa leve strane jednakosti
    			
				if (lastRecordField.getType().getKind() == Struct.Array && adresirano != true)
					report_error("Greska4: Element sa imenom: " + lastRecordField.getName() + " je ime niza, mora se adresirati! ", singleStatement);
					
				
				// provera za tip
				
				if (lastRecordField.getType().getKind() != Struct.Array) {
					
					if(lastRecordField.getType().getKind() != Struct.Int && lastRecordField.getType().getKind() != Struct.Char && lastRecordField.getType().getKind() != Struct.Bool)
						report_error("Greska: Element unutar read mora biti int, char ili bool tipa: ", singleStatement);
					
				} else 
					if(lastRecordField.getType().getElemType().getKind() != Struct.Int && lastRecordField.getType().getElemType().getKind() != Struct.Char && lastRecordField.getType().getElemType().getKind() != Struct.Bool)
						report_error("Greska: Element unutar read mora biti int, char ili bool tipa: ", singleStatement);
					
				
    		}
    		dsgZbir = 0;
    	}
    	
    	dsgSmena = 0; // msm da je nepotrebno
    	
    }
    
    //za condition
    
    public void visit(CondFact1 condFact) {
    	
    	dsgZbir = 0;
    	
    	if(condFact.getExpr().struct.getKind() != Struct.Bool)
    		report_error("Greska: Izraz unutar if naredbe mora biti bool! ", condFact);
    	
    }
    
    public void visit(CondFact2 condFact) {
    	
    	dsgZbir = 0;
    	
    	if(condFact.getExpr().struct.getKind() != condFact.getExpr1().struct.getKind())
    		report_error("Greska: Izrazi koji se porede unutar if naredbe moraju biti istog tipa!", condFact);
    	else if(lastArrays[1] == null)
    		report_error("Greska: Izrazi koji se porede unutar if naredbe moraju biti istog tipa! ", condFact);
    	else if(lastArrays[0].getType().getKind() == Struct.Array && condNEorE == false)
    		report_error("Greska: Reference se mogu porediti samo pomocu == i != .", condFact);
    	
    
    	//morao sam proveru da li su nizovi sa ovim lastIsArray, jer sam radio tako da expr uzima struct elementa niza a ne Array
    	
    	
    	condNEorE = false;	
    }
    
    public void visit(Relop1 relop) {
    	
    	condNEorE = true;
    	
    }
    
    public void visit(Relop2 relop) {
    	
    	condNEorE = true;
    	
    }
    
    public void visit(SqrdOption1 sqrdOption) {
    	
    	sqrdFormPars = true;
   
    }
    
    public void visit(SqrdOption2 sqrdOption) {
    	
    	sqrdFormPars = false;
    	
    }
    
    public void visit(FormPars formPars) {
    	
//    	if(sqrdFormPars == true)
//    		report_info("parametar niz : " + formPars.getVarName() + " tipa : " + formPars.getType().struct.getKind(), formPars);
//    	else
//    		report_info("parametar prom : " + formPars.getVarName() + " tipa : " + formPars.getType().struct.getKind(), formPars);
//    	
    	
    	if(sqrdFormPars == false)
    		methFormPars.get(methFormPars.size() - 1).add(0, new Obj(Obj.Var, formPars.getVarName(), new Struct(formPars.getType().struct.getKind())));
    	else
    		methFormPars.get(methFormPars.size() - 1).add(0, new Obj(Obj.Var, formPars.getVarName(), new Struct(Struct.Array, formPars.getType().struct)));
    
    	for(Obj o : methFormPars.get(methFormPars.size() - 1))
    		if(MyTab.currentScope().findSymbol(o.getName()) == null)
    			MyTab.insert(o.getKind(), o.getName(), o.getType());
    		else 
    			report_error("Greska1 Element sa imenom: " + o.getName() + " je vec deklarisan! ", null);
    		
    	
    }
    
    public void visit(FormParsList1 formParsList) {
    	
//    	if(sqrdFormPars == true)
//    		report_info("parametar niz : " + formParsList.getVarName() + " tipa : " + formParsList.getType().struct.getKind(), formParsList);
//    	else
//    		report_info("parametar prom : " + formParsList.getVarName() + " tipa : " + formParsList.getType().struct.getKind(), formParsList);
//    	
    	if(sqrdFormPars == false)
    		methFormPars.get(methFormPars.size() - 1).add(new Obj(Obj.Var, formParsList.getVarName(), new Struct(formParsList.getType().struct.getKind())));
    	else
    		methFormPars.get(methFormPars.size() - 1).add(new Obj(Obj.Var, formParsList.getVarName(), new Struct(Struct.Array, formParsList.getType().struct)));
    }
    
    public void visit(ActPars actPars) {
    	
    	actParsTypes.add(0, actPars.getExpr().struct);
    }
    
    public void visit(ExprList1 exprList) {
    	
    	actParsTypes.add(exprList.getExpr().struct);
    	
    }
    
    public void visit(LPARENpars lparen) {
    	actParsNow = true;
    }
    
    public void visit(RPARENpars rparen) {
    	actParsNow = false;
    }
    
    public void visit(AddopTermList1 atl) {
    	
    	//report_info(" + " + atl.getTerm().struct.getKind(), atl);
    	
//    	if(atl.getTerm().struct == null) {
//    		report_error("OVDEEEEE", atl);
//    		return;
//    	}
    	
    	if(atl.getTerm().struct.getKind() != Struct.Int)
    		report_error("Greska3: Svi elementi u racunanju izraza moraju biti tipa int : ", atl);
    	
    	termMustInt = true;
    	
    }
    
    public void visit(MinusOption1 minusOpt) {
    	
    	termMustInt = true;
    }
    
    public void visit(RETURNhelp ret) {
    	
    	actParsNow = true;
    	
    }
    
    public void visit(SEMIhelp semi) {
    	
    	actParsNow = false;
    	
    }
    
    public void visit(Factor7 factor) {
    	
    	if(factor.getExpr().struct.getKind() != Struct.Int)
    		report_error("Greska: Izraz unutar [] mora biti tipa int!", factor);
    	
    	factor.struct = new Struct(Struct.Array, factor.getType().struct); // return nikad nece biti array jer povratni tip ne moze array
    
    	factorSmena = 7;
    }
    
    private Obj findSviObj(String name) {
    	for(Obj o : sviObj)
    		if(o.getName().equals(name))
    			return o;
    	return null;
    }
    
    
    public boolean passed(){
    	return !errorDetected;
    }
    
}
