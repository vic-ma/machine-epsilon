package machineepsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MachineEpsilon
{
    private void createGUI()
    {
        JFrame frame = new JFrame();
        JTextField input = new JTextField();
        JTextArea output = new JTextArea();
        JButton button = new JButton("Exit");

        input.setHorizontalAlignment(JTextField.CENTER);
        input.addActionListener(new inputListener());

        frame.add(input, BorderLayout.NORTH);
        frame.add(output, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    private void generateProof()
    {
    }

    class inputListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            generateProof();
        }
    }

    public static void main(String args[])
    {
    }
}
