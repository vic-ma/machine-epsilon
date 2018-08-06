package machineepsilon;

public class MachineEpsilon
{
    public static void generateProof(Limit limit)
    {
        Polnomial function = limit.getFunction();
        int c = limit.getC();
        int l = limit.getL();

        Polynomial difference = new Polynomial(function);
        difference.subtractTerm(new Term(l, 0));  // |f(x)-L|
        Polynomial quotient = Polynomial.divide(function, difference);
        Inequality differenceXC = new Inequality(-1, difference, 1);  // Let |x-c| < 1
        differenceXC.addTerm(c);   // -1 < x-c < 1  =>  c-1 < x < c+1
        int m = Math.max(differenceXC.getLeft(), differenceXC.getRight()); // m = max{c-1, c+1}
        // |x| < m
    }
}
