import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by inksmallfrog on 15-9-19.
 */
public class ScoreCrawler{
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LogInGui logIn = new LogInGui();
                logIn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                logIn.setVisible(true);
            }
        });
    }
}

class LogInGui extends JFrame{
    public String studentId = "2014302580136";
    public String password = "19951014";
    public String identifyingCode;

    public String cookie;

    final File identityPic = new File("identityPic.png");
    final File scoreChart = new File("scoreChart.html");

    public LogInGui(){
        setSize(800, 600);
        setVisible(true);
        setLayout(null);

        JTextField studentText = new JTextField();
        studentText.setText("2014302580136");
        JPasswordField passwordText = new JPasswordField();
        passwordText.setText("19951014");
        JTextField identifyingText = new JTextField();

        JLabel studentIdLabel = new JLabel("学号");
        JLabel passwordLabel = new JLabel("密码");
        JLabel identifyingLabel = new JLabel("验证码");
        JLabel identifyingPic = new JLabel();

        JButton commitButton = new JButton("查看");

        studentIdLabel.setBounds(250, 150, 50, 20);
        studentText.setBounds(350, 150, 150, 20);
        studentText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                studentId = studentText.getText();
            }
        });

        passwordLabel.setBounds(250, 250, 50, 20);
        passwordText.setBounds(350, 250, 150, 20);
        passwordText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                password = passwordText.getPassword().toString();
            }
        });

        identifyingLabel.setBounds(250, 350, 50, 20);
        identifyingText.setBounds(350, 350, 150, 20);
        identifyingText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                GetIdentifyingCode();
                Image icon = Toolkit.getDefaultToolkit().createImage(identityPic.getPath());
                identifyingPic.setIcon(new ImageIcon(icon));
            }

            @Override
            public void focusLost(FocusEvent e) {
                identifyingCode = identifyingText.getText();
            }
        });
        identifyingText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                identifyingCode = identifyingText.getText();
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    CommitInfo();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        identifyingPic.setBounds(550, 340, 150, 40);

        commitButton.setBounds(550, 400, 150, 40);
        commitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CommitInfo();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        add(studentIdLabel);
        add(studentText);

        add(passwordLabel);
        add(passwordText);

        add(identifyingLabel);
        add(identifyingText);
        add(identifyingPic);

        add(commitButton);

        identifyingText.requestFocusInWindow();
    }

    public void GetIdentifyingCode(){
        String url = "http://210.42.121.241/";

        com.github.kevinsawicki.http.HttpRequest request = com.github.kevinsawicki.http.HttpRequest.get(url);
        cookie = request.header("Set-Cookie");
        int pos = cookie.indexOf(";");

        System.out.println(cookie);

        url = "http://210.42.121.241/servlet/GenImg";
        request = com.github.kevinsawicki.http.HttpRequest.get(url).header("Cookie", cookie).header(
                "Connection", "keep-alive");

        request.receive(identityPic);

        cookie = request.header("Set-Cookie");
        cookie = cookie.substring(0, pos);

        System.out.println(cookie);
    }

    public void CommitInfo(){
        Map<String, String> postMap = new HashMap();
        postMap.put("id", studentId);
        postMap.put("pwd", password);
        postMap.put("xdvfb", identifyingCode);

        String url = "http://210.42.121.241/servlet/Login";
        com.github.kevinsawicki.http.HttpRequest request = com.github.kevinsawicki.http.HttpRequest.get(url)
                .header("Cookie", cookie)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Connection", "keep-alive")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Cache-Control", "max-age=0")
                .form(postMap);

        System.out.println(request.code());

        url = "http://210.42.121.241/servlet/Svlt_QueryStuScore?year=0&term=&learnType=&scoreFlag=0&t=Fri%20Sep%2025%202015%2019:10:05%20GMT+0800%20(CST)";
        System.out.println(url);

        request = com.github.kevinsawicki.http.HttpRequest.get(url).header("Cookie", cookie).header(
                "Connection", "keep-alive");
        request.receive(scoreChart);

        try {
            Desktop.getDesktop().open(scoreChart);
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
    }
}

