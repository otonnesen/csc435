grammar ul;

options {
	backtrack=true;
}

@header {
	import ast.Expression;
	import ast.ExpressionArrayAccess;
	import ast.ExpressionFunctionCall;
	import ast.ExpressionIdentifier;
	import ast.Literal;
	import ast.LiteralBoolean;
	import ast.LiteralCharacter;
	import ast.LiteralFloat;
	import ast.LiteralInteger;
	import ast.LiteralString;
	import ast.ExpressionParenthesis;
	import ast.ExpressionList;
	import ast.ExpressionOperation;
	import ast.ExpressionTimes;
	import ast.ExpressionPlusMinus;
	import ast.ExpressionLessThan;
	import ast.ExpressionIsEqual;
	import ast.Statement;
	import ast.StatementArrayAssignment;
	import ast.StatementAssign;
	import ast.StatementEmpty;
	import ast.StatementExpression;
	import ast.StatementIf;
	import ast.StatementPrint;
	import ast.StatementPrintln;
	import ast.StatementReturn;
	import ast.StatementWhile;
	import ast.Block;
	import ast.VariableDeclaration;
	import ast.Function;
	import ast.FunctionBody;
	import ast.FunctionDeclaration;
	import ast.FormalParameters;
	import ast.Parameter;
	import type.Type;
	import type.TypeArray;
	import type.TypeBoolean;
	import type.TypeCharacter;
	import type.TypeFloat;
	import type.TypeInteger;
	import type.TypeString;
	import type.TypeVoid;

	import ast.Program;
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
				:	t = type id = exprId OPENPAREN (fp = formalParams)? CLOSEPAREN
				{
					fd = new FunctionDeclaration(t, id, fp);
				}
				;

formalParams	returns [FormalParameters fp]
				@init {
					fp = new FormalParameters();
				}
				:	t = compoundType e = exprId
				{
					fp.addParameter(new Parameter(t, e));
				}
				(COMMA t = compoundType e = exprId
				{
					fp.addParameter(new Parameter(t, e));
				})*
				;

compoundType	returns [Type t]
				:	tp = type { t = tp; }
				|	tp = type OPENBRACKET i = INT_CONST CLOSEBRACKET
				{
					int size = Integer.parseInt(i.getText());
					t = new TypeArray(t, size);
				}
				;

functionBody	returns [FunctionBody fb]
				@init {
					fb = new FunctionBody();
				}
				:	OPENBRACE (vd = varDecl { fb.addVariableDeclaration(vd); } )*
				(stmt = statement { fb.addStatement(stmt); } )* CLOSEBRACE
				;

varDecl			returns [VariableDeclaration v]
				:	t = compoundType e = exprId SEMICOLON
				{
					v = new VariableDeclaration(t, e);
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
				:	IF OPENPAREN e = expression CLOSEPAREN ib = block (ELSE eb = block)?
				{
					i = new StatementIf(e, ib, eb);
				}
				;

stmtWhile		returns [StatementWhile w]
				:	WHILE OPENPAREN expression CLOSEPAREN block
				;

stmtPrint		returns [StatementPrint p]
				:	PRINT e = expression SEMICOLON
				{
					p = new StatementPrint(e);
				}
				;

stmtPrintln		returns [StatementPrintln pl]
				:	PRINTLN e = expression SEMICOLON
				{
					pl = new StatementPrintln(e);
				}
				;

stmtReturn		returns [StatementReturn r]
				:	RETURN (e = expression)? SEMICOLON
				{
					r = new StatementReturn(e);
				}
				;

stmtAssign		returns [StatementAssign a]
				:	id = exprId EQUALS e = expression SEMICOLON
				{
					a = new StatementAssign(id, e);
				}
				;

stmtArrayAssign	returns [StatementArrayAssignment aa]
				:	eaa = exprArrayAccess EQUALS e = expression SEMICOLON
				{
					aa = new StatementArrayAssignment(eaa, e);
				}
				;

expression		returns [Expression e]
				:	o = exprOperation { e = o; }
				|	a = atom { e = a; }
				;

atom			returns [Expression e]
				:	aa = exprArrayAccess { e = aa; }
				|	fc = exprFuncCall { e = fc; }
				|	id = exprId { e = id; }
				|	l = literal { e = l; }
				|	p = exprParen { e = p; }
				;

exprOperation	returns [Expression e]
				:	eq = exprIsEqual { e = eq; }
				|	lt = exprLessThan {e = lt; }
				|	pm = exprPlusMinus { e = pm; }
				|	t = exprTimes { e = t; }
				;

exprIsEqual		returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	e1 = exprLessThan { it = e1; }
				(IS_EQUAL e2 = exprLessThan
				{ it = new ExpressionIsEqual(it, e2); })*
				;

exprLessThan	returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	e1 = exprPlusMinus { it = e1; }
				(LESS_THAN e2 = exprPlusMinus
				{ it = new ExpressionLessThan(it, e2); })*
				;

exprPlusMinus	returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	e1 = exprTimes { it = e1; }
				((PLUS|MINUS) e2 = exprTimes
				{ it = new ExpressionPlusMinus(it, e2); })*
				;

exprTimes		returns [Expression e]
				@init {
					Expression it = null;
				}
				@after {
					e = it;
				}
				:	e1 = atom { it = e1; }
				(TIMES e2 = atom
				{ it = new ExpressionTimes(it, e2); })*
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
					i = new ExpressionIdentifier(id);
				}
				;

exprParen		returns [ExpressionParenthesis p]
				:	OPENPAREN e = expression CLOSEPAREN
				{
					p = new ExpressionParenthesis(e);
				}
				;

exprList		returns [ExpressionList l]
				@init {
					l = new ExpressionList();
				}
				:	e = expression { l.addExpression(e); }
				(e = exprMore { l.addExpression(e); })*
				|
				;

exprMore		returns [Expression e]
				:	COMMA exp = expression { e = exp; }
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
				:	e = TRUE
				{
					boolean v = Boolean.valueOf(e.getText());
					b = new LiteralBoolean(v);
				}
				|	e = FALSE
				{
					boolean v = Boolean.valueOf(e.getText());
					b = new LiteralBoolean(v);
				}
				;

literalChar		returns [LiteralCharacter c]
				:	e = CHAR_CONST
				{
					char v = e.getText().charAt(1);
					c = new LiteralCharacter(v);
				}
				;

literalFloat		returns [LiteralFloat f]
				:	e = FLOAT_CONST
				{
					float v = Float.valueOf(e.getText());
					f = new LiteralFloat(v);
				}
				;

literalInt		returns [LiteralInteger i]
				:	e = INT_CONST
				{
					int v = Integer.parseInt(e.getText());
					i = new LiteralInteger(v);
				}
				;

literalString		returns [LiteralString s]
				:	e = STRING_CONST
				{
					String v = e.getText().substring(1, e.getText().length()-1);
					s = new LiteralString(v);
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

WS				:	(' '|'\n'|'\t')+ { $channel = HIDDEN; }
				;

COMMENT			:	'//' ~'\n'* '\n' { $channel = HIDDEN; }
				;