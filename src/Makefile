CLASSPATH="../lib/antlr-3.5.2-complete.jar"
GNAME= ul
GSRC= $(GNAME).g

all: grammar compile

grammar: $(GSRCS)
		java -cp $(CLASSPATH) org.antlr.Tool -fo . $(GSRC) 

compile:
		javac -cp $(CLASSPATH) *.java

clean:
		rm *.class $(GNAME)Lexer.java $(GNAME)Parser.java $(GNAME).tokens
