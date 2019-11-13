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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This Class Tests the Graph class and all of it correlating functions/operations
 * 
 * @author Jacob Brevard
 *
 */
class GraphTest {

  // Graph Object to be used in each test
  private Graph graph;

  /** Initialize empty Graph to be used in each test */
  @BeforeEach
  public void setUp() throws Exception {
    graph = new Graph();
  }

  /** Not much to do, just make sure that variables are reset */
  @AfterEach
  public void tearDown() throws Exception {
    graph = null;
  }

  /**
   * Tests the order of the graph and should return the correct number of vertices.
   * 
   */
  @Test
  void test_001_Order() {
    // Should add to the graph
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");

    // Should not add to the graph, These Vertices are already in the graph
    graph.addVertex("3");
    graph.addVertex("2");
    graph.addVertex(null);

    if (graph.order() != 4) {
      fail("The Order Should Have Been 4, But was Actually: " + graph.order());
    }
  }

  /**
   * Tests the size of the graph and should return the correct number of edges.
   * 
   */
  @Test
  void test_002_Size() {
    // Should add to the graph
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");

    // Should Add the edge to the graph
    graph.addEdge("1", "2");
    graph.addEdge("2", "3");
    graph.addEdge("3", "4");

    // Should not add edges to the graph
    graph.addEdge("2", "3");
    graph.addEdge("3", "5");
    graph.addEdge("100", "50");
    graph.addEdge(null, "2");
    graph.addEdge("1", null);
    graph.addEdge(null, null);

    if (graph.size() != 3) {
      fail("The Order Should Have Been 3, But was Actually: " + graph.size());
    }
  }

  /**
   * Tests the addVertex method to make sure of the proper insertion of vertices.
   * 
   */
  @Test
  void test_003_addVertex() {
    // Should add to the graph
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");
    graph.addVertex("Bob");
    graph.addVertex("Mike");
    graph.addVertex("Ryan");
    graph.addVertex("Tyler");
    graph.addVertex("tyler");
    graph.addVertex("bob");

    // Should Not be added
    graph.addVertex(null);
    graph.addVertex("2");



    if (graph.order() != 10) {
      fail("The graph order should be 8 but is actually: " + graph.order());
    }
  }

  /**
   * Tests the removeVertex method to make sure of the proper removal of vertices.
   * 
   */
  @Test
  void test_004_removeVertex() {
    // Should add to the graph
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");
    graph.addVertex("5");
    graph.addVertex("6");

    if (graph.order() != 6) {
      fail("The number of vertices should be 6 but is actually: " + graph.order());
    }

    graph.removeVertex("3");

    if (graph.order() != 5) {
      fail("The number of vertices should be 5 but is actually: " + graph.order());
    }

    graph.removeVertex("3");

    if (graph.order() != 5) {
      fail("The number of vertices should be 5 but is actually: " + graph.order());
    }

    graph.removeVertex("4");

    graph.removeVertex("2");

    graph.removeVertex("bob");

    graph.removeVertex(null);

    if (graph.order() != 3) {
      fail("The number of vertices should be 3 but is actually: " + graph.order());
    }
  }

  /**
   * Tests the addEdge method to make sure of the proper insertion of edges.
   * 
   */
  @Test
  void test_005_addEdge() {
    // Should add to the graph
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");
    graph.addVertex("5");
    graph.addVertex("6");

    // Should add an edge to the graph
    graph.addEdge("1", "2");
    graph.addEdge("1", "3");
    graph.addEdge("1", "4");
    graph.addEdge("1", "5");
    graph.addEdge("1", "6");

    // Should not add an edge to the graph
    graph.addEdge("1", "6");
    graph.addEdge(null, "6");
    graph.addEdge("1", null);
    graph.addEdge(null, null);
    graph.addEdge("100", "6");
    graph.addEdge("6", "100");
    graph.addEdge("100", "100");

    if (graph.size() != 5) {
      fail("The number of edges in the graph should have been 5 but was actually: " + graph.size());
    }
  }

  /**
   * Tests the removeEdge method to make sure of the proper removal of edges.
   * 
   */
  @Test
  void test_006_removeEdge() {
    // Should add to the graph
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");
    graph.addVertex("5");
    graph.addVertex("6");

    // Should add an edge to the graph
    graph.addEdge("1", "2");
    graph.addEdge("1", "3");
    graph.addEdge("1", "4");
    graph.addEdge("1", "5");
    graph.addEdge("1", "6");

    if (graph.size() != 5) {
      fail("The number of edges in the graph should have been 5 but was actually: " + graph.size());
    }

    graph.removeEdge("1", "3");

    if (graph.size() != 4) {
      fail("The number of edges in the graph should have been 4 but was actually: " + graph.size());
    }

    // Should not remove edge
    graph.removeEdge("1", "3");
    graph.removeEdge("3", "1");
    graph.removeEdge("1", null);
    graph.removeEdge(null, "3");
    graph.removeEdge(null, null);
    graph.removeEdge("1", "100");
    graph.removeEdge("100", "1");
    graph.removeEdge("100", "100");

    if (graph.size() != 4) {
      fail("The number of edges in the graph should have been 4 but was actually: " + graph.size());
    }

    graph.removeEdge("1", "5");

    if (graph.size() != 3) {
      fail("The number of edges in the graph should have been 3 but was actually: " + graph.size());
    }
  }

