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

        output += String.format("|f(x)-L| = |x-%s||%s|\n\n", l.getC(), quotient);

        output += String.format("Assume that |x-%s| < δ, so that |x-%s||%s| < δ|%s|", l.getC(),
                                l.getC(), quotient, quotient);

        output += String.format("By the Triangle Inequality, δ|%s| < δ|%s|.", );

        outputBox.setText(output);
    }

    public static void main(String args[])
    {
        GUI gui = new GUI();
        gui.createGUI();
    }
}
