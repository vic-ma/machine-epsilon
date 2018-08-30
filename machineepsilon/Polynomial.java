package machineepsilon;

import java.util.*;

public class Polynomial
{
    private ArrayList<Term> terms = new ArrayList<>();

    public Polynomial()
    {
    }

    public Polynomial(String polynomial)
    {
        polynomial = polynomial.replace(" ", "");
        int startIndex = 0;
        boolean skipNext = false;
        for (int i = 1; i < polynomial.length(); i++)
        {
            if (skipNext)
            {
                skipNext = false;
                continue;
            }
            if (polynomial.charAt(i) == '+')
            {
                terms.add(new Term(polynomial.substring(startIndex, i)));
                startIndex = i+1;
            }
            else if (polynomial.charAt(i) == '-')
            {
                terms.add(new Term(polynomial.substring(startIndex, i)));
                startIndex = i;
            }
        }
        terms.add(new Term(polynomial.substring(startIndex, polynomial.length())));
        this.simplify();
    }

    public Polynomial(Polynomial polynomial)
    {
        // Create a copy of polynomial
        for (int i=0; i < polynomial.getSize(); i++)
            terms.add(polynomial.getTerm(i));
    }

    public int getSize()
    {
        // Return the number of terms this Polynomial has
        return terms.size();
    }

    public int getDegree()
    {
        // Return the degree of this Polynomial
        return terms.get(0).getExponent();
    }

    public Polynomial abs()
    {
        // Return version of this Polynomial, where all Terms are absolute values of themselves
        Polynomial absolute = new Polynomial();
        for (int i = 0; i < terms.size(); i++)
        {
            absolute.addTerm(terms.get(i).abs());
        }
        return absolute;
    }

    private void sort()
    {
        // Sort Terms in descending order
        Collections.sort(terms, Collections.reverseOrder());
    }

    public void simplify()
    {
        // Collect like Terms
        this.sort();
        for (int i=0; i < terms.size()-1; i++)
        {
            Term current = terms.get(i);
            Term next = terms.get(i+1);
            if (current.getExponent() == next.getExponent())
            {
                Term sum = Term.add(current, next);
                if (sum.getCoefficient().getNumerator() != 0)
                {
                    terms.add(i, sum);
                    i--;
                }
                terms.remove(current);
                terms.remove(next);
            }
        }
    }

    public String toString()
    {
        String polynomial = terms.get(0).toString();

        for (int i = 1; i < terms.size(); i++)
        {
            if (terms.get(i).toString().charAt(0) == '-')
                polynomial += terms.get(i);
            else
                polynomial += "+" + terms.get(i);
        }

        return polynomial;
    }

    public String absString()
    {
        // Assuming this Term is the result of calling abs() on a Polynomial,
        // return its String representation, with absolute value bars ("|") around x

        String polynomial = terms.get(0).absString();

        for (int i = 1; i < terms.size(); i++)
        {
            polynomial += "+" + terms.get(i).absString();
        }

        return polynomial;
    }

    public String argString(String arg)
    {
        // Return this Polynomial, but with "x" substituted for "(arg)"

        String polynomial = terms.get(0).argString(arg);

        for (int i = 1; i < terms.size(); i++)
        {
            if (terms.get(i).toString().charAt(0) == '-')
                polynomial += terms.get(i).argString(arg);
            else
                polynomial += "+" + terms.get(i).argString(arg);
        }

        return polynomial;
    }

    public Term getTerm(int index)
    {
        // Return the index-th Term of this Polynomial
        return terms.get(index);
    }

    public void addTerm(Term term)
    {
        // Add a Term to this Polynomial
        terms.add(term);
        this.simplify();
    }

    public void subtractTerm(Term term)
    {
        // Subtract a Term from this Polynomial
        term = Term.multiply(term, new Term(-1, 0));
        addTerm(term);
    }

    public void multiplyTerm(Term term)
    {
        // Multiply a Term into this Polynomial
        for (int i = 0; i < terms.size(); i++)
        {
            terms.set(i, Term.multiply(terms.get(i), term));
        }
        this.sort();
    }

    public static Polynomial add(Polynomial p1, Polynomial p2)
    {
        // Return the sum of two Polynomials
        Polynomial sum = new Polynomial();
        for (int i = 0; i < p1.getSize(); i++)
            sum.addTerm(p1.getTerm(i));
        for (int i = 0; i < p2.getSize(); i++)
            sum.addTerm(p2.getTerm(i));
        sum.simplify();
        return sum;
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2)
    {
        // Return the difference between two Polynomials
        Polynomial difference = new Polynomial();
        for (int i = 0; i < p1.getSize(); i++)
            difference.addTerm(p1.getTerm(i));
        for (int i = 0; i < p2.getSize(); i++)
            difference.subtractTerm(p2.getTerm(i));
        difference.simplify();
        return difference;
    }

    public static Polynomial divide(Polynomial p1, Polynomial p2)
    {
        // Return the quotient of two Polynomial

        Polynomial dividend = new Polynomial(p1);
        Polynomial divisor = new Polynomial(p2);
        Polynomial quotient = new Polynomial();
        Polynomial remainder = dividend;

        while (remainder.getSize() != 0 && remainder.getDegree() >= divisor.getDegree())
        {
            Term quotientTerm = Term.divide(remainder.getTerm(0), divisor.getTerm(0));
            quotient.addTerm(quotientTerm);
            Polynomial tempDivisor = new Polynomial(divisor);
            tempDivisor.multiplyTerm(quotientTerm);
            remainder = Polynomial.subtract(remainder, tempDivisor);
        }
        quotient.simplify();
        return quotient;
    }

    public Fraction valueAt(Fraction x)
    {
        // Return the value of this Polynomial, given a value for x
        Fraction value = new Fraction(0);
        for (int i = 0; i < terms.size(); i++)
            value = Fraction.add(value, terms.get(i).valueAt(x));
        return value;
    }
}
