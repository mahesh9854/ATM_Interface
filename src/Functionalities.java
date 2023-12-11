import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Functionalities extends JFrame implements ActionListener {
    JButton transactionHistory,withDraw,deposit,transfer,quit;
    JLabel title;
    Font font=new Font("SANS_SERIF",Font.ITALIC,15);
    int id;
    Functionalities(int uid) {
        id=uid;
        ImageIcon icon1 = new ImageIcon("src\\img.png", "");
        this.setIconImage(icon1.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Functionalities Page");
        this.setSize(390, 390);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(234, 255, 98));

        title = new JLabel("Welcome Choose these");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 0, 390, 30);
        title.setFont(font);

        transactionHistory = new JButton("Transaction History");
        transactionHistory.setBounds(95, 40, 200, 50);
        transactionHistory.setFocusable(false);
        transactionHistory.setBackground(new Color(240, 74, 91));
        transactionHistory.setBorderPainted(false);
        transactionHistory.addActionListener(this);
        transactionHistory.setFont(font);
        transactionHistory.setBorderPainted(true);

        withDraw = new JButton("WithDraw Money");
        withDraw.setBounds(95, 100, 200, 50);
        withDraw.setFocusable(false);
        withDraw.setBackground(new Color(240, 74, 91));
        withDraw.setBorderPainted(false);
        withDraw.addActionListener(this);
        withDraw.setFont(font);
        withDraw.setBorderPainted(true);
        
        deposit = new JButton("Deposit Money");
        deposit.setBounds(95, 160, 200, 50);
        deposit.setFocusable(false);
        deposit.setBackground(new Color(240, 74, 91));
        deposit.setBorderPainted(false);
        deposit.addActionListener(this);
        deposit.setFont(font);
        deposit.setBorderPainted(true);
        
        transfer = new JButton("Transfer");
        transfer.setBounds(95, 220, 200, 50);
        transfer.setFocusable(false);
        transfer.setBackground(new Color(240, 74, 91));
        transfer.setBorderPainted(false);
        transfer.addActionListener(this);
        transfer.setFont(font);
        transfer.setBorderPainted(true);

        quit = new JButton("Exit");
        quit.setBounds(95, 280, 200, 50);
        quit.setFocusable(false);
        quit.setBackground(new Color(240, 74, 91));
        quit.setBorderPainted(false);
        quit.addActionListener(this);
        quit.setFont(font);
        quit.setBorderPainted(true);

        this.add(title);
        this.add(transactionHistory);
        this.add(withDraw);
        this.add(deposit);
        this.add(transfer);
        this.add(quit);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==transactionHistory) {
            dispose();
            new History(id);
        }
        else if (e.getSource()==withDraw) {
            dispose();
            new WithDraw(id);
        }else if (e.getSource()==deposit) {
            dispose();
            new Deposit(id);
        }else if (e.getSource()==transfer) {
            dispose();
            new Transfer(id);
        }
        else if (e.getSource()==quit) {
            System.exit(0);
        }
    }
}
