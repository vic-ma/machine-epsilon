package machineepsilon;

import javax.swing.*;

public class MachineEpsilon
{

    public static void generateProof(Limit l, JTextArea outputBox)
    {
        String output = "";
        output += "ROUGH WORK\n\n";

        Polynomial xMinusC = new Polynomial();
        xMinusC.addTerm(new Term("x"));
        xMinusC.subtractTerm(new Term(l.getC(), 0));

        Polynomial quotient = Polynomial.divide(l.getF(), xMinusC);

        Polynomial difference = new Polynomial(l.getF());
        difference.subtractTerm(new Term(l.getL(), 0));

        output += String.format("\t|f(x)-L|/|x-c| = |%s|/|x-%s| = |%s|\n\n", difference, l.getC(),
                                quotient);

        output += String.format("Assume that |x-c| = |x-%s| < δ, so that\n\n\t"
                                +"|f(x)-L| = |x-%s||%s| < δ|%s|\t\n\n", l.getC(),
                                 l.getC(), quotient, quotient);

        Polynomial absQuotient = quotient.abs();
        output += String.format("By the Triangle Inequality,\n\n\tδ|%s| ≤ δ(%s)\n\n",
                                quotient, absQuotient.abs().absString());

        output += String.format("Assume that |x-%s| < 1, so that\n\n", l.getC());

        Fraction leftSide = Fraction.subtract(new Fraction(-1), xMinusC.getTerm(1).getCoefficient());
        Fraction rightSide = Fraction.subtract(new Fraction(1), xMinusC.getTerm(1).getCoefficient());
        Fraction absMax = Fraction.max(leftSide.abs(), rightSide.abs());
        output += String.format("\t-1 < x-%s < 1 ⇒ %s < x < %s ⇒ |x| < %s\t\n\n", l.getC(),
                                leftSide, rightSide, absMax);

        Fraction max = absQuotient.valueAt(absMax);
        output += String.format("Then,\n\n\tδ(%s) < δ(%s) = δ%s\t(*)\n\n\n", absQuotient.absString(),
                                absQuotient.argString("("+absMax+")"), max);

        output += "PROOF\n\n";

        output += String.format("Let ϵ > 0.\n\nSet δ = max{1, ϵ/%s}.\n\n", max);

        output += String.format("Assume |x-%s| < δ.\n\nThen, since |x-%s| < δ ≤ 1,\n\n\t"
                                +"|f(x)-L| < %sδ, by (*)\n\n", l.getC(), l.getC(), max);

        output += String.format("And since δ ≤ ϵ/%s,\n\n\t%sδ ≤ %s(ϵ/%s) = ϵ\t∎", max, max, max, max);

        outputBox.setText(output);
    }

    public static void main(String args[])
    {
        GUI gui = new GUI();
        gui.createGUI();
    }
}
