package pwmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPage extends JFrame {
    public static AddPage instance = null;
    private final ImageIcon icon = new ImageIcon("C:\\Users\\drepa\\Development\\password-manager\\src\\main\\resources\\icon.png"); 

    public static AddPage getInstance() {
        if (instance == null)
            instance = new AddPage();

        return instance;
    }
    
    private AddPage() {
        this.setTitle("Add Account - Password Manager");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(this.icon.getImage());
        
        // Create labels and textboxes
        JLabel accountLabel = new JLabel("Account:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel notesLabel = new JLabel("Account Notes:");

        JTextField accountTextField = new JTextField();
        JTextField usernameTextField = new JTextField();
        JTextField passwordTextField = new JTextField();
        JTextField notesTextField = new JTextField();

        // Create Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountTextField.getText();
                String user = usernameTextField.getText();
                String pass = passwordTextField.getText();
                String notes = notesTextField.getText();

                PasswordHasher hasher = new PasswordHasher();
                String hashedPass = hasher.hashPassword(pass);

                DatabaseOperations dbMan = DatabaseOperations.getInstance();
                if (dbMan.addAccounts(account, user, pass, notes, hashedPass)) {
                    added();
                }
                else {
                    notCompleted();
                }
            }
        });

        // Set layout manager to BorderLayout
        this.setLayout(new BorderLayout());

        // Create panel for labels and textboxes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(accountLabel);
        panel.add(accountTextField);
        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(notesLabel);
        panel.add(notesTextField);

        // Add panel to the content pane
        this.getContentPane().add(panel, BorderLayout.CENTER);

        // Add Submit button to the bottom-right corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }   

    public void added() {
        JOptionPane.showMessageDialog(this, "Account Added");
    }

    public void notCompleted() {
        JOptionPane.showMessageDialog(this, "Could not add account");
    }
}
