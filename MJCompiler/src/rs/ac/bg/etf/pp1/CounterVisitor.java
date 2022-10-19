package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {

	protected int count = 0;
	
	public int getCount() {
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor {
		
		public void visit(FormPars formPars) {
			++count;
		}
		
		public void visit(FormParsList1 formParsList) {
			++count;
		}
		
	}
	
	public static class VarCounter extends CounterVisitor {
		
		public void visit(VarDecl varDecl) {
			++count;
		}
		
		public void visit(VarDeclList1 varDeclList) {
			++count;
		}
		
	}

}
