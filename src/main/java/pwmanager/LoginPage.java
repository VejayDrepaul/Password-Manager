package pwmanager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    public static LoginPage instance = null;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static final Image icon = Toolkit.getDefaultToolkit().getImage("Project Resources\\icon.png");

    private LoginPage() {
        this.setTitle("Login - Password Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        this.setIconImage(icon);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        this.usernameField = new JTextField(20);
        this.add(usernameLabel, gbc);
        gbc.gridx++;
        this.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField(20);
        this.add(passwordLabel, gbc);
        gbc.gridx++;
        this.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckCredential();
            }
        });
        this.add(loginButton, gbc);

        this.setLocationRelativeTo(null);
    }

    public static LoginPage getInstance() {
        if (instance == null) 
            instance = new LoginPage();

        return instance;
    }

    public void CheckCredential() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if ("yourUsername".equals(username) && "yourPassword".equals(password)) {
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}