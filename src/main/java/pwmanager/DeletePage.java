package pwmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeletePage extends JFrame{
    public static DeletePage instance = null;
    private final ImageIcon icon = new ImageIcon("C:\\Users\\drepa\\Development\\password-manager\\src\\main\\resources\\icon.png"); 

    public static DeletePage getInstance() {
        if (instance == null)
            instance = new DeletePage();

        return instance;
    }
    private DeletePage() {
        this.setTitle("Account Name Frame");
        this.setSize(500, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());

        // Create label
        JLabel accountNameLabel = new JLabel("Account Name:");

        // Create textbox
        JTextField accountNameTextField = new JTextField();

        // Create Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations dbMan = DatabaseOperations.getInstance();
                if (dbMan.deleteAccount(accountNameTextField.getText())) {
                    added();
                }
                else {
                    notCompleted();
                }
            }
        });

        // Set layout manager to BorderLayout
        this.setLayout(new BorderLayout());

        // Add label to the left (WEST), textbox to the center, and button to the south (BOTTOM)
        this.add(accountNameLabel, BorderLayout.WEST);
        this.add(accountNameTextField, BorderLayout.CENTER);
        this.add(submitButton, BorderLayout.SOUTH);

        this.setVisible(true);
    } 

    public void added() {
        JOptionPane.showMessageDialog(this, "Account Removed");
    }

    public void notCompleted() {
        JOptionPane.showMessageDialog(this, "Could not remove account");
    }
}
