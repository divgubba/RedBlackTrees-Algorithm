# RedBlackTree Algorithm

- IDE used: IntelliJ IDEA, compiled .java files through IntelliJ, executed through terminal on MacOS, using java command in “Execute command line - “ mentioned in the README.txt file 
- Arguments taken in command line arguments are the full part for input and output file. 
- Version of java used is java 8
- Arguments for file names are passed in through command line from intelliJ or through terminal 
- All methods should work normally
- Program works for test cases given in instructions
- output is given as “true” or “false”, not upper case letter “True” or “False”
- Program works for test cases provided in the instructions, for Insert operation if there is not data assigned to it, will print out “Error in Line: Insert”
- Error checking is done for if the file exists, if output file does not exist, will create one.
- Error checking also done for whether 2 arguments are passed in. 


Execute this command line - 
java Main "/Users/dgubba/IntelliJ-JavaProjects/Project4RedBlackTrees/RBTInpFile.txt" "/Users/dgubba/IntelliJ-JavaProjects/Project4RedBlackTrees/RBTOutputFile.txt"

SAMPLE RUNS
————————————————————————————————————
Test 1
Input File:
String
Insert:Computer
Insert:Keyboard
Contains:Keyboard
Insert:78.5
Insert:Mouse
Insert:Tablet
PrintTree

Output File:
true
true
true
true
true
true
Computer 78.5 Mouse *Keyboard *Tablet 
————————————————————————————————————

————————————————————————————————————
Test 2
Input File:
Integer
Insert:98
Insert:-68
Insert:55
Insert:45
PrintTree
Contains:45
Insert:84
Insert:32
Insert:132
Insert:45
PrintTree
Insert
hih

Output File:
true
true
true
true
55 -68 *45 98 
true
true
true
true
false
55 32 *-68 *45 98 *84 *132 
Error in Line: Insert
Error in line: hih
————————————————————————————————————


————————————————————————————————————
Test 3
Input File:
Students

Output File:
Can only accept 2 objects, String or Integer
————————————————————————————————————

————————————————————————————————————
Test 4
Input File:
String
Insert:Ana
Insert:Owen
Insert:Pete
Insert:Leo
PrintTree
Contains:Owen
Insert:Nick
Insert:Maya
Insert:Leo
PrintTree

Output File:
true
true
true
true
Owen Ana *Leo Pete 
true
true
true
false
Owen *Leo Ana Nick *Maya Pete 
————————————————————————————————————

————————————————————————————————————
Test 5
Input File:
Integer
Insert:-42
Insert:60
Insert:57
Contains:57
Insert:57
PrintTree
Insert:1
Insert:-24
PrintTree
jd;klfjdk;f

Output File:
true
true
true
true
false
57 *-42 *60 
true
true
57 -24 *-42 *1 60 
Error in line: jd;klfjdk;f
————————————————————————————————————
