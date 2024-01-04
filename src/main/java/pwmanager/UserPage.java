package pwmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserPage extends JFrame {
    public static UserPage instance = null;
    private DatabaseOperations dbMan = DatabaseOperations.getInstance();
    private DefaultTableModel tableModel;
    private JTable table;
    private final ImageIcon icon = new ImageIcon("C:\\Users\\drepa\\Development\\password-manager\\src\\main\\resources\\icon.png"); 

    public static UserPage getInstance() {
        if (instance == null) 
            instance = new UserPage();

        return instance;
    }

    private UserPage() {
        this.setTitle("Accounts - Password Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setResizable(false);
        this.setIconImage(this.icon.getImage());

        // Create a DefaultTableModel with column names
        String[] columnNames = {"Accounts", "Username", "Password", "Account Info"};
        this.tableModel = new DefaultTableModel(columnNames, 0);

        // Create a JTable with the DefaultTableModel
        this.table = new JTable(tableModel);
        dbMan.populateTable(tableModel);

        // Add the JTable to a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create buttons
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPage addAccount = AddPage.getInstance();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeletePage deletePage = DeletePage.getInstance();
            }
        });

        // Add buttons to a panel in the corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
    }
}
