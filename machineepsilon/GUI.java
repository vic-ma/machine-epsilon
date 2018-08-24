package machineepsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI
{
    public void createGUI()
    {
        JFrame frame = new JFrame();
        JTextField input = new JTextField();
        JTextArea output = new JTextArea();
        JButton exitButton = new JButton("Exit");

        input.setHorizontalAlignment(JTextField.CENTER);
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
            MachineEpsilon.generateProof();
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
