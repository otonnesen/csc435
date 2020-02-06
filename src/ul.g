grammar ul;

options {
	backtrack=true;
}

@header {
	import ast.*;
	import type.*;
}

@members
{
protected void mismatch (IntStream input, int ttype, BitSet follow)
		throws RecognitionException
{
		throw new MismatchedTokenException(ttype, input);
}
public Object recoverFromMismatchedSet (IntStream input,
										RecognitionException e,
										BitSet follow)
										throws RecognitionException
	{
		reportError(e);
		throw e;
	}
}

@rulecatch {
	catch (RecognitionException ex) {
		reportError(ex);
		throw ex;
	}
}

/* Parser */

program			returns [Program p]
				@init {
					p = new Program();
				}
				:	(f = function
				{
					p.addFunction(f);
				})+ EOF
				;

function		returns [Function f]
				:	fd = functionDecl fb = functionBody
				{
					f = new Function(fd, fb);
				}
				;

functionDecl	returns [FunctionDeclaration fd]
				:	t = compoundType id = exprId OPENPAREN (fp = formalParams)? CLOSEPAREN
				{
					fd = new FunctionDeclaration(t, id, fp);
				}
				;

compoundType	returns [Type t]
				:	tp = type { t = tp; }
				|	tp = type OPENBRACKET i = INT_CONST CLOSEBRACKET
				{
					int size = Integer.parseInt(i.getText());
					t = new TypeArray(tp, size);
				}
				;

functionBody	returns [FunctionBody fb]
				@init {
					fb = new FunctionBody();
				}
				:	OPENBRACE (vd = varDecl { fb.addVariableDeclaration(vd); } )*
				(stmt = statement { fb.addStatement(stmt); } )* CLOSEBRACE
				;

declaration		returns [Declaration d]
				:	t = compoundType id = exprId
				{
					d = new Declaration(t, id);
				}
				;

formalParams	returns [ArrayList<Declaration> fp]
				@init {
					fp = new ArrayList<Declaration>();
				}
				:	d = declaration
				{
					fp.add(d);
				}
				(COMMA d = declaration
				{
					fp.add(d);
				})*
				;

varDecl			returns [VariableDeclaration v]
				:	d = declaration SEMICOLON
				{
					v = new VariableDeclaration(d);
				}
				;

statement		returns [Statement s]
				:	em = stmtEmpty { s = em; }
				|	e = stmtExpr { s = e; }
				|	i = stmtIf { s = i; }
				|	w = stmtWhile { s = w; }
				|	p = stmtPrint { s = p; }
				|	pl = stmtPrintln { s = pl; }
				|	r = stmtReturn { s = r; }
				|	a = stmtAssign { s = a; }
				|	aa = stmtArrayAssign { s = aa; }
				;

stmtEmpty		returns [StatementEmpty em]
				:	SEMICOLON
				{
					em = new StatementEmpty();
				}
				;

stmtExpr		returns [StatementExpression e]
				:	exp = expression SEMICOLON
				{
					e = new StatementExpression(exp);
				}
				;

stmtIf			returns [StatementIf i]
				:	t = IF OPENPAREN e = expression CLOSEPAREN ib = block (ELSE eb = block)?
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					i = new StatementIf(line, offset, e, ib, eb);
				}
				;

stmtWhile		returns [StatementWhile w]
				:	t = WHILE OPENPAREN e = expression CLOSEPAREN b = block
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					w = new StatementWhile(line, offset, e, b);
				}
				;

stmtPrint		returns [StatementPrint p]
				:	t = PRINT e = expression SEMICOLON
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					p = new StatementPrint(line, offset, e);
				}
				;

stmtPrintln		returns [StatementPrintln pl]
				:	t = PRINTLN e = expression SEMICOLON
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					pl = new StatementPrintln(line, offset, e);
				}
				;

stmtReturn		returns [StatementReturn r]
				:	t = RETURN (e = expression)? SEMICOLON
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					r = new StatementReturn(line, offset, e);
				}
				;

stmtAssign		returns [StatementAssign a]
				:	id = exprId t = EQUALS e = expression SEMICOLON
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					a = new StatementAssign(line, offset, id, e);
				}
				;

stmtArrayAssign	returns [StatementArrayAssignment aa]
				:	eaa = exprArrayAccess t = EQUALS e = expression SEMICOLON
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					aa = new StatementArrayAssignment(line, offset, eaa, e);
				}
				;

expression		returns [Expression e]
				:	exp = exprIsEqual { e = exp; }
				;

atom			returns [Expression e]
				:	aa = exprArrayAccess { e = aa; }
				|	fc = exprFuncCall { e = fc; }
				|	id = exprId { e = id; }
				|	l = literal { e = l; }
				|	p = exprParen { e = p; }
				;

exprIsEqual		returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	left = exprLessThan { it = left; }
				( t = IS_EQUAL right = exprLessThan
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					it = new ExpressionIsEqual(line, offset, it, right);
				})*
				;

exprLessThan	returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	left = exprPlusMinus { it = left; }
				( t = LESS_THAN right = exprPlusMinus
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					it = new ExpressionLessThan(line, offset, it, right);
				})*
				;

exprPlusMinus	returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	left = exprTimes { it = left; }
				(( t = PLUS right = exprTimes
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					it = new ExpressionPlus(line, offset, it, right);
				})
				|( t = MINUS right = exprTimes
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					it = new ExpressionMinus(line, offset, it, right);
				}))*
				;

