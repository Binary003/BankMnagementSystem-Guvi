package bank.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JButton login, clear, signup;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setLayout(null);
        setTitle("AUTOMATED TELLER MACHINE");

        /* used to import the images from the sources */
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        /* setting the image height and width */
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);

        /* setting the bounds manually */
        label.setBounds(70, 10, 100, 100);
        /* used to add the component to the window */
        add(label);

        /* used to write the text for welcome to ATM */
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        /* used to write the card no. and setting it's height and size */
        JLabel cardno = new JLabel("Card No.");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 40);
        add(cardno);

        /* used to import the text field and it's position for card no. */
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 160, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        /* used to write the Pin and setting it's height and size */
        JLabel pin = new JLabel("Pin");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 40);
        add(pin);

        /* used to import the text field and it's position for pin */
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 230, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        /* used to import the button for sign in and it's color and position */
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        /* Event listener for action */
        login.addActionListener(this);
        add(login);

        /* used to import the button for Clear and it's color and position */
        clear = new JButton("Clear");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        /* Event listener for action */
        clear.addActionListener(this);
        add(clear);

        /* used to import the button for Sign up and it's color and position */
        signup = new JButton("Sign up");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        /* Event listener for action */
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);

        /* setting the size of the window */
        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");

        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where card_number = '" + cardnumber + "'and pin_number = '" + pinnumber
                    + "'";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);

        }

    }

    public static void main(String[] args) {
        new Login();
    }
}