package machineepsilon;

public class Inequality
{
    Polynomial left;
    Polynomial centre;
    Polynomial right;
    
    public Inequality(Polynomial left, Polynomial centre, Polynomial right)
    {
        this.left = new Polynomial(left);
        this.centre = new Polynomial(centre);
        this.right = new Polynomial(right);
    }

    public Fraction getLeft()
    {
        return left;
    }
    public Fraction getCentre()
    {
        return centre;
    }
    public Fraction getRight()
    {
        return right;
    }

    public void addTerm(Term term)
    {
        left.addTerm(term);
        centre.addTerm(term);
        right.addTerm(term);
    }

    public void multiplyTerm(Term term)
    {
        left.multiplyTerm(term);
        centre.multiplyTerm(term);
        right.multiplyTerm(term);
    }
}
