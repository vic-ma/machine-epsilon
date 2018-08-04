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
        this.l = l
    }

    public Polnomial function()
    {
        return function;
    }

    public int c()
    {
        return c;
    }

    public int l()
    {
        return l
    }

}
