@ECHO OFF
java Main < input\Test1.in > R1.out
FC output\Test1.out R1.out

java Main < input\Test2.in > R2.out
FC output\Test2.out R2.out

java Main < input\Test3.in > R3.out
FC output\Test3.out R3.out

java Main < input\Test4.in > R4.out
FC output\Test4.out R4.out

java Main < input\Test5.in > R5.out
FC output\Test5.out R5.out

java Main < input\Test6.in > R6.out
FC output\Test6.out R6.out
