import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage extends JFrame implements ActionListener, KeyListener {
    JLabel title,label1,label2;
    JTextField userId;
    JPasswordField userPassword;
    JButton login,cancel;
    Font font=new Font("SANS_SERIF",Font.ITALIC,15);
    LoginPage(){
        ImageIcon icon1=new ImageIcon("src\\img.png","");
        this.setIconImage(icon1.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Welcome Page");
        this.setSize(420,270);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(234, 255, 98));

        title=new JLabel("LOGIN PAGE");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0,0,420,30);
        title.setFont(font);
//        title.setForeground(Color.white);

        label1=new JLabel("USER ID:");
        label1.setBounds(10,70,150,30);
        label1.setFont(font);
//        label1.setForeground(Color.white);

        userId=new JTextField();
        userId.setBounds(150,70,200,30);
        userId.setFont(font);



        label2=new JLabel("USER PIN:");
        label2.setBounds(10,120,150,30);
        label2.setFont(font);
//        label2.setForeground(Color.white);

        userPassword=new JPasswordField();
        userPassword.setBounds(150,120,200,30);
        userPassword.setFont(font);
        userPassword.addKeyListener(this);


        login=new JButton("LOGIN");
        login.setForeground(Color.white);
        login.setBounds(150,170,100,50);
        login.setBackground(new Color(15, 87, 252));
        login.setFocusable(false);
        login.addActionListener(this);
        login.setFont(font);
        login.setBorderPainted(false);

        cancel=new JButton("CANCEL");
        cancel.setForeground(Color.white);
        cancel.setBounds(280,170,100,50);
        cancel.setBackground(new Color(15, 87, 252));
        cancel.setFocusable(false);
        cancel.addActionListener(this);
        cancel.setFont(font);
        cancel.setBorderPainted(false);

        this.add(title);
        this.add(label1);
        this.add(userId);
        this.add(label2);
        this.add(userPassword);
        this.add(login);
        this.add(cancel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login)
        {
            int uid=Integer.parseInt(userId.getText());
            int pass= Integer.parseInt(String.valueOf(userPassword.getPassword()));
            String urlString = "jdbc:mysql://localhost:3306/atm";
            String uName = "root";
            String uPassword = "CPVamsi@0212";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(urlString, uName, uPassword);
                Statement st = con.createStatement();
                System.out.println("try");
                String query="select * from user_login where user_id="+uid+" and user_pass= "+pass;
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);
                if(rs.next()){
                    System.out.println("Success");
                    st.close();
                    con.close();
                    dispose();
                    new Functionalities(uid);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Wrong password try again!","Password Error",JOptionPane.WARNING_MESSAGE);
                    System.out.println("Failed");
                    userId.setText("");
                    userPassword.setText("");
                }

            } catch (Exception ex) {
                System.out.println();
            }
        }
        else if(e.getSource()==cancel)
        {
            dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            login.doClick();
        }if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            cancel.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
