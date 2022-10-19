package rs.ac.bg.etf.pp1;

import java.util.HashMap;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MyDumpSymbolTableVisitor extends SymbolTableVisitor{

	protected StringBuilder izlaz = new StringBuilder();
	protected final String idn = "   ";
	protected StringBuilder currIdn = new StringBuilder();
		
	/* (non-Javadoc)
	 * @see rs.etf.pp1.symboltable.test.SymbolTableVisitor#visitObjNode(symboltable.Obj)
	 */
	@Override
	public void visitObjNode(Obj objToVisit) {
		switch (objToVisit.getKind()) {
			case Obj.Con:  izlaz.append("Con "); break;
			case Obj.Var:  izlaz.append("Var "); break;
			case Obj.Type: izlaz.append("Type "); break;
			case Obj.Meth: izlaz.append("Meth "); break;
			case Obj.Fld:  izlaz.append("Fld "); break;
			case Obj.Prog: izlaz.append("Prog "); break;
		}
		izlaz.append(objToVisit.getName());
		izlaz.append(": ");
		if ((Obj.Var == objToVisit.getKind()) && "this".equalsIgnoreCase(objToVisit.getName())) {
			izlaz.append("");
		}
		else {
			objToVisit.getType().accept(this);
		}
		
		dodatak();
		
		izlaz.append(", address = ");
		izlaz.append(objToVisit.getAdr());
		izlaz.append(", level = ");
		izlaz.append(objToVisit.getLevel() + " ");
		izlaz.append(", fpPos = ");
		izlaz.append(objToVisit.getFpPos() + " ");
		
		
		if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth) {
			izlaz.append("\n");
			funkc();
		}

		for (Obj o : objToVisit.getLocalSymbols()) {
			izlaz.append(currIdn.toString());
			o.accept(this);
			izlaz.append("\n");
		}
		
		if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth) {
			funkc2();
		}
	}

	/* (non-Javadoc)
	 * @see rs.etf.pp1.symboltable.test.SymbolTableVisitor#visitStructNode(symboltable.Struct)
	 */
	@Override
	public void visitStructNode(Struct structToVisit) {
		switch (structToVisit.getKind()) {
		case Struct.None:
			izlaz.append("notype");
			break;
		case Struct.Int:
			izlaz.append("int");
			break;
		case Struct.Char:
			izlaz.append("char");
			break;
		case Struct.Bool:
			izlaz.append("bool");
			break;
		case Struct.Array:
			izlaz.append("Arr of ");
			pomoc(structToVisit.getElemType().getKind());
			/*switch (structToVisit.getElemType().getKind()) {
			case Struct.None:
				izlaz.append("notype");
				break;
			case Struct.Int:
				izlaz.append("int");
				break;
			case Struct.Char:
				izlaz.append("char");
				break;
			case Struct.Bool:
				izlaz.append("bool");
				break;
			case Struct.Class:
				izlaz.append("Class");
				break;
			}*/
			break;
		case Struct.Class:
			izlaz.append("Class [");
			for (Obj obj : structToVisit.getMembers()) {
				obj.accept(this);
			}
			izlaz.append("]");
			break;
		}

	}
	
	private void dodatak() {
		
	}
	
	public void pomoc(int i) {
		switch (i) {
		case Struct.None:
			izlaz.append("notype");
			break;
		case Struct.Int:
			izlaz.append("int");
			break;
		case Struct.Char:
			izlaz.append("char");
			break;
		case Struct.Bool:
			izlaz.append("bool");
			break;
		case Struct.Class:
			izlaz.append("Class");
			break;
		}
	}

	public String getOutput() {
		return izlaz.toString();
	}
	
	protected void funkc() {
		currIdn.append(idn);
	}
	
	protected void funkc2() {
		if (currIdn.length() > 0)
			currIdn.setLength(currIdn.length()-idn.length());
	}
	
	
	/* (non-Javadoc)
	 * @see rs.etf.pp1.symboltable.test.SymbolTableVisitor#visitScopeNode(symboltable.Scope)
	 */
	@Override
	public void visitScopeNode(Scope scope) {
		for (Obj o : scope.values()) {
			o.accept(this);
			izlaz.append("\n");
		}
	}

}
