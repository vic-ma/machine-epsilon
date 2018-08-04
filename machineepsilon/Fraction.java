package machineepsilon;

import java.util.*;

public class Fraction implements Comparable<Fraction>
{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(int numerator)
    {
        this.numerator = numerator;
        denominator = 1;
        simplify();
    }

    public Fraction(String fraction)
    {
        boolean positive = (fraction.charAt(0) == '-' ? false : true);

        if (fraction.indexOf('/') == -1)
        {
            numerator = Integer.valueOf(fraction);
            denominator = 1;
        }
        else
        {
            numerator = Integer.valueOf(fraction.substring(0, fraction.indexOf('/')));
            denominator = Integer.valueOf(fraction.substring(fraction.indexOf('/')+1));
        }
        simplify();
    }

    public int getNumerator()
    {
        return numerator;
    }

    public int getDenominator()
    {
        return denominator;
    }

    public String toString()
    {
        if (numerator == 0)
            return "0";
        else if (denominator == 1)
            return String.valueOf(numerator);
        return String.valueOf(numerator) + "/" + String.valueOf(denominator);
    }

    public void simplify()
    {
        int gcf = greatestCommonFactor(numerator, denominator);
        numerator /= gcf;
        denominator /= gcf;
        if (denominator < 0)
        {
            numerator *= -1;
            denominator *= -1;
        }
    }

    public static int greatestCommonFactor(int a, int b)
    {
        return b==0 ? a : greatestCommonFactor(b, a%b);
    }

    public int compareTo(Fraction frac)
    {
        Fraction difference = Fraction.subtract(this, frac);
        if (this.numerator > 0)
        {
            if (frac.getNumerator() < 0)
                return -1;
            if (difference.getNumerator() < 0)
                return 1;
            else if (difference.getNumerator() > 0)
                return -1;
            return 0;
        }
        else
        {
            if (frac.getNumerator() > 0)
                return 1;
            if (difference.getNumerator() > 0)
                return -1;
            else if (difference.getNumerator() < 0)
                return 1;
            return 0;
        }
    }

    public boolean equals(Fraction fraction)
    {
        return compareTo(fraction) == 0;
    }

    public int hashCode()
    {
        return Objects.hash(numerator, denominator);
    }

    public static Fraction add(Fraction f1, Fraction f2)
    {
        int numerator = f1.getNumerator() * f2.getDenominator()
            + f2.getNumerator() * f1.getDenominator();
        int denominator = f1.getDenominator() * f2.getDenominator();
        Fraction sum = new Fraction(numerator, denominator);
        sum.simplify();
        return sum;
    }

    public static Fraction subtract(Fraction f1, Fraction f2)
    {
        int numerator = f1.getNumerator() * f2.getDenominator()
            - f2.getNumerator() * f1.getDenominator();
        int denominator = f1.getDenominator() * f2.getDenominator();
        Fraction difference = new Fraction(numerator, denominator);
        difference.simplify();
        return difference;
    }

    public static Fraction multiply(Fraction f1, Fraction f2)
    {
        int numerator = f1.getNumerator() * f2.getNumerator();
        int denominator = f1.getDenominator() * f2.getDenominator();
        Fraction product = new Fraction(numerator, denominator);
        product.simplify();
        return product;
    }

    public static Fraction divide(Fraction f1, Fraction f2)
    {
        return multiply(f1, new Fraction(f2.getDenominator(), f2.getNumerator()));
    }

    public static void main(String args[])
    {
    }
}
