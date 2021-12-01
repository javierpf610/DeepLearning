package agent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgentGUI extends JFrame {
    private MyAgent myAgent;
    private JTextField valores;

    AgentGUI(MyAgent a) {

        super(a.getLocalName());

        myAgent = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(10,10));
        p.add(new JLabel("Deep Learning"));
        p.add(new JLabel("Value:"));
        valores = new JTextField(10);
        p.add(valores);
        getContentPane().add(p, BorderLayout.CENTER);
        JButton addButton = new JButton("Traffic Signals");
        addButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    myAgent.executeAgentTraffic(valores.getText());
                    valores.setText("");

                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(AgentGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } );

        JButton addButton1 = new JButton("Numbers");
        addButton1.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    myAgent.executeAgentNumbers(valores.getText());
                    valores.setText("");

                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(AgentGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } );


        p = new JPanel();
        p.add(addButton);
        p.add(addButton1);

        getContentPane().add(p, BorderLayout.SOUTH);


        addWindowListener(new	WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        } );

        setResizable(false);
    }



    public void showGui() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setVisible(true);
    }



}
