#Class - Interpreter
##Author: Aunn Raza | BSCS2B | 01248


Interpreter Class is a generic declarative language interpreter which caters three data types i.e. String, Integer and Floating points. It reads the given code file line by line and executes accordingly.

Currently, Interpreter Class performs three type of operations that are
•	Variable Declaration:	Variables can be declared by the “LET” command. For example “let x =2”, “Let str=”HelloWorld” “
•	Printing Output:		User can print a String or variable to standard output stream by using the “PRINT” command. For example “print x”, “print ‘Hey, This is Print!’ ”
•	Arithmetic Operations	User can perform arthimetic operations on variables and constants just line a math equation and assign its value to any variable. Operations follows the rules as does JAVA i.e. String addition will cause two strings to concatenate. For example “x= y+2*5”

##Error Handling

Upon any invalid command or erroneous operation, Interpreter class throws exception and breaks the code execution. Different kinds of errors are catered from which few are:
•	Left hand value of assignment (=) operator should be variable
•	Only one variable can exist on left hand side of assignment operator
•	Undeclared variables
•	Variable name should start with alphabets only
•	Each command should be on new line
•	One declaration per line only

##Testing (JUnit Test Cases)
 
Validation and Correctness testing is performed by JUnit testing class. Different test cases were developed for each data type i.e. string, integer and floating point.
In each test case, different test file is loaded into ArrayList as sequence of read lines and passed to the interpreter’s run function.


