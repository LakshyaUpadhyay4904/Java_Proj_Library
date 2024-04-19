import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;


public class BookManagement extends JPanel implements ActionListener {
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton, refreshButton;

    public BookManagement() {
        setSize(1200, 700);
        // Define table model
        tableModel = new DefaultTableModel(new String[]{"Id","Title", "Author", "Publisher", "Quantity"}, 0);
        bookTable = new JTable(tableModel);

        // Add buttons
        addButton = new JButton("Add");
        addButton.addKeyListener(Global.keyAdapter);
        addButton.addActionListener(this);
        editButton = new JButton("Edit");
        editButton.addKeyListener(Global.keyAdapter);
        editButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.addKeyListener(Global.keyAdapter);
        deleteButton.addActionListener(this);
        refreshButton = new JButton("Refresh");
        refreshButton.addKeyListener(Global.keyAdapter);
        refreshButton.addActionListener(this);

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.setBackground(Color.BLUE);

        JScrollPane scrollPane = new JScrollPane(bookTable);
        

        //JPanel mainPanel = new JPanel();
        // // Make a grid layout with 2 rows and 1 column
        //setLayout(new GridLayout(2,1,10,10);

        //add(Box.createVerticalGlue());
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        showBookList();

    }

    // function to show the book list in table
    public void showBookList() {
        // Clear the table model
        tableModel.setRowCount(0);

        // Add books to the table model
        for (Book book : Book.BookList) {
            tableModel.addRow(new Object[]{book.Id,book.Title, book.Author, book.Publisher, book.Quantity});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Add button clicked
            // Create a new book
            BookWindow addDialog = new BookWindow(null);
            if (addDialog.ShowDialogBox()) {
                showBookList();
            }
        } else if (e.getSource() == editButton) {
            // Edit button clicked
            // Get the selected row
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow == -1) {
                // No row selected
                JOptionPane.showMessageDialog(this, "Please select a book to edit.");
                return;
            }
            // Get the book ID from the selected row
            int bookId = (int) bookTable.getValueAt(selectedRow, 0);
            // Find the book in the list
            Book bookToEdit = null;
            for (Book book : Book.BookList) {
                if (book.Id == bookId) {
                    bookToEdit = book;
                    break;
                }
            }
            if (bookToEdit == null) {
                // Book not found
                JOptionPane.showMessageDialog(this, "Book not found.");
                return;
            }
            // Show the edit dialog
            BookWindow editDialog = new BookWindow(bookToEdit);
            editDialog.setVisible(true);
            showBookList();
        } else if (e.getSource() == deleteButton) {
            // Delete button clicked
            // Get the selected row
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow == -1) {
                // No row selected
                JOptionPane.showMessageDialog(this, "Please select a book to delete.");
                return;
            }
            // Get the book ID from the selected row
            int bookId = (int) bookTable.getValueAt(selectedRow, 0);
            // Find the book in the list
            Book bookToDelete = null;
            for (Book book : Book.BookList) {
                if (book.Id == bookId) {
                    bookToDelete = book;
                    break;
                }
            }
            if (bookToDelete == null) {
                // Book not found
                JOptionPane.showMessageDialog(this, "Book not found.");
                return;
            }
            // Confirm deletion
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this book?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Delete the book
                Book.BookList.remove(bookToDelete);
                showBookList();
            }
        } else if (e.getSource() == refreshButton) {
            // Refresh button clicked
            showBookList();
        }
    }
}
