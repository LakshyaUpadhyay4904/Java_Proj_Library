import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.awt.event.ActionEvent; // Import the ActionEvent class
import java.awt.event.ActionListener;

public class StudentWindow extends JDialog implements ActionListener{
    
    JTextField nameField;
    JTextField courseField;
    JTextField secField;
    JTextField enrollNoField;
    JTextField ageField;
    private JButton okButton;
    private JButton cancelButton;
    private boolean isDataSaved;
    int studentId = -1;
    Student selStudent = new Student();

    public StudentWindow(Student student) {
        try {
            setModal(true);
            InitializeComponent();
            setSize(400, 200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (student != null) {
                studentId = student.Id;
                nameField.setText(student.Name);
                courseField.setText(student.Course);
                secField.setText(student.Section);
                enrollNoField.setText(student.EnrollNo);
                selStudent = student;
            }
            else {
                nameField.setText("");
                courseField.setText("");
                secField.setText("");
                enrollNoField.setText("");
                
            }
        } 
        catch (Exception e) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean ShowDialogBox() {
        try {
            isDataSaved = false;
            setVisible(true);
            return isDataSaved;
        } catch (Exception e) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // public int ShowDialogBox(){
    //     //return InitializeComponent();
    //     if (InitializeComponent() == JOptionPane.OK_OPTION) {
    //         String name = nameField.getText();
    //         String course = courseField.getText();
    //         String sec = secField.getText();
    //         String enrollNo = enrollNoField.getText();
    

    //         // Add new student data to table model
    //         Student student = new Student();
    //         student.addStudent(0, name, course, sec, enrollNo, "", "", LocalDate.now());
    //         return 1;
    //     }
    //     else return 0;
    // }

    private void InitializeComponent(){        
        try {
            // Open dialog to add new student 
            nameField = new JTextField(20);
            nameField.addKeyListener(Global.keyAdapter);
            nameField.setSize(new Dimension(20, 40)); 

            courseField = new JTextField(20);
            courseField.addKeyListener(Global.keyAdapter);
            courseField.setSize(new Dimension(20, 40)); 

            secField = new JTextField(20);
            secField.addKeyListener(Global.keyAdapter);
            secField.setSize(new Dimension(20, 40));

            enrollNoField = new JTextField(20);
            enrollNoField.addKeyListener(Global.keyAdapter);
            enrollNoField.setSize(new Dimension(20, 40)); 

            JPanel addPanel = new JPanel(new GridBagLayout());
            JPanel buttonPanel = new JPanel();
    
            GridBagConstraints c = new GridBagConstraints();
    
            JLabel nameLabel = new JLabel("Name:");
            c.gridx = 0;
            c.gridy = 0;
            addPanel.add(nameLabel, c);
            c.gridx = 1;
            c.gridy = 0;
            addPanel.add(nameField, c);
    
            JLabel enrollNoLabel = new JLabel("Enrollment No.:");
            c.gridx = 0;
            c.gridy = 1;
            addPanel.add(enrollNoLabel, c);
            c.gridx = 1;
            c.gridy = 1;
            addPanel.add(enrollNoField, c);
    
            JLabel classLabel = new JLabel("course:");
            c.gridx = 0;
            c.gridy = 2;
            addPanel.add(classLabel, c);
            c.gridx = 1;
            c.gridy = 2;
            addPanel.add(courseField, c);
    
            JLabel divLabel = new JLabel("Section:");
            c.gridx = 0;
            c.gridy = 3;
            addPanel.add(divLabel, c);
            c.gridx = 1;
            c.gridy = 3;
            addPanel.add(secField, c);

            add(addPanel, BorderLayout.CENTER);

            okButton = new JButton("OK");
            okButton.addActionListener(this);
            okButton.addKeyListener(Global.keyAdapter);
            
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);
            cancelButton.addKeyListener(Global.keyAdapter);
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);

        } catch (Exception e) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()== okButton){
                String name = nameField.getText();
                    String course = courseField.getText();
                    String sec = secField.getText();
                    String enrollNo = enrollNoField.getText();
            
                    // Add new student data to table model
                    //Student student = new Student();
                    if(studentId != -1){
                        selStudent.Name = name;
                        selStudent.Course = course;
                        selStudent.Section = sec;
                        selStudent.EnrollNo = enrollNo;

                        //method to edit student
                        //Student.editStudent(studentId, selStudent);
                    }
                    else{

                        selStudent.addStudent(name, course, sec, enrollNo, "", "", LocalDate.now());
                    }
                    isDataSaved = true;
                    dispose();
            }
            else if(e.getSource() == cancelButton){
                dispose();
            }
        } catch (Exception ex) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
