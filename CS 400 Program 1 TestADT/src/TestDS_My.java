//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Program 1 Implement and Test an ADT
// Files: DS_My.java, TestDS_My.java, DataStrucureADT.java
// Course: Fall 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Deppeler
// Lecture Number: 001
//
// Description of Program: This program implements and test an ADT. The program also tests the
// implementations of other ADT that people have written in previous semesters. The program utilizes
// an interface an Junit tests to test peoples implementations in a generic way.
//
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


// TO TEST A DATA STRUCTURE CLASS:
//
// for each data structure class file you wish to test:
// 1. create a test class (like this one)
// 2. edit the actual type being created (line 16)
// 3. run this test class
// 4. OR, configure Eclipse project to run all tests
// Eclipse: Run->Run Configurations->"Run All Tests..."

@SuppressWarnings("rawtypes")
public class TestDS_My extends DataStructureADTTest {

  // the return type must be the name of the data structure class you are testing
  @Override
  protected DataStructureADT createInstance() {
    return new DS_My();
  }

}
