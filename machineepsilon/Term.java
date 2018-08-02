package machineepsilon;

public class Term implements Comparable<Term>
{
    Fraction coefficient;
    int exponent;

    public Term(Fraction coefficient, int exponent)
    {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term(int coefficient, int exponent)
    {
        Term(new Fraction(coefficient), exponent);
    }

    public int compareTo(Term term)
    {
        int exponentCompare = this.exponent - term.getExponent();
        if (exponentCompare > 0)
            return 1;
        else if (exponentCompare < 0)
            return -1;

        int coefficientCompare = this.coefficient.compareTo(term.getCoefficient());
        if (coefficientCompare > 0)
            return 1;
        else if (coefficientCompare < 0)
            return -1;

        return 0;
    }

    public static Term add(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.add(t1.getCoefficient(), t2.getCoefficient());
        return new Term(coefficient, t1.getExponent());
    }

    public static Term subtract(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.subtract(t1.getCoefficient(), t2.getCoefficient());
        return new Term(coefficient, t1.getExponent());
    }

    public static Term multiply(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.multiply(t1.getCoefficient(), t2.getCoefficient());
        int exponent = t1.getExponent() + t2.getExponent();
        return new Term(coefficient, exponent);
    }

    public static Term divide(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.divide(t1.getCoefficient(), t2.getCoefficient());
        int exponent = t1.getExponent() - t2.getExponent();
        return new Term(coefficient, exponent);
    }
}
