package linmath;
import java.util.Scanner;
public class EqTest {

	public static void main(String[] args) {
		
		//Initializing variables and creating LinearEquation object and a scanner
		double a, b, c, d, e, f;
		LinearEquation LE;
		String Exit = null;
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println("Enter 6 numbers to determine wether X and Y exist\n"
					+ "in a system of linear equations.\n\nThe set up for the equations "
					+ "goes as follows:\n\nAx + By = E  &  Cx + Dy = F\n\n"
					+ "Enter numbers in order a, b, c, d, e, & f:");
			
			//accepting 6 inputed values
			a = input.nextDouble();
			b = input.nextDouble();
			c = input.nextDouble();
			d = input.nextDouble();
			e = input.nextDouble();
			f = input.nextDouble();
		
			//Initializing a constructor with values above
			LE = new LinearEquation(a, b, c, d, e, f);
		
			/*displaying the current state of the function as it is
			 * inside the class using getter methods
			 */
			System.out.println("\n" + LE.getA() + "x + " + LE.getB() + "y = " + LE.getE() + "\n"
					+ LE.getC() + "x + " + LE.getD() + "y = " + LE.getF() + "\n");
			
			//checking if it is not solvable, and displaying results accordingly
			if(!LE.isSolvable())
				System.out.println("The numbers you entered have an undefined value\n"
						+ "for the system of equations, DIVIDE BY 0 ERROR!");
			else 
				System.out.println("The numbers that you entered have a solution for X and Y.\n"
						+ "These values are, X = " + LE.getX() + " and Y = " + LE.getY());
			
			//Giving option to calculate another system of equations
			System.out.println("\n\nWould you like to try and calculate another X and Y\n"
					+ "using a system of linear equations? <Y,N>");
			
			Exit = input.next();
			System.out.println("\n\n\n");
			}
			while(!Exit.toUpperCase().equals("N"));
		
			System.out.println("Thanks for using my program!");
			input.close();
		}

}

