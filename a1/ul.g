grammar ul;

options {
	backtrack=true;
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

program			:	function+
				;

function		:	functionDecl functionBody
				;

functionDecl	:	type ID OPENPAREN formalParameters CLOSEPAREN
				;

formalParameters:	compoundType ID moreFormals*
				|
				;

moreFormals		:	COMMA compoundType ID
				;

compoundType	:	type
				|	type OPENBRACKET INT_CONST CLOSEBRACKET
				;

functionBody	:	OPENBRACE varDecl* statement* CLOSEBRACE
				;

varDecl			:	compoundType ID SEMICOLON
				;

statement		:	stmtEmpty
				|	stmtExpr
				|	stmtIf
				|	stmtWhile
				|	stmtPrint
				|	stmtPrintln
				|	stmtReturn
				|	stmtAssign
				|	stmtArrayAssign
				;

stmtEmpty		:	SEMICOLON
				;

stmtExpr		:	expression SEMICOLON
				;

stmtIf			:	IF OPENPAREN expression CLOSEPAREN block (ELSE block)?
				;

stmtWhile		:	WHILE OPENPAREN expression CLOSEPAREN block
				;

stmtPrint		:	PRINT expression SEMICOLON
				;

stmtPrintln		:	PRINTLN expression SEMICOLON
				;

stmtReturn		:	RETURN expression? SEMICOLON
				;

stmtAssign		:	ID EQUALS expression SEMICOLON
				;

stmtArrayAssign	:	exprArrayAccess EQUALS expression SEMICOLON
				;

/* TODO */
expression		:	/*exprOperation
				|*/	exprArrayAccess
				|	exprFuncCall
				|	exprId
				|	literal
				|	exprParen
				;

exprOperation	:	exprIsEqual
				|	exprLessThan
				|	exprPlus
				|	exprMinus
				|	exprTimes
				;

exprIsEqual		:	expression IS_EQUAL expression
				;

exprLessThan	:	expression LESS_THAN expression
				;

exprPlus		:	expression PLUS expression
				;

exprMinus		:	expression MINUS expression
				;

exprTimes		:	expression TIMES expression
				;

exprArrayAccess	:	ID OPENBRACKET expression CLOSEBRACKET
				;

exprFuncCall	:	ID OPENPAREN exprList CLOSEPAREN
				;

exprId			:	ID
				;

exprParen		:	OPENPAREN expression CLOSEPAREN
				;

exprList		:	expression exprMore*
				|
				;

exprMore		:	COMMA expression
				;

block			:	OPENBRACE statement* CLOSEBRACE
				;

type			:	INT
				|	FLOAT
				|	CHAR
				|	STRING
				|	BOOLEAN
				|	VOID
				;

literal			:	STRING_CONST
				|	INT_CONST
				|	FLOAT_CONST
				|	CHAR_CONST
				|	TRUE
				|	FALSE
				;

op				:	IS_EQUAL
				|	LESS_THAN
				|	PLUS
				|	MINUS
				|	TIMES
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
