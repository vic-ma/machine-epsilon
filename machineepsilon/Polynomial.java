package machineepsilon;

import java.util.*;

public class Polynomial
{
    private ArrayList<Term> terms = new ArrayList<>();

    public Polynomial()
    {
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
        terms.sort(null);
    }

    public Term getTerm(int index)
    {
        return terms.get(index);
    }

    public void addTerm(Term term)
    {
        terms.add(term);
        this.sort();
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

    public polynomial subtract(Polynomial p1, Polynomial p2)
    {
    }

    public Polynomial divide(Polynomial dividend, Polynomial divisor)
    {
        Polynomial quotient = new Polynomial();
        Polynomial remainder = dividend;

        while (remainder.getSize() != 0 && remainder.getDegree() >= divisor.getDegree())
        {
            Term quotientTerm = Term.divide(remainder.getTerm(0), divisor.getTerm(0));
            quotient.addTerm(quotientTerm);
            Polynomial temp = divisor;
            divisor.multiplyTerm(quotientTerm);
            remainder.subtract(divisor);
            divisor = temp;
        }
    }
}
