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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.FileReader;
import java.util.Stack;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Filename: PackageManager.java Project: p4 Authors:
 * 
 * PackageManager is used to process json package dependency files and provide function that make
 * that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json file.
 * 
 * Package dependencies are important when building software, as you must install packages in an
 * order such that each package is installed after all of the packages that it depends on have been
 * installed.
 * 
 * For example: package A depends upon package B, then package B must be installed before package A.
 * 
 * This program will read package information and provide information about the packages that must
 * be installed before any given package can be installed. all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test classes.
 */
public class PackageManager {

  private Graph graph;

  /**
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    graph = new Graph();
  }

  /**
   * Takes in a file path for a json file and builds the package dependency graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException if the give file cannot be read
   * @throws ParseException if the given json cannot be parsed
   */
  public void constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    // Creates a new JSONParser object with the passed in file name
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));

    // Creates a new JSON Object from the JSON Parser Object
    JSONObject jo = (JSONObject) obj;

    // Creates a new JSON Array from the packages
    JSONArray packages = (JSONArray) jo.get("packages");

    // Iterates through the packages
    for (int i = 0; i < packages.size(); i++) {

      // Creates a Json object for every package
      JSONObject currPackage = (JSONObject) packages.get(i);

      // Keeps track of the current package name
      String packageName = (String) currPackage.get("name");

      // Adds the vertex to the graph
      graph.addVertex(packageName);

      // Creates a json array with all of the dependencies
      JSONArray dependencies = (JSONArray) currPackage.get("dependencies");

      // Adds the dependencies to the graph
      for (int j = 0; j < dependencies.size(); j++) {
        // Holds the current dependency
        String dep = (String) dependencies.get(j);

        // Adds the vertex to the graph
        graph.addVertex(dep);

        // Adds the directed edge to the graph
        graph.addEdge(packageName, dep);
      }
    }
  }

  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    // Returns a set with all of the vertices. Each vertex represents a package.
    return graph.getAllVertices();
  }

  /**
   * Given a package name, returns a list of packages in a valid installation order.
   * 
   * Valid installation order means that each package is listed before any packages that depend upon
   * that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the installation
   *         order for a particular package. Tip: Cycles in some other part of the graph that do not
   *         affect the installation order for the specified package, should not throw this
   *         exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    // Checks if the package is in the graph
    if (!getAllPackages().contains(pkg)) {
      throw new PackageNotFoundException();
    }

    // List to store the topological order
    List<String> topoOrder = new ArrayList<String>();

    // Stack to keep track of cycles
    Stack<String> stack = new Stack<String>();

    // Keeps track of the vertices that have been visited
    List<String> visited = new ArrayList<String>();

    // Pushes the passed in package to the stack and adds it to the visited list
    stack.push(pkg);
    visited.add(pkg);

    // Loops until the stack is empty
    outerloop: while (!stack.isEmpty()) {
      // Gets the current vertex from the top of the stack
      String currentVertex = stack.peek();

      // Gets all the adjacent vertices of the current vertex
      List<String> adjVert = graph.getAdjacentVerticesOf(currentVertex);

      // Iterates through the adjVert list
      for (int i = 0; i < adjVert.size(); i++) {
        // Keeps track of the current vertex
        String vertex = adjVert.get(i);

        // If the vertex is not visited
        if (!visited.contains(vertex)) {
          // Push it to the stack and mark it as visited
          stack.push(vertex);
          visited.add(vertex);
          // Goes to outer loop
          continue outerloop;

          // If the stack already contains the vertex throw a cycle exception
        } else if (stack.contains(vertex)) {
          throw new CycleException();
        }
      }
      // Adds the item off the top of the stack to the List
      topoOrder.add(stack.pop());
    }

    // Return the list
    return topoOrder;
  }

  /**
   * 
   * @param currentVertex
   * @param visited
   * @param stack
   * @param topoOrder
   * @throws CycleException
   */
  private void depthFirstSearch(String currentVertex, List<String> visited, Stack<String> stack,
      List<String> topoOrder) throws CycleException {

    // If the currentVertex is in the stack throw a cycle exception
    if (stack.contains(currentVertex)) {
      throw new CycleException();
    }

    // If the current vertex has not been visited
    if (!visited.contains(currentVertex)) {
      // Push it to the stack, add it to visited and add it to the topological order list
      stack.push(currentVertex);

      visited.add(currentVertex);

      topoOrder.add(currentVertex);
    } else {
      return;
    }

    // Gets the adjacency List for the vertex on the top of the stack
    List<String> adjVert = graph.getAdjacentVerticesOf(stack.peek());
    for (int i = 0; i < adjVert.size(); i++) {
      // Calls the depth for search method recursively to each of the adjacent neighbors
      depthFirstSearch(adjVert.get(i), visited, stack, topoOrder);
    }

    // Pop the to vertex off the stack
    stack.pop();
  }

  /**
   * Given two packages - one to be installed and the other installed, return a List of the packages
   * that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If package A needs to be
   * installed and packageB is already installed, return the list ["A", "C"] since D will have been
   * installed when B was previously installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the dependencies of
   *         the given packages. If there is a cycle in some other part of the graph that doesn't
   *         affect the parsing of these dependencies, cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist in the dependency
   *         graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    // If either of the passed in packages are not in the graph then throw a package not found
    // exception
    if (!(getAllPackages().contains(newPkg) && getAllPackages().contains(installedPkg))) {
      throw new PackageNotFoundException();
    }

    // List to store the visited nodes
    List<String> visited = new ArrayList<String>();

    // Stack to keep track of cycles
    List<String> toInstall = new ArrayList<String>();

    // Keeps track of the vertices that have been visited
    Stack<String> stack = new Stack<String>();

    // Pushes the passed in package to the stack and adds it to the visited list
    visited.add(installedPkg);

    stack.push(installedPkg);

    // Loops until the stack is empty
    while (!stack.isEmpty()) {

      // Gets the current vertex from the top of the stack
      String currentVertex = stack.pop();

      // Gets all the adjacent vertices of the current vertex
      List<String> adjVert = graph.getAdjacentVerticesOf(currentVertex);

      // Iterates through the adjVert list
      for (int i = 0; i < adjVert.size(); i++) {

        // If the vertex is not visited
        if (!visited.contains(adjVert.get(i))) {
          stack.push(adjVert.get(i));

          visited.add(adjVert.get(i));
        }
      }
    }

    // Call the depth for search recursive method
    depthFirstSearch(newPkg, visited, stack, toInstall);

    return toInstall;
  }

  /**
   * Return a valid global installation order of all the packages in the dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException if you encounter a cycle in the graph
   */
  public List<String> getInstallationOrderForAllPackages() throws CycleException {

    // List to store the visited nodes
    List<String> visited = new ArrayList<String>();

    // Keeps track of the topological order
    List<String> topoOrder = new ArrayList<String>();

    // The stack to keep track of cycles
    Stack<String> stack = new Stack<String>();

    // ArrayList to store all the vertices
    List<String> allVertices = new ArrayList<String>(getAllPackages());

    allVertices.sort(String.CASE_INSENSITIVE_ORDER);


    // Iterates through each of the vertices in the graph
    for (String vertex : allVertices) {
      // List for depth for search first
      List<String> dFSF = new ArrayList<String>();

      // List for depth for search stack
      Stack<String> dFSS = new Stack<String>();

      // List for depth for search order
      List<String> dFSO = new ArrayList<String>();

      depthFirstSearch(vertex, dFSF, dFSS, dFSO);
    }

    // Keeps track of the adjacency list
    List<List<String>> adjList = new ArrayList<List<String>>();

    // Iterates through and adds the adjacent vertices to populate the adjacency list
    for (int i = 0; i < allVertices.size(); i++) {
      adjList.add(graph.getAdjacentVerticesOf(allVertices.get(i)));
    }

    // Loops through all of the vertices
    outerloop: for (int i = 0; i < allVertices.size(); i++) {

      // Keeps track of the current vertex
      String vertex = allVertices.get(i);

      // Iterates through the adjacency list
      for (int j = 0; j < adjList.size(); j++) {
        // If the adjacency list contains the vertex then continue to the outer loop
        if (adjList.get(j).contains(vertex)) {
          continue outerloop;
        }

      }
      // Push the vertex to the stack and mark it as visited
      stack.push(vertex);
      visited.add(vertex);
    }

    // Iterates through until the stack is empty
    outerloop: while (!stack.isEmpty()) {
      // Gets the adjacent vertices of the vertex at the top of the stack
      List<String> adjVert = graph.getAdjacentVerticesOf(stack.peek());

      // Iterates through the adjacent vertices list
      for (int i = 0; i < adjVert.size(); i++) {
        // Keeps track of the current adjacent vertex
        String vertex = adjVert.get(i);

        // Checks if the current vertex has been visited
        if (!visited.contains(vertex)) {
          // Adds it to the stack and marks as visited. Then continues to the outerloop
          stack.push(vertex);
          visited.add(vertex);
          continue outerloop;
        }
      }

      // Adds the item at the top of the stack to the topo Order list
      topoOrder.add(stack.pop());
    }

    return topoOrder;
  }

  /**
   * Find and return the name of the package with the maximum number of dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The number of
   * dependencies includes the dependencies of its dependencies. But, if a package is listed in
   * multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D. Then, A has 3
   * dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException if you encounter a cycle in the graph
   */
  public String getPackageWithMaxDependencies() throws CycleException {
    // List with all of the packages in the graph
    List<String> allPackages = new ArrayList<String>(getAllPackages());

    // Keeps track of the package with the most dependencies
    String max = "";

    // Keeps track of the number of dependencies
    int value = -1;

    // Iterates through all of the packages
    for (int i = 0; i < allPackages.size(); i++) {

      try {
        // Keeps track of the current package
        String currentPackage = allPackages.get(i);

        // Keeps track of the temporary number of packages by the length of the list returned by the
        // getInstallationOrder Method
        int num = getInstallationOrder(currentPackage).size();

        // If the num is greater than the value change max
        if (num > value) {
          value = num;
          max = currentPackage;
        }
        // If the package is not found then catch the exception here
      } catch (PackageNotFoundException e) {
        e.printStackTrace();
      }
    }

    // Return the package with the max dependencies
    return max;
  }

  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

}
