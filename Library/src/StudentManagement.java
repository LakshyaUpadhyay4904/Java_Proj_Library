import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class StudentManagement extends JPanel implements ActionListener {

  private JTable studentTable;
  private DefaultTableModel tableModel;
  private JButton addButton, editButton, deleteButton, refreshButton;

  public StudentManagement() {
    setSize(1200, 700);
    // Define table model
    tableModel = new DefaultTableModel(new String[]{"Id","Name", "EnRollment No", "Course", "Section"}, 0);
    studentTable = new JTable(tableModel);

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

    JScrollPane scrollPane = new JScrollPane(studentTable);
    

    //JPanel mainPanel = new JPanel();
    // // Make a grid layout with 2 rows and 1 column
    //setLayout(new GridLayout(2,1,10,10));

    //add(Box.createVerticalGlue());
    add(buttonPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
     
    showStudentList();

  }

  // function to show the student list in table
  public void showStudentList() {
    // Clear the table model
    tableModel.setRowCount(0);

    // Add students to the table model
    for (Student student : Student.StudentList) {
      tableModel.addRow(new Object[]{student.Id,student.Name, student.EnrollNo, student.Course, student.Section});
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addButton) {
      StudentWindow studentWindow = new StudentWindow(null);
      if (studentWindow.ShowDialogBox()) {
        showStudentList();
      }
    } else if (e.getSource() == editButton) {
      int selectedRow = studentTable.getSelectedRow();
      if (selectedRow != -1) {
        Student student = (Student) Student.getStudent(selectedRow);
        StudentWindow studentWindow = new StudentWindow(student);
        if (studentWindow.ShowDialogBox()) {
          showStudentList();
        }
      } else {
        JOptionPane.showMessageDialog(this, "Please select a student to edit.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
      }
    } else if (e.getSource() == deleteButton) {
      // Get the selected student from the table
      int selectedRow = studentTable.getSelectedRow();
      if (selectedRow != -1) {
        Student student = (Student) tableModel.getValueAt(selectedRow, 0);
        
        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
          // Remove the student data from the table model
          //tableModel.removeRow(selectedRow);
          // Remove the student from the StudentList
          //Student.StudentList.remove(student);
          Student.deleteStudent(student.getId());
        }
      } else {
        JOptionPane.showMessageDialog(this, "Please select a student to delete.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
      }
      showStudentList();

    } else if (e.getSource() == refreshButton) {
      // Reload data from the underlying source (e.g., database)
      // Update the table model with fresh data
      showStudentList();
    }
  }

}
