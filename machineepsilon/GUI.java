package machineepsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI
{

    JFrame frame;
    JTextField input;
    JTextArea output;
    JScrollPane scrollPane;
    float FONT_SIZE = 20f;

    public void createGUI()
    {
        frame = new JFrame();
        input = new JTextField();
        output = new JTextArea();
        scrollPane = new JScrollPane(output);

        input.setHorizontalAlignment(JTextField.CENTER);
        input.setText("lim x→c f(x) = L");
        input.setText("lim x→2 -(1/4)x^4-(1/2)x^2 = -6");
        input.setFont(input.getFont().deriveFont(FONT_SIZE));
        input.addActionListener(new InputListener());

        output.setFont(output.getFont().deriveFont(FONT_SIZE));

        frame.add(input, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

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
}
