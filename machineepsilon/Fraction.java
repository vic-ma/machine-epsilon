package machineepsilon;

public class Fraction implements Comparable<Fraction>
{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
        this.simplify();
    }

    public Fraction(int numerator)
    {
        this.numerator = numerator;
        this.denominator = 1;
        this.simplify();
    }

    public Fraction(String fraction)
    {
        boolean positive = (fraction.charAt(0) == '-' ? false : true);

        if (fraction.indexOf('/') != -1)
        {
            this.numerator = Integer.valueOf(fraction.substring(0, fraction.indexOf('/')));
            this.denominator = Integer.valueOf(fraction.substring(fraction.indexOf('/')+1));
        }
        else
        {
            this.numerator = Integer.valueOf(fraction);
            this.denominator = 1;
        }
        this.simplify();
    }

    public int getNumerator()
    {
        return this.numerator;
    }

    public int getDenominator()
    {
        return this.denominator;
    }

    public String toString()
    {
        if (this.numerator == 0)
            return "0";
        else if (this.denominator == 1)
            return String.valueOf(this.numerator);
        return String.valueOf(this.numerator) + "/" + String.valueOf(this.denominator);
    }

    public void simplify()
    {
        int gcf = greatestCommonFactor(this.numerator, this.denominator);
        this.numerator /= gcf;
        this.denominator /= gcf;
        if (this.denominator < 0)
        {
            this.numerator *= -1;
            this.denominator *= -1;
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
