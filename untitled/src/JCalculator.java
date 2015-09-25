import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JCalculator extends JFrame{
    private String[] s = {"+","-","*","/"};
    private double result = 0;
    private double number1 = 0;
    private double number2 = 0;
    public JCalculator()
    {
        Container c = getContentPane();
        setLayout(new GridLayout(1,5));
        setVisible(true);
        setSize(800,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextField jt1 = new JTextField();
        c.add(jt1);
        jt1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                number1 = Double.parseDouble(jt1.getText());
            }
        });


        JComboBox jc = new JComboBox(s);
        c.add(jc);

        JTextField jt2 = new JTextField();
        c.add(jt2);
        jt2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                number2 = Double.parseDouble(jt2.getText());
            }
        });

        JButton jb = new JButton("=");
        c.add(jb);


        JTextField jt3 = new JTextField();
        c.add(jt3);

        switch(jc.getSelectedIndex())
        {
            case 0:
                result = number1+number2;
            case 1:
                result = number1-number2;
            case 2:
                result = number1*number2;
            case 3:
                result = number1/number2;
        }

        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jt3.setText(result+"");
            }
        });

    }

    public static void main(String args[]) {
        // TODO Auto-generated method stub
        new JCalculator();
    }

}

