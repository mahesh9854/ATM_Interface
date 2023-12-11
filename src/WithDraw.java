import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WithDraw extends JFrame implements ActionListener {
    JLabel title, amount;
    JTextField textField;
    JButton draw;
    Font font = new Font("SANS_SERIF", Font.ITALIC, 15);
    int id;

    WithDraw(int uid) {
        id = uid;
        ImageIcon icon1 = new ImageIcon("src\\img.png", "");
        this.setIconImage(icon1.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("WithDraw Page");
        this.setSize(350, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(234, 255, 98));

        title = new JLabel();
        title.setText("Welcome mr." + id + " to withdraw page");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 0, 350, 40);
        title.setFont(font);

        amount = new JLabel("Amount");
        amount.setHorizontalAlignment(JLabel.CENTER);
        amount.setBounds(0, 60, 100, 30);
        amount.setFont(font);

        textField = new JTextField();
        textField.setBounds(120, 60, 140, 30);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(font);

        draw = new JButton("WithDraw");
        draw.setBounds(90, 120, 150, 30);
        draw.setFocusable(false);
        draw.setFont(font);
        draw.addActionListener(this);
        draw.setBackground(new Color(240, 74, 91));
        draw.setBorderPainted(true);

        this.add(title);
        this.add(draw);
        this.add(amount);
        this.add(textField);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == draw) {
            String amount = textField.getText();
            int finalAmount, amountInt = 0;
            try {
                amountInt = Integer.parseInt(amount);
            } catch (Exception exception) {
                textField.setText("");
                JOptionPane.showMessageDialog(null, "Wrong input try again!", "Input Error", JOptionPane.WARNING_MESSAGE);

            }
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
                finalAmount = rs.getInt(2);
                String transaction = rs.getString(3);
                System.out.println(finalAmount);
                System.out.println(amountInt);
                if (amountInt <= finalAmount) {
                    finalAmount = finalAmount - amountInt;
                    transaction += " #Withdraw - " + amountInt;
                    query = "UPDATE user_wallet set user_amount=" + finalAmount + ",user_last_transaction =\""+ transaction+ "\" where user_id=" + id;
                    System.out.println(query);
                    st.executeUpdate(query);
                    String msg = "With Draw Successful latest balance: " + finalAmount;
                    JOptionPane.showMessageDialog(null, msg, "Successful", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Success");
                    st.close();
                    con.close();
                    dispose();
                    new LoginPage();
                } else {
                    String msg = "Requested amount is not available";
                    JOptionPane.showMessageDialog(null, msg, "Sorry", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new WithDraw(id);
                }

            } catch (Exception ex) {
                System.out.println();
            }
        }
    }
}
