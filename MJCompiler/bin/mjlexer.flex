package rs.ac.bg.etf.pp1;

// import sekcija

import java_cup.runtime.Symbol;

%%

// sekcija direktiva

%{ // kod sekcija

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%} // kraj sekcije koda

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

// sekcija regularnih izraza - dodao

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"break"     { return new_symbol(sym.BREAK, yytext()); }
"class"     { return new_symbol(sym.CLASS, yytext()); }
"enum"      { return new_symbol(sym.ENUM, yytext()); }
"else"      { return new_symbol(sym.ELSE, yytext()); }
"const"     { return new_symbol(sym.CONST, yytext()); }
"if"        { return new_symbol(sym.IF, yytext()); }
"do"     	{ return new_symbol(sym.DO, yytext()); }
"while"     { return new_symbol(sym.WHILE, yytext()); }
"new"     	{ return new_symbol(sym.NEW, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"     	{ return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"extends"   { return new_symbol(sym.EXTENDS, yytext()); }
"continue"  { return new_symbol(sym.CONTINUE, yytext()); }
"this"     	{ return new_symbol(sym.THIS, yytext()); }
"super"     { return new_symbol(sym.SUPER, yytext()); }
"goto"     	{ return new_symbol(sym.GOTO, yytext()); }
"record"    { return new_symbol(sym.RECORD, yytext()); }

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MULTIPLY, yytext()); }
"/" 		{ return new_symbol(sym.DIVIDE, yytext()); }
"%" 		{ return new_symbol(sym.MODUO, yytext()); }
"==" 		{ return new_symbol(sym.EQ, yytext()); }
"!=" 		{ return new_symbol(sym.NEQ, yytext()); }
">" 		{ return new_symbol(sym.GRT, yytext()); }
">=" 		{ return new_symbol(sym.GRTEQ, yytext()); }
"<" 		{ return new_symbol(sym.LESS, yytext()); }
"<=" 		{ return new_symbol(sym.LESSEQU, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"=" 		{ return new_symbol(sym.EQUALS, yytext()); }
"++" 		{ return new_symbol(sym.PLUSINCR, yytext()); }
"--" 		{ return new_symbol(sym.MINUSDECR, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
":" 		{ return new_symbol(sym.TWODOTS, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"[" 		{ return new_symbol(sym.LSQRD, yytext()); }
"]" 		{ return new_symbol(sym.RSQRD, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }

"??"			{ return new_symbol(sym.DOUBLE_QUESTION, yytext()); }

<YYINITIAL> "//" 	{ yybegin(COMMENT); }
<COMMENT> .      	{ yybegin(COMMENT); }
<COMMENT> "\r\n" 	{ yybegin(YYINITIAL); }

[0-9]+  { return new_symbol(sym.NUMCONST, new Integer (yytext())); }
"true" | "false"  { return new_symbol(sym.BOOLCONST, yytext()); }
([a-z]|[A-Z])[a-zA-Z0-9_]* 	{return new_symbol(sym.IDENT, yytext()); }
"'" [ -~]  "'"  { return new_symbol(sym.CHARCONST, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline + 1) + "i koloni " + (yycolumn + 1)); }






