package machineepsilon;

public class Limit
{
    private Fraction c;
    private Polynomial f;
    private Fraction l;

    public Limit(Fraction c, Polynomial f, Fraction l)
    {
        this.c = c;
        this.f = f;
        this.l = l;
    }

    public Limit(String limit)
    {
        String[] splitLimit = limit.split(" ");
        for (int i = 0; i < splitLimit.length; i++)
        {
            if (splitLimit[i].indexOf("→") == 1)
            {
                this.c = new Fraction(splitLimit[i++].substring(2));
                this.f = new Polynomial(splitLimit[i++]);
                this.l = new Fraction(splitLimit[i+1]);
                return;
            }
        }
    }

    public Fraction getC()
    {
        return c;
    }

    public Polynomial getF()
    {
        return f;
    }

    public Fraction getL()
    {
        return l;
    }
}
