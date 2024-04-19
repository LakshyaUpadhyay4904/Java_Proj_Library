import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent; // Import the ActionEvent class
import java.awt.event.ActionListener;


public class BookWindow extends JDialog implements ActionListener{
    
    JTextField titleField;
    JTextField authorField;
    JTextField publisherField;
    JTextField quantityField;
    private JButton okButton;
    private JButton cancelButton;
    private boolean isDataSaved;
    int bookId = -1;
    Book selBook = new Book();

    public BookWindow(Book book) {
        try {
            setModal(true);
            InitializeComponent();
            setSize(400, 200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (book != null) {
                bookId = book.Id;
                titleField.setText(book.Title);
                authorField.setText(book.Author);
                publisherField.setText(book.Publisher);
                quantityField.setText(String.valueOf(book.Quantity));
                selBook = book;
            }
            else {
                titleField.setText("");
                authorField.setText("");
                publisherField.setText("");
                quantityField.setText("");
                
            }
        } 
        catch (Exception e) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean ShowDialogBox() {
        try {
            setVisible(true);
            return isDataSaved;
        } 
        catch (Exception e) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void InitializeComponent() {
        try {
            // Create a panel
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets.set(5, 5, 5, 5);

            // Add title label
            JLabel titleLabel = new JLabel("Title");
            panel.add(titleLabel, gbc);

            // Add title field
            gbc.gridx = 1;
            titleField = new JTextField();
            panel.add(titleField, gbc);

            // Add author label
            gbc.gridx = 0;
            gbc.gridy = 1;
            JLabel authorLabel = new JLabel("Author");
            panel.add(authorLabel, gbc);

            // Add author field
            gbc.gridx = 1;
            authorField = new JTextField();
            panel.add(authorField, gbc);

            // Add publisher label
            gbc.gridx = 0;
            gbc.gridy = 2;
            JLabel publisherLabel = new JLabel("Publisher");
            panel.add(publisherLabel, gbc);

            // Add publisher field
            gbc.gridx = 1;
            publisherField = new JTextField();
            panel.add(publisherField, gbc);

            // Add quantity label
            gbc.gridx = 0;
            gbc.gridy = 3;
            JLabel quantityLabel = new JLabel("Quantity");
            panel.add(quantityLabel, gbc);

            // Add quantity field
            gbc.gridx = 1;
            quantityField = new JTextField();
            panel.add(quantityField, gbc);

            // Add buttons
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            okButton = new JButton("OK");
            okButton.addActionListener(this);
            panel.add(okButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);
            panel.add(cancelButton, gbc);

            add(panel, BorderLayout.CENTER);


        } catch (Exception e) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == okButton) {
                // OK button clicked
                // Save the data
                if (bookId == -1) {
                    // Add a new book
                    Book book = new Book();
                    book.addBook(titleField.getText(), authorField.getText(), publisherField.getText(), Integer.parseInt(quantityField.getText()));
                } else {
                    // Edit the existing book
                    Book.editBook(bookId, selBook);
                }
                isDataSaved = true;
                setVisible(false);
            } else if (e.getSource() == cancelButton) {
                // Cancel button clicked
                isDataSaved = false;
                setVisible(false);
            }
        } catch (Exception ex) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
