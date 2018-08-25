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

    public void createGUI()
    {
        frame = new JFrame();
        input = new JTextField();
        output = new JTextArea();
        exitButton = new JButton("Exit");

        input.setHorizontalAlignment(JTextField.CENTER);
        input.setText("lim x→c f(x) = L");
        input.addActionListener(new InputListener());

        exitButton.addActionListener(new ExitListener());

        frame.add(input, BorderLayout.NORTH);
        frame.add(output, BorderLayout.CENTER);
        frame.add(exitButton, BorderLayout.SOUTH);

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class InputListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                MachineEpsilon.generateProof(new Limit(input.getText()), output);
            }
            catch (Exception ex)
            {
                input.setText("lim x→c f(x) = L");
            }
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
