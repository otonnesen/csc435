grammar ul;

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

statement		:	SEMICOLON
				|	expr SEMICOLON /* NOTE: Removing any of these three lines makes the program compile */
				|	IF OPENPAREN expr CLOSEPAREN block (ELSE block)?
				|	WHILE OPENPAREN expr CLOSEPAREN block
				|	PRINT expr SEMICOLON
				|	PRINTLN expr SEMICOLON
				|	RETURN expr? SEMICOLON
				|	ID EQUALS expr SEMICOLON
				/* TODO: Fix this */
				/* |	ID OPENBRACKET expr CLOSEBRACKET EQUALS expr SEMICOLON *//* NOTE: Removing any of these three lines makes the program compile */
				;

expr			:	ID OPENBRACKET expr CLOSEBRACKET/* NOTE: Removing any of these three lines makes the program compile */
				|	ID OPENPAREN exprList CLOSEPAREN
				|	ID
				/* TODO: Fix this */
				/* |	expr op expr */
				|	literal
				|	OPENPAREN expr CLOSEPAREN
				;

exprList		:	expr exprMore*
				;

exprMore		:	COMMA expr
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
