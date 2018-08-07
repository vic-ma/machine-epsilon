package machineepsilon;

public class Limit
{
    private Polynomial function;
    private int c;
    private int l;

    public Limit(Polynomial function, int c, int l)
    {
        this.function = function;
        this.c = c;
        this.l = l;
    }

    public Polynomial getFunction()
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
