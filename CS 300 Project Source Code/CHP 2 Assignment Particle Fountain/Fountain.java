//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P02 Particle Fountain
// Files:           Fountain.java
// Course:          Spring 2019
//
// Author:          Jacob Brevard
// Email:           jbrevard@wisc.edu
// Lecturer's Name: Professor Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains the code for the Particle Fountain program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Fountain Class contains method for a fully functioning Particle Fountain
 * program. It also contains the main method to run the program.
 */

import java.util.Random;

/**
 * The Particle Fountain class contains methods to contribute to creating an
 * image with numerous particles/pixels. The supporting methods are contained in
 * this class and called from the Utility package.
 * 
 */
public class Fountain {
	// Private Static Field of the random generator object used to generate random
	// numbers throughout the program
	private static Random randGen = new Random();

	// Stores the particles throughout the program
	private static Particle[] particles;

	// The field for the x position
	private static int positionX;

	// The field for the y position
	private static int positionY;

	// The field for the starting color
	private static int startColor;

	// The field for the ending color
	private static int endColor;

	/**
	 * Main method used to run the Particle Fountain program and call the necessary
	 * supporting methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Utility.runApplication();
	}

	/**
	 * The setup method is used to initialize all of the private static fields. It
	 * sets all of the default values for the particle fountain program. It also
	 * calls the two testing methods and prints out failed if either of the two
	 * tests fail.
	 * 
	 */
	public static void setup() {
		// Sets the particles array to have a capacity of 800 particles
		particles = new Particle[800];

		// Sets the starting and ending colors
		startColor = Utility.color(23, 141, 235);
		endColor = Utility.color(23, 200, 255);

		// Sets the initial x and y positions
		positionX = 400;
		positionY = 300;

		// Calls the two testing methods and prints out Failed to the console if one of
		// them does not return true
		if (testUpdateParticle() != true || testRemoveOldParticles() != true) {
			System.out.println("FAILED");
		}

		// Resets the particles array to a new reference
		particles = new Particle[800];
	}

	/**
	 * The update method is used to update the particles by calling the various
	 * methods. It serves as the main driver for the particle fountain program. It
	 * constantly iterates through the particles array updating their position.
	 * 
	 */
	public static void update() {
		// Removes the particles with a specified age
		removeOldParticles(80);

		// Sets the background color
		Utility.background(Utility.color(235, 213, 186));

		// Calls the create new particles method which creates the number of specified
		// particles
		createNewParticles(10);

		// Iterates through the particles array and updates the position of the non null
		// objects by calling the update particle method
		for (int i = 0; i <= particles.length - 1; i++) {
			if (particles[i] != null) {
				updateParticle(i);
			}
		}

	}

	/**
	 * The updateParticle method is used to update the position of the particles.
	 * This method is called and then updates the position of the particle as
	 * gravity has an effect on it.
	 * 
	 * @param index
	 *            The index of the particle in the particles array to update.
	 * 
	 */
	private static void updateParticle(int index) {
		// Creates the particle with the specified values from the particle object
		Utility.circle(particles[index].getPositionX(), particles[index].getPositionY(),
				particles[index].getSize());
		Utility.fill(particles[index].getColor(), particles[index].getTransparency());

		// Gravitational effect
		particles[index].setVelocityY(particles[index].getVelocityY() + 0.3f);

		// Updates the Particles Position
		particles[index]
				.setPositionX(particles[index].getPositionX() + particles[index].getVelocityX());
		particles[index]
				.setPositionY(particles[index].getPositionY() + particles[index].getVelocityY());

		// Increments the Particles Age
		particles[index].setAge(particles[index].getAge() + 1);
	}

	/**
	 * The createNewParticles method is used to create new Particles in the
	 * particles array. The parameter number determines the number of particles the
	 * method will create. The method iterates through the particles array looking
	 * for null indexes. If it finds an index that is null it creates a new particle
	 * setting all of its attributes to random values. The method ends after it
	 * iterates through the entire particles array or when the correct number of
	 * particles have been created.
	 * 
	 * @param number
	 *            The number of particles to create.
	 * 
	 */
	private static void createNewParticles(int number) {
		// Iterates through the particles array creating new particles with random
		// attributes at index's that currently have a null reference
		for (int i = 0; i <= particles.length - 1; i++) {
			if (number > 0 && particles[i] == null) {
				// Creates a new instance of a Particle Object
				particles[i] = new Particle();

				// The x position of each particle should begin within 3 pixels (+/-) of
				// Fountain.positionX.
				particles[i].setPositionX(
						Fountain.positionX + (randGen.nextFloat() + 2) - (randGen.nextInt(6)));

				// The y position of each particle should begin within 3 pixels (+/-) of
				// Fountain.positionY.
				particles[i].setPositionY(
						Fountain.positionY + (randGen.nextFloat() + 2) - (randGen.nextInt(6)));

				// The size of each particle should begin between 4 and 11.
				particles[i].setSize(randGen.nextInt(8) + 4);

				// The color of each particle should begin between Fountain.startColor and
				// Fountain.endColor
				particles[i].setColor(Utility.lerpColor(Fountain.startColor, Fountain.endColor,
						randGen.nextFloat()));

				// The x velocity of each particle should begin between -1 and 1.
				particles[i].setVelocityX(randGen.nextInt(3) - 1);

				// The y velocity of each particle should begin between -5 and -10.
				particles[i].setVelocityY(randGen.nextInt(6) - 10);

				// The age of each particle should begin between 0 and 40
				particles[i].setAge(randGen.nextInt(41));

				// The transparency of each particle should begin between 32 and 127.
				particles[i].setTransparency(randGen.nextInt(96) + 32);

				// Decrements 1 from number indicating that we created a new particle
				number--;
			}
		}

	}

