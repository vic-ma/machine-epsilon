package machineepsilon;

public class Inequality
{
    Polynomial left;
    Polynomial centre;
    Polynomial right;

    public Inequality()
    {
        this.left = new Polynomial();
        this.centre = new Polynomial();
        this.right = new Polynomial();
    }
    
    public Inequality(Polynomial left, Polynomial centre, Polynomial right)
    {
        this.left = new Polynomial(left);
        this.centre = new Polynomial(centre);
        this.right = new Polynomial(right);
    }

    public Inequality(int left, Polynomial centre, int right)
    {
        this.left = new Polynomial();
        this.left.addTerm(new Term(left, 0));
        this.centre = centre;
        this.right = new Polynomial();
        this.right.addTerm(new Term(right, 0));
    }

    public Inequality(Inequality inequality)
    {
        this.left = new Polynomial(inequality.getLeft());
        this.centre = new Polynomial(inequality.getCentre());
        this.right = new Polynomial(inequality.getRight());
    }

    public Polynomial getLeft()
    {
        return left;
    }
    public Polynomial getCentre()
    {
        return centre;
    }
    public Polynomial getRight()
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

    public void addInequality(Inequality inequality)
    {
        left = Polynomial.add(left, inequality.getLeft());
        centre = Polynomial.add(left, inequality.getCentre());
        right = Polynomial.add(left, inequality.getRight());
    }

    public static void main(String args[])
    {
        Polynomial f = new Polynomial();
        f.addTerm(new Term(1, 2));
        f.addTerm(new Term(-4, 0));
        Polynomial p = new Polynomial(f);
        p.subtractTerm(new Term(12, 0));
        Polynomial xMC = new Polynomial();
        xMC.addTerm(new Term(1, 1));
        xMC.addTerm(new Term(-4, 0));
        Polynomial q = Polynomial.divide(p, xMC);
        Inequality iXMC = new Inequality(-1, xMC, 1);
        iXMC.addTerm(q.getTerm(1));
        iXMC.getLeft().printTerms();
        System.out.println();
        iXMC.getCentre().printTerms();
        System.out.println();
        iXMC.getRight().printTerms();
    }
}
