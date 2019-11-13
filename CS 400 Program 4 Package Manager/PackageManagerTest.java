//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Program 4 Package Manager
// Files: PackageManager.java, Graph.java, PackageManagerTest.java, GraphTest.java
// Course: Fall 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Deppeler
// Lecture Number: 001
//
// Description of Program: This program implements graphs to manage packages with different
// operations/functions.
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
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This Class Tests the Package Manager class and all of it correlating functions/operations
 * 
 * @author Jacob Brevard
 *
 */
class PackageManagerTest {

  // PackageManager Object to be used in each test
  private PackageManager packageManager;

  /** Initialize empty packageManager to be used in each test */
  @BeforeEach
  public void setUp() throws Exception {
    packageManager = new PackageManager();
  }

  /** Not much to do, just make sure that variables are reset */
  @AfterEach
  public void tearDown() throws Exception {
    packageManager = null;
  }

  /**
   * This tests the parsing of a json file and the construction of the graph.
   * 
   */
  @Test
  void test_001_constructGraph() {
    try {
      packageManager.constructGraph("valid.json");
    } catch (IOException | ParseException e) {
      fail("Should not throw an exception");
    }
  }

  /**
   * This tests the getAllPackages method to make sure it returns a complete set of packages.
   * 
   */
  @Test
  void test_002_getAllPackages() {
    ArrayList<String> installationOrder = null;
    try {
      packageManager.constructGraph("valid.json");
      installationOrder = (ArrayList<String>) packageManager.getInstallationOrderForAllPackages();
    } catch (IOException | ParseException | CycleException e) {
      fail("Should not throw an exception");
      e.printStackTrace();
    }

    Set<String> allVertices = packageManager.getAllPackages();

    if (allVertices.size() != 6) {
      fail("The number of vertices returned by the get all packages method is incorrect");
    }

    System.out.print(installationOrder);

    // Checks to make sure the correct packages are returned at each index and are in the correct
    // order for the package dependencies.
    if (!installationOrder.get(0).equals("A")) {
      fail("The first item in the list should have been A");
    }

    if (!installationOrder.get(1).equals("B")) {
      fail("The first item in the list should have been B");
    }
  }

  /**
   * This tests the getInstallationOrder method to make sure it returns the correct Installation
   * order of packages.
   * 
   */
  @Test
  void test_003_getInstallationOrder() {
    ArrayList<String> installationOrder = null;
    try {
      packageManager.constructGraph("valid.json");
      installationOrder = (ArrayList<String>) packageManager.getInstallationOrder("F");
    } catch (IOException | ParseException | CycleException | PackageNotFoundException e) {
      fail("Should not throw an exception");
      e.printStackTrace();
    }

    System.out.print(installationOrder);

    // Checks to make sure the correct packages are returned at each index and are in the correct
    // order for the package dependencies.
    if (!installationOrder.get(0).equals("A")) {
      fail("The first item in the list should have been A");
    }


    if (!installationOrder.get(1).equals("F")) {
      fail("The first item in the list should have been F");
    }
  }

  /**
   * This tests the toInstall method to make sure it returns the correct list of packages that still
   * need to be installed.
   * 
   */
  @Test
  void test_004_toInstall() {
    ArrayList<String> installationOrder = null;
    try {
      packageManager.constructGraph("valid.json");
      installationOrder = (ArrayList<String>) packageManager.toInstall("D", "A");
    } catch (IOException | ParseException | CycleException | PackageNotFoundException e) {
      fail("Should not throw an exception");
      e.printStackTrace();
    }

    // Checks to make sure the correct packages are returned at each index and are in the correct
    // order for the package dependencies.
    if (!installationOrder.get(0).equals("D")) {
      fail("The first item in the list should have been D");
    }

    if (!installationOrder.get(1).equals("B")) {
      fail("The first item in the list should have been B");
    }

    if (!installationOrder.get(2).equals("F")) {
      fail("The first item in the list should have been F");
    }

    if (!installationOrder.get(3).equals("G")) {
      fail("The first item in the list should have been G");
    }

    if (!installationOrder.get(4).equals("H")) {
      fail("The first item in the list should have been H");
    }
  }

  /**
   * This tests the getInstallationOrderForAllPackages method to make sure it returns the correct
   * list of the Installation orders to be installed.
   * 
   */
  @Test
  void test_005_getInstallationOrderForAllPackages() {
    ArrayList<String> installationOrder = null;
    try {
      packageManager.constructGraph("shared_dependencies.json");
      installationOrder = (ArrayList<String>) packageManager.getInstallationOrderForAllPackages();
    } catch (IOException | ParseException | CycleException e) {
      fail("Should not throw an exception");
      e.printStackTrace();
    }

    // Checks to make sure the correct packages are returned at each index and are in the correct
    // order for the package dependencies.
    if (!installationOrder.get(0).equals("D")) {
      fail("The first item in the list should have been D");
    }

    if (!installationOrder.get(1).equals("B")) {
      fail("The first item in the list should have been B");
    }

    if (!installationOrder.get(2).equals("C")) {
      fail("The first item in the list should have been C");
    }

    if (!installationOrder.get(3).equals("A")) {
      fail("The first item in the list should have been A");
    }
  }

  /**
   * This tests the getPackageWithMaxDependencies method to make sure it returns the package with
   * the highest number of dependencies.
   * 
   */
  @Test
  void test_006_getPackageWithMaxDependencies() {
    String maxDependencies = null;
    try {
      packageManager.constructGraph("valid.json");
      maxDependencies = packageManager.getPackageWithMaxDependencies();
    } catch (IOException | ParseException | CycleException e) {
      fail("Should not throw an exception");
      e.printStackTrace();
    }

    if (!maxDependencies.equals("D")) {
      fail("The package with the maximum number of dependencies is D");
    }
  }
}
