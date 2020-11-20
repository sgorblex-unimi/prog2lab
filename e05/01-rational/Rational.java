/**
 * Implementation of the concept of rational number. {@code Rational}s are
 * immutable objects which can be summed, multiplied and divided in couples,
 * each operation returning a new {@code Rational}.
 */
public class Rational implements Cloneable {
	private final int num;
	private final int den;

	/*
	 * Abstraction Function: A(num, den) = num/den rational number.
	 *
	 * Representation Invariant: den!=0
	 *
	 * Abstraction Invariant: den!=0
	 */

	/**
	 * Constructs the rational n/d.
	 * 
	 * @param n numerator
	 * @param d denominator
	 * @throws IllegalArgumentException if {@code d<0}.
	 */
	public Rational(int n, int d) {
		if (d == 0)
			throw new IllegalArgumentException("denominator can't be 0.");
		num = n;
		den = d;
	}

	/**
	 * Returns the numerator of this.
	 * 
	 * @return numerator
	 */
	public int num() {
		return num;
	}

	/**
	 * Returns the denominator of this.
	 * 
	 * @return denominator
	 */
	public int den() {
		return den;
	}

	/**
	 * Returns the greater common divisor of the naturals a and b. Assumes a and b
	 * non negative.
	 * 
	 * @param a first natural
	 * @param b second natural
	 * @return GCD of a and b
	 */
	public static int gcd(int a, int b) {
		while (b > 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	/**
	 * Reduces this Rational in an irreducible one.
	 * 
	 * @return this reduced
	 */
	public Rational reduce() {
		boolean negative = num * den < 0;
		int n = Math.abs(num);
		int d = Math.abs(den);
		int gcd = gcd(n, d);
		if (negative)
			return new Rational(-n / gcd, d / gcd);
		return new Rational(n / gcd, d / gcd);
	}

	/**
	 * Returns the sum of this and q.
	 * 
	 * @param q addend
	 * @return the sum
	 */
	public Rational add(Rational q) {
		return new Rational(this.num * q.den + q.num * this.den, this.den * q.den).reduce();
	}

	/**
	 * Returns the product of this and q.
	 * 
	 * @param q factor
	 * @return the product
	 */
	public Rational mul(Rational q) {
		return new Rational(this.num * q.num, this.den * q.den).reduce();
	}

	/**
	 * Returns the reciprocal of this.
	 * 
	 * @return reciprocal of this
	 * @throws ArithmeticException if den is zero
	 */
	public Rational reciprocal() {
		if (num == 0)
			throw new ArithmeticException("illegal rational: can't divide by zero.");
		return new Rational(den, num);
	}

	/**
	 * Returns the quotient of this and q.
	 * 
	 * @param q divisor
	 * @return the quotient
	 * @throws ArithmeticException if q is zero
	 */
	public Rational div(Rational q) {
		return this.mul(q.reciprocal());
	}

	/**
	 * Returns a string identifying this Rational.
	 *
	 * @return a string identifying this Rational.
	 */
	@Override
	public String toString() {
		if (this.num == this.den)
			return "1";
		String res = "" + this.num;
		if (this.den != 1 && this.num != 0)
			res += "/" + this.den;
		return res;
	}

	/**
	 * Compares the specified object with this Rational for equality. Two
	 * {@code Rational}s a/b and c/d are defined to be equal if {@code ad=bc}.
	 *
	 * @param o the object to be compared with this.
	 * @return true if this and o are equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Rational))
			return false;
		Rational other = (Rational) obj;
		return this.num * other.den == this.den * other.num;
	}

	/**
	 * Returns the hash code value for this Rational.
	 *
	 * @return the hash code.
	 */
	@Override
	public int hashCode() {
		return 31 * num + den;
	}

	/**
	 * Returns a shallow copy of this Rational.
	 *
	 * @return a clone of this
	 */
	@Override
	public Rational clone() {
		return this;
	}

}