  /**
   * Tests the getAllVertices method to make sure it returns a correct list with all of the
   * vertices.
   * 
   */
  @Test
  void test_007_getAllVerticies() {
    // Should add to the graph
    for (int i = 0; i < 5000; i++) {
      graph.addVertex("" + i);
    }

    if (graph.order() != 5000) {
      fail("Should have 100 vertices but actually have: " + graph.order());
    }

    if (graph.getAllVertices().size() != 5000) {
      fail("The size of the set returned should have beeen 5000 but was actually: "
          + graph.getAllVertices().size());
    }
  }

  /**
   * Tests the getAdjacentVerticesOf method to make sure a set with adjacent vertices are returned.
   * 
   */
  @Test
  void test_008_getAdjacentVerticiesOf() {
    // Should add to the graph
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");
    graph.addVertex("5");
    graph.addVertex("6");
    graph.addVertex("7");
    graph.addVertex("8");
    graph.addVertex("9");
    graph.addVertex("10");

    // Should add an edge to the graph
    graph.addEdge("1", "2");
    graph.addEdge("1", "3");
    graph.addEdge("1", "4");
    graph.addEdge("1", "5");
    graph.addEdge("1", "6");
    graph.addEdge("1", "7");
    graph.addEdge("1", "8");
    graph.addEdge("1", "9");
    graph.addEdge("1", "10");

    if (graph.getAdjacentVerticesOf("1").size() != 9) {
      fail("The size of the adjacency list should be 9 but is actually: "
          + graph.getAdjacentVerticesOf("1").size());
    }

    if (graph.getAdjacentVerticesOf("2").size() != 0) {
      fail("The size of the adjacent verices list of 2 should be 0 but was actually: "
          + graph.getAdjacentVerticesOf("2").size());
    }
  }

  /**
   * Tests the addVertex method to make sure of the proper insertion of vertices.
   * 
   */
  @Test
  void test_009_endToEndTest_Tests_Multiple_Methods_Together() {
    // Family tree test

    // Children
    graph.addVertex("Jacob");
    graph.addVertex("Morgan");
    graph.addVertex("Heather");

    // Parents
    graph.addVertex("Mike");
    graph.addVertex("Jen");

    // Grand Parents
    graph.addVertex("Diane");
    graph.addVertex("Ron");
    graph.addVertex("Marge");


    graph.addEdge("Jacob", "Mike");
    graph.addEdge("Jacob", "Jen");

    graph.addEdge("Morgan", "Mike");
    graph.addEdge("Morgan", "Jen");

    graph.addEdge("Heather", "Mike");
    graph.addEdge("Heather", "Jen");

    graph.addEdge("Jen", "Ron");
    graph.addEdge("Jen", "Diane");

    graph.addEdge("Mike", "Marge");

    if (graph.size() != 9) {
      fail("The number of edges in the graph should be 9 but is actually: " + graph.size());
    }

    if (graph.order() != 8) {
      fail("The number of verticies in the graph should be 8 but is actually: " + graph.order());
    }

    if (graph.getAdjacentVerticesOf("Jacob").size() != 2) {
      fail("The size of the adjacent vertices list to Jacob should have been 2 but is actually: "
          + graph.getAdjacentVerticesOf("Jacob").size());
    }
  }

  /**
   * Tests the addVertex method to make sure of the proper insertion of vertices.
   * 
   */
  @Test
  void test_010_endToEndTest_Tests_Multiple_Methods_Together() {
    // Should add to the graph
    for (int i = 0; i < 5000; i++) {
      graph.addVertex("" + i);
      if (graph.order() != i + 1) {
        fail("The number of vertices in the graph is incorrect. Should be: " + i);
      }
    }

    if (graph.order() != 5000) {
      fail("The number of vertices in the graph should be 5000 but is actually: " + graph.order());
    }

    // Should add to the graph
    for (int i = 0; i < 5000; i++) {
      graph.removeVertex("" + i);
    }


    if (graph.order() != 0) {
      fail(
          "The number of vertices in the graph should be 0 because we removed all of the vertices. But is actually: "
              + graph.order());
    }
  }
}