	/**
	 * The removeOldParticles method is used to remove particles in the particles
	 * array that are over a specified age. This age is determined by the parameter
	 * maxAge. This method iterates through the particles array and if the age is
	 * over maxAge then that index is set to null and if the age is under or equal
	 * to max age the particle is left alone.
	 * 
	 * @param maxAge
	 *            The maximum age for a particle before it is removed.
	 * 
	 */
	public static void removeOldParticles(int maxAge) {
		// Iterates through the particles array searching for particles with an age
		// greater than maxAge.
		for (int i = 0; i <= particles.length - 1; i++) {
			if (particles[i] != null) {
				if (particles[i].getAge() > maxAge) {
					// Sets the index to null if the particle's age is greater than the maxAge
					particles[i] = null;
				}
			}
		}
	}

	/**
	 * The mousePressed method is used to change the location of the fountain when
	 * the user clicks the mouse somewhere on the screen. Constantly updating the
	 * fountain's position as the when the user presses the mouse.
	 * 
	 */
	public static void mousePressed(int xPosition, int yPosition) {
		// Changes the position of the fountain to where the mouse is clicked
		Fountain.positionX = xPosition;
		Fountain.positionY = yPosition;
	}

	/**
	 * The keyPressed method is used to take a screen shot of the particle fountain
	 * and save it when the user presses 'p' on the keyboard.
	 * 
	 */
	public static void keyPressed(char key) {
		// If the user presses p then it takes a screenshot and saves it
		if (key == 'p') {
			Utility.save("screenshot.png");
		}
	}

	/**
	 * Creates a single particle at position (3,3) with velocity (-1,-2). Then
	 * checks whether calling updateParticle() on this particle's index correctly
	 * results in changing its position to (2,1.3).
	 * 
	 * @return true when no defect is found, and false otherwise
	 */
	private static boolean testUpdateParticle() {
		// Creates 1 new instance of the particle object
		particles[0] = new Particle();

		// Sets the new particle's x position
		particles[0].setPositionX(3.0f);

		// Sets the new particle's y position
		particles[0].setPositionY(3.0f);

		// Sets the new particle's x velocity
		particles[0].setVelocityX(-1);

		// Sets the new particle's y velocity
		particles[0].setVelocityY(-2);

		// Sets the particles color to match the rest of the program - Not required but
		// it looked better
		particles[0].setColor(
				Utility.lerpColor(Fountain.startColor, Fountain.endColor, randGen.nextFloat()));

		// Calls the update particle method with the index of the new particle
		updateParticle(0);

		// Checks if the position was correctly changed
		if (particles[0].getPositionX() != 2 || particles[0].getPositionY() != 1.3f) {
			return false;
		}

		return true;
	}

	/**
	 * Calls removeOldParticles(6) on an array with three particles (two of which
	 * have ages over six and another that does not). Then checks whether the old
	 * particles were removed and the young particle was left alone.
	 * 
	 * @return true when no defect is found, and false otherwise
	 */
	private static boolean testRemoveOldParticles() {
		// Creates three new particles
		particles[1] = new Particle();
		particles[2] = new Particle();
		particles[3] = new Particle();

		// Sets 2 out of the 3 particles to have an age over 6 and one to be below 6
		particles[1].setAge(8);

		particles[2].setAge(10);

		particles[3].setAge(4);

		// Sets the particles color to match the rest of the program - Not required but
		// it looked better
		particles[1].setColor(
				Utility.lerpColor(Fountain.startColor, Fountain.endColor, randGen.nextFloat()));

		// Sets the particles color to match the rest of the program - Not required but
		// it looked better
		particles[2].setColor(
				Utility.lerpColor(Fountain.startColor, Fountain.endColor, randGen.nextFloat()));

		// Sets the particles color to match the rest of the program - Not required but
		// it looked better
		particles[3].setColor(
				Utility.lerpColor(Fountain.startColor, Fountain.endColor, randGen.nextFloat()));

		// Calls the remove old particles method
		removeOldParticles(6);

		// Checks to see that the 2 particles older than 6 were removed and the 1
		// particle younger than 6 was left alone
		if (particles[1] != null || particles[2] != null || particles[3] == null) {
			return false;
		}

		return true;
	}

}
