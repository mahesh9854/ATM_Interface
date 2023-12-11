import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class History extends JFrame {
    Font font = new Font("SANS_SERIF", Font.ITALIC, 15);

    public History(int id) {
        ImageIcon icon1 = new ImageIcon("src\\img.png", "");
        this.setIconImage(icon1.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("History Page");
        this.setSize(390, 390);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(234, 255, 98));

        JButton draw=new JButton("Close");
        draw.setBounds(30, 5, 150, 30);
        draw.setFocusable(false);
        draw.setFont(font);
        draw.setBackground(new Color(240, 74, 91));
        draw.setBorderPainted(true);
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginPage();

            }
        });


        JTextArea textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.setBounds(5, 50, 365, 360);
        String urlString = "jdbc:mysql://localhost:3306/atm";
        String uName = "root";
        String uPassword = "CPVamsi@0212";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(urlString, uName, uPassword);
            Statement st = con.createStatement();
            String query = "select * from user_wallet where user_id=" + id;
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int balance = rs.getInt(2);
            textArea.append("Remaining Balance:"+String.valueOf(balance)+"\n\n");
            String transaction = rs.getString(3);
            transaction.replace("#", " \n");
            textArea.append(transaction.replace("#", " \n"));
            st.close();
            con.close();
        } catch (Exception ex) {
            System.out.println();
        }
        this.add(draw);
        this.add(textArea);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