exprTimes		returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	left = atom { it = left; }
				(t = TIMES right = atom
				{
					int line = t.getLine();
					int offset = t.getCharPositionInLine();
					it = new ExpressionTimes(line, offset, it, right);
				})*
				;

exprArrayAccess	returns [ExpressionArrayAccess aa]
				:	id = exprId OPENBRACKET e = expression CLOSEBRACKET
				{
					aa = new ExpressionArrayAccess(id, e);
				}
				;

exprFuncCall	returns [ExpressionFunctionCall fc]
				:	id = exprId OPENPAREN el = exprList CLOSEPAREN
				{
					fc = new ExpressionFunctionCall(id, el);
				}
				;

exprId			returns [ExpressionIdentifier i]
				:	e = ID
				{
					String id = e.getText();
					int line = e.getLine();
					int offset = e.getCharPositionInLine();
					i = new ExpressionIdentifier(line, offset, id);
				}
				;

exprParen		returns [ExpressionParenthesis p]
				:	OPENPAREN e = expression CLOSEPAREN
				{
					p = new ExpressionParenthesis(e);
				}
				;

exprList		returns [ArrayList<Expression> l]
				@init {
					l = new ArrayList<Expression>();
				}
				:	e = expression { l.add(e); }
				(COMMA e = expression { l.add(e); })*
				|
				;

block			returns [Block b]
				@init {
					b = new Block();
				}
				:	OPENBRACE (s = statement { b.addStatement(s); })* CLOSEBRACE
				;

type			returns [Type t]
				:	BOOLEAN { t = new TypeBoolean(); }
				|	CHAR { t = new TypeCharacter(); }
				|	FLOAT { t = new TypeFloat(); }
				|	INT { t = new TypeInteger(); }
				|	STRING { t = new TypeString(); }
				|	VOID { t = new TypeVoid(); }
				;

literalBool		returns [LiteralBoolean b]
				:	e = (TRUE|FALSE)
				{
					boolean v = Boolean.valueOf(e.getText());
					int line = e.getLine();
					int offset = e.getCharPositionInLine();
					b = new LiteralBoolean(line, offset, v);
				}
				;

literalChar		returns [LiteralCharacter c]
				:	e = CHAR_CONST
				{
					char v = e.getText().charAt(1);
					int line = e.getLine();
					int offset = e.getCharPositionInLine();
					c = new LiteralCharacter(line, offset, v);
				}
				;

literalFloat		returns [LiteralFloat f]
				:	e = FLOAT_CONST
				{
					float v = Float.valueOf(e.getText());
					int line = e.getLine();
					int offset = e.getCharPositionInLine();
					f = new LiteralFloat(line, offset, v);
				}
				;

literalInt		returns [LiteralInteger i]
				:	e = INT_CONST
				{
					int v = Integer.parseInt(e.getText());
					int line = e.getLine();
					int offset = e.getCharPositionInLine();
					i = new LiteralInteger(line, offset, v);
				}
				;

literalString		returns [LiteralString s]
				:	e = STRING_CONST
				{
					String v = e.getText().substring(1, e.getText().length()-1);
					int line = e.getLine();
					int offset = e.getCharPositionInLine();
					s = new LiteralString(line, offset, v);
				}
				;

literal			returns [Literal l]
				:	b = literalBool		{ l = b; }
				|	c = literalChar		{ l = c; }
				|	f = literalFloat	{ l = f; }
				|	i = literalInt		{ l = i; }
				|	s = literalString	{ l = s; }
				;


/* Lexer */

INT				:	'int'
				;

FLOAT			:	'float'
				;

CHAR			:	'char'
				;

STRING			:	'string'
				;

BOOLEAN			:	'boolean'
				;

VOID			:	'void'
				;

IF				:	'if'
				;

ELSE			:	'else'
				;

WHILE			:	'while'
				;

PRINT			:	'print'
				;

PRINTLN			:	'println'
				;

RETURN			:	'return'
				;

TRUE			:	'true'
				;

FALSE			:	'false'
				;

IS_EQUAL		:	'=='
				;

LESS_THAN		:	'<'
				;

PLUS			:	'+'
				;

MINUS			:	'-'
				;

TIMES			:	'*'
				;

EQUALS			:	'='
				;

OPENPAREN		:	'('
				;

CLOSEPAREN		:	')'
				;

OPENBRACE		:	'{'
				;

CLOSEBRACE		:	'}'
				;

OPENBRACKET		:	'['
				;

CLOSEBRACKET	:	']'
				;

SEMICOLON		:	';'
				;

COMMA			:	','
				;

INT_CONST		:	('0'..'9')+
				;

FLOAT_CONST		:	('0'..'9')+'.'('0'..'9')+
				;

CHAR_CONST		:	'\''
					('a'..'z'
				|	'A'..'Z'
				|	'0'..'9'
				|	'!'
				|	','
				|	'.'
				|	':'
				|	'_'
				|	'{'
				|	'}'
				|	' ')
					'\''
				;

STRING_CONST	:	'"'
					('a'..'z'
				|	'A'..'Z'
				|	'0'..'9'
				|	'!'
				|	','
				|	'.'
				|	':'
				|	'_'
				|	'{'
				|	'}'
				|	' ')*
					'"'
				;

ID				:	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
				;

WS				:	(' '|'\n'|'\r'|'\t')+ { $channel = HIDDEN; }
				;

COMMENT			:	'//' ~('\n'|'\r')* ('\n'|'\r') { $channel = HIDDEN; }
				;
