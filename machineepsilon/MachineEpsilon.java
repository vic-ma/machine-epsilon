package machineepsilon;

import javax.swing.*;

public class MachineEpsilon
{

    public static void generateProof(Limit lim, JTextArea outputBox)
    {
        String output = "";
        Fraction c = lim.getC();
        Polynomial f = lim.getF();
        Fraction l = lim.getL();

        if (f.getDegree() == 0)
        {
            output += "Let ϵ > 0\n\n";
            output += "Then, |f(x)-L| = 0 < ϵ ∎";
            outputBox.setText(output);
            return;
        }
        else if (f.getDegree() == 1)
        {
            output += "Let ϵ > 0\n\n";

            Fraction kFrac = f.getTerm(0).getCoefficient().abs();
            String kStr = (kFrac.getDenominator() == 1 ? kFrac.toString() : "("+kFrac.toString()+")");

            String delta;
            if (kFrac.getDenominator() == 1)
                delta = "ϵ/" + kStr;
            else
                delta = kFrac.getDenominator() + "ϵ/" + kFrac.getNumerator();

            output += String.format("Set δ = %s\n\n", delta);

            Polynomial xMinusC = new Polynomial();
            xMinusC.addTerm(new Term(1, 1));
            xMinusC.subtractTerm(new Term(c, 0));
            output += String.format("Assume 0 < |x-c| = |%s| < δ\n\n", xMinusC);

            Polynomial fMinusL = new Polynomial(f);
            fMinusL.subtractTerm(new Term(l, 0));
            output += String.format("|f(x)-L| = |%s| = %s|%s| < %sδ = %s(%s)= ϵ ∎",
                                    fMinusL, kStr, xMinusC, kStr, kStr, delta);
            outputBox.setText(output);
            return;
        }

        output += "ROUGH WORK\n\n";

        Polynomial xMinusC = new Polynomial();
        xMinusC.addTerm(new Term("x"));
        xMinusC.subtractTerm(new Term(c, 0));

        Polynomial quotient = Polynomial.divide(f, xMinusC);

        Polynomial fMinusL = new Polynomial(f);
        fMinusL.subtractTerm(new Term(l, 0));

        output += String.format("\t|f(x)-L|/|x-c|\t= |%s|/|%s|\n\t\t= |%s|\n\n", fMinusL, xMinusC, quotient);

        output += String.format("Assume that 0 < |x-c| = |%s| < δ, so that\n\n\t"
                                +"|f(x)-L| \t= |%s||%s|\n\t\t< δ|%s|\t\n\n", xMinusC, xMinusC, quotient, quotient);

        Polynomial absQuotient = quotient.abs();
        output += String.format("Which, by the Triangle Inequality,\n\n\t\t ≤ δ(%s)\n\n",
                                absQuotient.absString());

        output += String.format("Assume that |%s| < 1, so that\n\n", xMinusC);

        Fraction leftSide = Fraction.subtract(new Fraction(-1), xMinusC.getTerm(1).getCoefficient());
        Fraction rightSide = Fraction.subtract(new Fraction(1), xMinusC.getTerm(1).getCoefficient());
        Fraction absMax = Fraction.max(leftSide.abs(), rightSide.abs());
        output += String.format("\t-1 < %s < 1  ⇒  %s < x < %s  ⇒  |x| < %s\t\n\n",
                                xMinusC, leftSide, rightSide, absMax);

        Fraction kFrac = absQuotient.valueAt(absMax);
        String kStr = (kFrac.getDenominator() == 1 ? kFrac.toString() : "("+kFrac.toString()+")");
        output += String.format("Then,\n\n\t|f(x)-L|\t < δ(%s)\n\t\t < δ(%s) = δ%s\t(*)\n\n\n",
                                absQuotient.absString(), absQuotient.argString("("+absMax+")"), kStr);

        output += "PROOF\n\n";

        String delta;
        if (kFrac.getDenominator() == 1)
            delta = "ϵ/" + kStr;
        else
            delta = kFrac.getDenominator() + "ϵ/" + kFrac.getNumerator();
        output += String.format("Let ϵ > 0\n\nSet δ = min{1, %s}\n\n", delta);

        output += String.format("Assume 0 < |%s| < δ\n\nThen, since |%s| < δ ≤ 1,\n\n\t"
                                +"|f(x)-L| < %sδ, by (*)\n\n", xMinusC, xMinusC, kStr);

        output += String.format("And since δ ≤ %s,\n\n\t%sδ ≤ %s(%s) = ϵ ∎",
                                delta, kStr, kStr, delta);

        outputBox.setText(output);
    }

    public static void main(String args[])
    {
        GUI gui = new GUI();
        gui.createGUI();
    }
}
