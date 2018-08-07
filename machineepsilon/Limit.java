package machineepsilon;

public class Limit
{
    private Polnomial function;
    private int c;
    private int l;

    public Limit(Polnomial function, int c, int l)
    {
        this.function = function;
        this.c = c;
        this.l = l;
    }

    public Polnomial getFunction()
    {
        return function;
    }

    public int getC()
    {
        return c;
    }

    public int getL()
    {
        return l;
    }
}
