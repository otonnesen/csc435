# CSC 435 - Compiler Construction

The major aspects of constructing a compiler from scratch.
https://heat.csc.uvic.ca/coview/course/2020011/CSC435

## Topics
- Lexing and regular expressions
- Parsing and context-free grammars
- Abstract Syntax Trees
- Type checking and semantic analysis
- Register allocation
- Code generation
- Code optimization

## Running
This project requires ANTLR3 to generate JVM bytecode.

Additionally, it needs Jasmin, Polyglot, and Soot to compile and run on the JVM.

Build with `make`.

From there, pretty print, generate IR, or generate JVM bytecode with `./run <file.ul> [print|ir|jasmin]`.

If the libraries are available in ./lib, then `./compile <file.ul>` will generate a `.class` file that can be run with `java`.
