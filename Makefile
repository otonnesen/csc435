CLASSPATH="./lib/antlr-3.5.2-complete.jar"
GNAME= ul
GSRC= $(GNAME).g
SRC= "./src"
BUILD= "./build"

all: grammar compile

grammar: $(GSRCS)
		java -cp $(CLASSPATH) org.antlr.Tool -fo $(SRC) $(SRC)/$(GSRC)

compile:
		javac -cp $(CLASSPATH) $(SRC)/*/*.java $(SRC)/*.java -d $(BUILD)

clean:
		rm $(BUILD)/* $(SRC)/$(GNAME)Lexer.java $(SRC)/$(GNAME)Parser.java $(SRC)/$(GNAME).tokens -r
