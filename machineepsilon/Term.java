package machineepsilon;

import java.util.*;

public class Term implements Comparable<Term>
{
    private Fraction coefficient;
    private int exponent;

    public Term(Fraction coefficient, int exponent)
    {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term(int coefficient, int exponent)
    {
        this(new Fraction(coefficient), exponent);
    }

    public Term(String term)
    {
        if (term.indexOf("x") == -1)  // If term is constant
        {
            coefficient = new Fraction(term);
            exponent = 0;
            return;
        }
        else if (term.indexOf("^") == -1)  // If term is linear
        {
            exponent = 1;
        }
        else  // term has exponent >= 2
        {
            exponent = Integer.valueOf(term.substring(term.indexOf("^")+1));
        }

        // Exponent is set
        // Now, set the coefficient

        if (term.charAt(0) == 'x')  // If coefficient is 1
        {
            coefficient = new Fraction(1);
            return;
        }
        else if (term.substring(0, 2).equals("-x"))  // If coefficient is -1
        {
            coefficient = new Fraction(-1);
            return;
        }


        if (term.indexOf("/") != -1)  // If coefficient is a fraction
            term = term.replaceAll("()", "");

        coefficient = new Fraction(term.substring(0, term.indexOf("x")));
    }

    public Fraction getCoefficient()
    {
        return coefficient;
    }

    public int getExponent()
    {
        return exponent;
    }

    public int compareTo(Term term)
    {
        // Return -1, 0, or 1, iff this Term is less than, equal to, or greater than term

        int exponentCompare = this.exponent - term.getExponent();
        if (exponentCompare > 0)
            return 1;
        else if (exponentCompare < 0)
            return -1;

        // Exponents are equal
        // So, compare coefficients

        int coefficientCompare = this.coefficient.compareTo(term.getCoefficient());
        if (coefficientCompare > 0)
            return 1;
        else if (coefficientCompare < 0)
            return -1;

        return 0;
    }

    public Term abs()
    {
        // Return absolute value of this Term
        return new Term(coefficient.abs(), exponent);
    }

    public String toString()
    {
        String coefficient = String.valueOf(this.coefficient);

        if (this.exponent == 0)
            return coefficient;

        if (coefficient.equals("1"))
            coefficient = "";
        else if (coefficient.equals("-1"))
            coefficient = "-";

        if (this.coefficient.getDenominator() != 1)
        {
            if (this.coefficient.getNumerator() > 0)
                coefficient = "(" + coefficient + ")";
            else
                coefficient = "-(" + coefficient.substring(1) + ")";
        }

        if (exponent == 1)
            return coefficient + "x";
        return coefficient + "x^" + exponent;
    }

    public String absString()
    {
        // Assuming this Term is the result of calling abs() on a Term,
        // return its String representation, with absolute value bars ("|") around x

        String term = this.toString();

        if (exponent == 0)
            return term;
        if (exponent != 1)
            return term.substring(0, term.indexOf("x")) + "|x|" + term.substring(term.indexOf("^"));
        return term.substring(0, term.indexOf("x")) + "|x|";
    }

    public String argString(String arg)
    {
        // Return this Term, but with "x" substituted for "(arg)"

        String coefficient = String.valueOf(this.coefficient);

        if (this.exponent == 0)
            return coefficient;

        if (coefficient.equals("1"))
            coefficient = "";
        else if (coefficient.equals("-1"))
            coefficient = "-";

        if (this.coefficient.getDenominator() != 1)
        {
            if (this.coefficient.getNumerator() > 0)
                coefficient = "(" + coefficient + ")";
            else
                coefficient = "-(" + coefficient.substring(1) + ")";
        }

        if (exponent == 1)
            return coefficient + arg;
        return coefficient + arg +"^" + exponent;
    }

    public boolean equals(Term term)
    {
        // Return whether this Term equals term
        return compareTo(term) == 0;
    }

    public int hashCode()
    {
        return Objects.hash(coefficient, exponent);
    }

    public static Term add(Term t1, Term t2)
    {
        // Return the sum of two Terms
        Fraction coefficient = Fraction.add(t1.getCoefficient(), t2.getCoefficient());
        return new Term(coefficient, t1.getExponent());
    }

    public static Term subtract(Term t1, Term t2)
    {
        // Return the difference between two Terms
        Fraction coefficient = Fraction.subtract(t1.getCoefficient(), t2.getCoefficient());
        return new Term(coefficient, t1.getExponent());
    }

    public static Term multiply(Term t1, Term t2)
    {
        // Return the product of two Terms
        Fraction coefficient = Fraction.multiply(t1.getCoefficient(), t2.getCoefficient());
        int exponent = t1.getExponent() + t2.getExponent();
        return new Term(coefficient, exponent);
    }

    public static Term divide(Term t1, Term t2)
    {
        // Return the quotient of two Terms
        Fraction coefficient = Fraction.divide(t1.getCoefficient(), t2.getCoefficient());
        int exponent = t1.getExponent() - t2.getExponent();
        return new Term(coefficient, exponent);
    }

    public Fraction valueAt(Fraction x)
    {
        // Return the value of this Term, given a value for x
        Fraction power = new Fraction((int)Math.pow(x.getNumerator(), exponent),
                (int)Math.pow(x.getDenominator(), exponent));
        return Fraction.multiply(coefficient, power); 
    }
}
