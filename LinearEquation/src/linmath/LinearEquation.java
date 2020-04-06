package linmath;

public class LinearEquation {

		private double a;
		private double b;
		private double c;
		private double d;
		private double e;
		private double f;

		/* Creating constructor for LinearEquation, accepting 6 double values.
		 * I use the 'this' keyword to keep the variables similar so its easier
		 * to link which one goes where for the equation.
		 */
		public LinearEquation(double a, double b, double c, double d, double e, double f) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
			this.f = f;
		}

		public double getA() {return a;}
		public double getB() {return b;}
		public double getC() {return c;}
		public double getD() {return d;}
		public double getE() {return e;}
		public double getF() {return f;}
		
		/*Creating the get() functions for X and Y calculating the 
		 * values for each in the return statements
		 */
		public double getX() {return ((e * d) - (b * f)) / ((a * d) - (b * c));}
		public double getY() {return ((a * f) - (e * c)) / ((a * d) - (b * c));}
		
		/*boolean member that checks weather the denominator of the
		 * X and Y functions equals 0. If so, it is an undefined number
		 * and returns false.
		 */
		public boolean isSolvable() {
			if((a*d) - (b*c) == 0)
				return false;
			else
				return true;
		}
}

