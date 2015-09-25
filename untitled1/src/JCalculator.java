import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JCalculator extends JFrame{
    //初始化组合框中的选项
    private String[] s = {"+","-","*","/"};

    //初始化计算时的数字
    private double result = 0.0;
    private double number1 = 0.0;
    private double number2 = 0.0;

    //计算器主框架
    public JCalculator()
    {
        setLayout(new GridLayout(1, 5));    //设置布局样式
        setVisible(true);       //设置可见
        setSize(800, 200);      //设置框架大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        //设置退出方式

        Container c = getContentPane();     //创建容器

        JTextField jt1 = new JTextField();      //文本框1
        JComboBox jc = new JComboBox(s);        //组合框
        JTextField jt2 = new JTextField();      //文本框2
        JButton jb = new JButton("=");          //按钮框
        JTextField jt3 = new JTextField();      //结果文本框
        jt3.setFocusable(false);                //结果文本框不能被修改

        //当文本框1触发焦点事件时
        jt1.addFocusListener(new FocusListener() {
            //当文本框1获得焦点时
            @Override
            public void focusGained(FocusEvent e) {
            }

            //当文本框1失去焦点时
            @Override
            public void focusLost(FocusEvent e) {
                //当文本框1中的内容转换为Double时,检查是否产生了类型不匹配的异常
                try{
                    number1 = Double.parseDouble(jt1.getText());
                }
                //如果产生了数字类型不匹配的异常
                catch(NumberFormatException exc){
                    number1 = 0.0;      //将参数1的值置为0.0
                    jt1.setText("0.0");     //将文本框1的内容显示为0.0
                }

                //如果参数2是有效数字，则计算结果
                if (!Double.isNaN(number2)) {
                    switch (jc.getSelectedIndex()) {
                        case 0:
                            result = number1 + number2;
                            break;
                        case 1:
                            result = number1 - number2;
                            break;
                        case 2:
                            result = number1 * number2;
                            break;
                        case 3:
                            result = number1 / number2;
                            break;
                    }
                    jt3.setText(result + "");       //显示计算结果
                }
            }
        });

        //道理同文本框1
        jt2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) throws NumberFormatException{
                try{
                    number2 = Double.parseDouble(jt2.getText());
                }
                catch(NumberFormatException exc){
                    number2 = 0.0;
                    jt2.setText("0.0");
                }
                if(Double.isNaN(number2)){
                    number2 = 0.0;
                }
                if (!Double.isNaN(number1)) {
                    switch (jc.getSelectedIndex()) {
                        case 0:
                            result = number1 + number2;
                            break;
                        case 1:
                            result = number1 - number2;
                            break;
                        case 2:
                            result = number1 * number2;
                            break;
                        case 3:
                            result = number1 / number2;
                            break;
                    }
                    jt3.setText(result + "");
                }
            }
        });

        //当组合栏的元素（运算符）改变时,重新计算结果
        jc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (jc.getSelectedIndex()) {
                    case 0:
                        result = number1 + number2;
                        break;
                    case 1:
                        result = number1 - number2;
                        break;
                    case 2:
                        result = number1 * number2;
                        break;
                    case 3:
                        result = number1 / number2;
                        break;
                }
                jt3.setText(result + "");
            }
        });

        //按顺序添加组件
        c.add(jt1);
        c.add(jc);
        c.add(jt2);
        c.add(jb);
        c.add(jt3);
    }

    public static void main(String args[]) {
        // TODO Auto-generated method stub
        //将要运行的框架放入运行队列中
        EventQueue.invokeLater(new Runnable() {
            @Override
            //运行框架
            public void run() {
                new JCalculator();
            }
        });
    }
}

