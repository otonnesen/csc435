/*
 * Compiler.java
 *
 * A starting place for the unnamed language compiler for CSC 435/535
 *
 */

import org.antlr.runtime.*;
import java.io.*;

import ast.Program;
import visitor.PrettyPrintVisitor;
import visitor.TypeCheckVisitor;
import visitor.IRVisitor;
import codegen.JasminVisitor;

public class Compiler {
	public static void main (String[] args) throws Exception {
		ANTLRInputStream input;

		if (args.length == 0 ) {
			System.out.println("Usage: Compiler filename.ul");
			return;
		}
		else {
			input = new ANTLRInputStream(new FileInputStream(args[0]));
		}

		// The name of the grammar here is "ulNoActions",
		// so ANTLR generates ulNoActionsLexer and ulNoActionsParser
		ulLexer lexer = new ulLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ulParser parser = new ulParser(tokens);
		Program p = new Program();

		try {
			p = parser.program();
		}
		catch (RecognitionException e )	{
			// A lexical or parsing error occured.
			// ANTLR will have already printed information on the
			// console due to code added to the grammar.  So there is
			// nothing to do here.
		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		// PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		// p.accept(ppv);
		TypeCheckVisitor tcv = new TypeCheckVisitor();
		p.accept(tcv);
		IRVisitor irv = new IRVisitor(args[0].split("\\.")[0]);
		p.accept(irv);
		// System.out.printf("%s\n", irv.getProgram());
		JasminVisitor jv = new JasminVisitor(irv.getProgram());
		System.out.printf("%s\n", jv.toString());
	}
}
