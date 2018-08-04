package machineepsilon;

import java.util.*;

public class Polynomial
{
    private ArrayList<Term> terms = new ArrayList<>();

    public Polynomial()
    {
    }

    public Polynomial(Polynomial polynomial)
    {
        for (int i=0; i < polynomial.getSize(); i++)
            terms.add(polynomial.getTerm(i));
    }

    public int getSize()
    {
        return terms.size();
    }

    public int getDegree()
    {
        return terms.get(0).getExponent();
    }

    private void sort()
    {
        Collections.sort(terms, Collections.reverseOrder());
    }

    public void simplify()
    {
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

    public Term getTerm(int index)
    {
        return terms.get(index);
    }

    public void addTerm(Term term)
    {
        terms.add(term);
        this.simplify();
    }

    public void subtractTerm(Term term)
    {
        term = Term.multiply(term, new Term(-1, 0));
        addTerm(term);
    }

    public void multiplyTerm(Term term)
    {
        for (int i = 0; i < this.getSize(); i++)
        {
            terms.set(i, Term.multiply(terms.get(i), term));
        }
        this.sort();
    }

    public static Polynomial add(Polynomial p1, Polynomial p2)
    {
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

    public void printTerms()
    {
        for (int i = 0; i < terms.size(); i++)
            System.out.println(terms.get(i));
    }

    public static void main(String args[])
    {
    }
}
