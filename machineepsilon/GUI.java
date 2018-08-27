package machineepsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI
{

    JFrame frame;
    JTextField input;
    JTextArea output;
    JButton exitButton;
    float FONT_SIZE = 20f;

    public void createGUI()
    {
        frame = new JFrame();
        input = new JTextField();
        output = new JTextArea();
        exitButton = new JButton("Exit");

        input.setHorizontalAlignment(JTextField.CENTER);
        //input.setText("lim x→c f(x) = L");
        input.setFont(input.getFont().deriveFont(FONT_SIZE));
        input.setText("lim x→1/2 x^2+3/4 = 1");
        input.addActionListener(new InputListener());

        output.setFont(output.getFont().deriveFont(FONT_SIZE));

        exitButton.addActionListener(new ExitListener());

        frame.add(input, BorderLayout.NORTH);
        frame.add(output, BorderLayout.CENTER);
        frame.add(exitButton, BorderLayout.SOUTH);

        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class InputListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
//            try
//            {
                MachineEpsilon.generateProof(new Limit(input.getText()), output);
//            }
//            catch (Exception ex)
//            {
//                System.out.println("Invalid Input!");
//                input.setText("lim x→c f(x) = L");
//            }
        }
    }

    class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
}
