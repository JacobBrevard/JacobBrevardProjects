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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * Filename: Graph.java Project: p4 Author: Jacob Brevard
 * 
 * Directed and unweighted graph implementation
 */
public class Graph implements GraphADT {
  // Variable to keep track of the number of vertices
  private int vertices;

  // Variable to keep track of the number of edges
  private int edges;

  // The Array of Linked Lists to represent the Adjacency List
  private LinkedList<String> adjListArray[];

  /**
   * Default no-argument constructor
   */
  public Graph() {
    adjListArray = new LinkedList[25];
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void addVertex(String vertex) {
    if (vertex == null) {
      return;
    }

    // Iterates through the list and checks to see if the vertex is already in it
    for (int i = 0; i < vertices; i++) {
      if (adjListArray[i].getFirst().equals(vertex)) {
        return;
      }
    }

    // re sizes the array if it is at the capacity
    if (vertices == adjListArray.length) {
      resize();
    }

    // Creates the new vertex with a linked list
    adjListArray[vertices] = new LinkedList<String>();

    // Adds the vertex to be the first node in the linked list
    adjListArray[vertices].add(vertex);

    // increments the number of vertices in the graph
    vertices++;
  }

  /**
   * Resizes the adjList when it is at capacity and copies over the old list. Doubles the adjacency
   * lists capacity.
   */
  private void resize() {
    // Creates the new size of the array which is double the old size
    int newSize = adjListArray.length * 2;

    // Creates the new Array of Linked Lists
    LinkedList<String> newAdjListArray[] = new LinkedList[newSize];

    // Iterates through the old array copying over the elements
    for (int i = 0; i < adjListArray.length; i++) {
      newAdjListArray[i] = adjListArray[i];
    }

    // Sets the old array to be the newly created and copied array
    adjListArray = newAdjListArray;
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void removeVertex(String vertex) {
    if (vertex == null) {
      return;
    }

    // Initializes the Index Variable to -1
    int index = -1;

    // Iterates through the list and checks to see if the vertex is already in it
    for (int i = 0; i < vertices; i++) {
      // If the current vertex equals the vertex to remove then set the index and set that array
      // index to null
      if (adjListArray[i].getFirst().equals(vertex)) {
        index = i;
        adjListArray[i] = null;
      }
    }

    // If the index was changed from -1
    if (index >= 0) {
      // shifts all the vertices over 1 spot after removal
      shiftVertices(index);

      // decrements vertices
      vertices--;

      // Removes any edges in other vertices to this vertex
      for (int i = 0; i < vertices; i++) {
        if (adjListArray[i].contains(vertex)) {
          adjListArray[i].remove(vertex);
          edges--;
        }
      }
    }

    // If the vertex was not found to remove
    return;
  }

  /**
   * Shifts all of the vertices down in the graph after removing one so there are no gaps.
   * 
   * @param index - The index of the vertex that was removed
   */
  private void shiftVertices(int index) {
    // Iterates through the array and shifts all the vertices down 1 position
    for (int i = index; i < vertices - 1; i++) {
      adjListArray[i] = adjListArray[i + 1];
    }

    // Sets the last vertex that was shifted to null
    adjListArray[vertices - 1] = null;
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, add vertex, and add edge, no exception is thrown. If the edge exists in
   * the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
   */
  public void addEdge(String vertex1, String vertex2) {
    if (vertex1 == null || vertex2 == null) {
      return;
    }

    // Initializes the index to 0
    int index = 0;

    // Boolean variables to keep track of the vertex pair is valid
    boolean isValidVertex1 = false;
    boolean isValidVertex2 = false;

    // Iterates through the current list making sure the vertices exist and that the first vertex
    // does not already have a directed edge to vertex 2
    for (int i = 0; i < vertices; i++) {
      if (vertex1.equals(adjListArray[i].getFirst())) {
        // Means the edge does not already exist
        if (!adjListArray[i].contains(vertex2)) {
          isValidVertex1 = true;
          index = i;
        }
      }
      // Sees if the second vertex is in the adjacency list
      if (vertex2.equals(adjListArray[i].getFirst())) {
        isValidVertex2 = true;
      }
    }

    // If both vertexes exist add the directed edge from 1 to 2
    if (isValidVertex1 && isValidVertex2) {
      // Add the edge to the Linked List at the specified index
      adjListArray[index].add(vertex2);

      // Increment the number of edges
      edges++;
    }

    return;
  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   */
  public void removeEdge(String vertex1, String vertex2) {
    if (vertex1 == null || vertex2 == null) {
      return;
    }

    // Initializes the index to 0
    int index = 0;

    // Boolean variables to keep track of the vertex pair is valid
    boolean isValidVertex1 = false;
    boolean isValidVertex2 = false;

    // Iterates through the current list making sure the vertices exist and that the first vertex
    // has a directed edge to vertex 2
    for (int i = 0; i < vertices; i++) {
      if (vertex1.equals(adjListArray[i].getFirst())) {
        // Means vertex 1 does have a directed edge to vertex 2
        if (adjListArray[i].contains(vertex2)) {
          isValidVertex1 = true;
          index = i;
        }
      }
      // Sees if the second vertex is in the adjacency list
      if (vertex2.equals(adjListArray[i].getFirst())) {
        isValidVertex2 = true;
      }
    }

    // If both vertexes exist remove the directed edge from 1 to 2
    if (isValidVertex1 && isValidVertex2) {
      // Removes the edge from the linkedList at the specified index
      adjListArray[index].remove(vertex2);

      // Decrement the number of edges
      edges--;
    }

    return;
  }

  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllVertices() {
    // Creates a new set
    Set<String> allVertices = new HashSet<String>();

    // Iterates through the list and gets each vertex and adds it to the list
    for (int i = 0; i < vertices; i++) {
      allVertices.add(adjListArray[i].getFirst());
    }

    return allVertices;
  }

  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    // Creates the list to store the adjacent vertices
    List<String> adjacentVertices = new ArrayList<String>();

    if (vertex == null) {
      return adjacentVertices;
    }

    // Sets the index to -1
    int index = -1;

    // Iterates through the adjacency list and checks to see if the vertex is in the list and
    // records the index where it is located
    for (int i = 0; i < vertices; i++) {
      if (adjListArray[i].getFirst().equals(vertex)) {
        index = i;
      }
    }

    if (index == -1) {
      return adjacentVertices;
    }

    // Stores the Linked List at that index in a temporary variable
    LinkedList<String> adjListForVertex = adjListArray[index];

    // Iterates through the Linked List and adds all but the first node into the List. (The first
    // node represents the vertex and not the directed edges).
    for (int i = 1; i < adjListForVertex.size(); i++) {
      adjacentVertices.add(adjListForVertex.get(i));
    }

    return adjacentVertices;
  }

  /**
   * Returns the number of edges in this graph.
   */
  public int size() {
    return edges;
  }

  /**
   * Returns the number of vertices in this graph.
   */
  public int order() {
    return vertices;
  }
}
