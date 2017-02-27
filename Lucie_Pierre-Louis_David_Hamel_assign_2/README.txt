
Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=preference-input.txt -Darg1=regTime-input.txt -Darg2=output.txt  -Darg3=logger level

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.”

[Date: ] -- 2/12/17

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

Student ArrayList, Time complexity for insert is O(1) which is useful because of the amount of inserting into this list

Student scheduledCourses ArrayList, Time Complexity for insert is O(1)

Student coursePreference Array, Time complexity for access is O(1)

Course courseList Array, Time complexity for access is O(1)
-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

