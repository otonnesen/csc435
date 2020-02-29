cp ~/tests/a3/factorial.ul .
cp ~/tests/a3/if.ul .
cp ~/tests/a3/ar.ul .
cp ~/tests/a3/expr.ul .
cp ~/tests/a3/towersOfHanoi.ul .
cp ~/csc435/sandbox/codegen/codegen .
chmod 755 codegen

echo
echo "Testing factorial"
echo
java Compiler factorial.ul > factorial.ir
./codegen --file=factorial.ir > fact.j && java jasmin.Main fact.j
echo "Either this"
java factorial.ul
echo "Or this "
java factorial
echo "Done factorial"

echo
echo "Testing if"
echo
java Compiler if.ul > if.ir
./codegen --file=if.ir > if.j && java jasmin.Main if.j
echo "Either this"
java if.ul
echo "Or this "
java if
echo "Done if"

echo
echo "Testing an array"
echo
java Compiler ar.ul > ar.ir
./codegen --file=ar.ir > ar.j && java jasmin.Main ar.j
echo "Either this"
java ar.ul
echo "Or this "
java ar
echo "Done array"

echo
echo "Testing an expression"
echo
java Compiler expr.ul > expr.ir
./codegen --file=expr.ir > expr.j && java jasmin.Main expr.j
echo "Either this"
java expr.ul
echo "Or this "
java expr
echo "Done expression"

echo
echo "Testing Towers of Hanoi"
echo
java Compiler towersOfHanoi.ul > towersOfHanoi.ir
./codegen --file=towersOfHanoi.ir > towersOfHanoi.j && java jasmin.Main towersOfHanoi.j
echo "Either this"
java towersOfHanoi.ul
echo "Or this "
java towersOfHanoi
echo "Done towers"

echo
echo "All done!"