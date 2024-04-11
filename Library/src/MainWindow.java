import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

    JButton StudentBtn;
    JButton BookBtn;
    JButton IssueBtn;
    JButton ReturnBtn;
    JButton LogoutBtn;
    JPanel menuPanel;
    StudentManagement studentManagement;

    MainWindow() {

        init();
        readDataFromCsv();
    }

    //create  the main window and set its properties
    public void init() {
        
        try {
            this.setTitle("Main Window");
            setLayout(new BorderLayout());
            setBackground(new Color(128,128,255));
    
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1200, 700);
            setMinimumSize(new Dimension(300,400));;
            setLocationRelativeTo(null);

            // Add window listener to handle closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Call your function here before closing
                saveDataToCsv();
            }
        });
    
            //create a menu bar
            menuPanel = new JPanel();
            StudentBtn = new JButton("Students");
            BookBtn = new JButton("Books");
            IssueBtn = new JButton("Issue");
            ReturnBtn = new JButton("Return");
            LogoutBtn = new JButton("Logout");
    
            StudentBtn.addActionListener(this);
            menuPanel.add(StudentBtn);
            menuPanel.add(BookBtn);
            menuPanel.add(IssueBtn);
            menuPanel.add(ReturnBtn);
            menuPanel.add(LogoutBtn);
    
            add(menuPanel, BorderLayout.NORTH);
    
            // mainPane = new JTabbedPane();
            // mainPane.add("Students", new StudentManagement());
            // mainPane.add("Books", new JPanel());
            // mainPane.add("Issue", new JPanel());    
            // mainPane.add("Return", new JPanel());
            // mainPane.setBackground(new Color(128,128,255));
            // add(mainPane, BorderLayout.CENTER);

            //mainPanel = new JPanel();
            //mainPanel.setBackground(new Color(128,128,255));
            //add(mainPanel, BorderLayout.CENTER);
            studentManagement = new StudentManagement();
            add(studentManagement, BorderLayout.CENTER);
            studentManagement.setVisible(true);
            setVisible(true);

        } catch (Exception ex) {
            // Code to handle the exception, including displaying a pop-up message
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    
    @Override
  public void actionPerformed(ActionEvent e) {
      try {
          if (e.getSource() == StudentBtn) {
              //add code to open student management window
              
              }
              else if (e.getSource() == BookBtn) {
              System.out.println("Book Button Clicked");
              }
              else if (e.getSource() == IssueBtn) {
              System.out.println("Issue Button Clicked");
              }
              else if (e.getSource() == ReturnBtn) {
              System.out.println("Return Button Clicked");
              }
              else if (e.getSource() == LogoutBtn) {
              System.out.println("Logout Button Clicked");
          }
    } catch (Exception ex) {
        // Code to handle the exception, including displaying a pop-up message
        JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

    public void saveDataToCsv() 
    {
        // Code to save data to CSV file
        System.out.println("Saving data to CSV file...");
        String[] csvList = {"Student","Book","Issue","Return"};
        // Loop through the list and save each item of StudentList to a CSV file
        for (String item : csvList) {
            if (item == "Student") {
                // Save student data to CSV file
                Student.ListToCsv();
                System.out.println("Saving student data to CSV file...");
            } else if (item == "Book") {
                // Save book data to CSV file
                System.out.println("Saving book data to CSV file...");
            } else if (item == "Issue") {
                // Save issue data to CSV file
                System.out.println("Saving issue data to CSV file...");
            } else if (item == "Return") {
                // Save return data to CSV file
                System.out.println("Saving return data to CSV file...");
            }
        }
    }

    //method to read data from csv file
    public void readDataFromCsv() {
        // Code to read data from CSV file
        String[] csvList = {"Student","Book","Issue","Return"};
        // Loop through the list and read each item of StudentList from a CSV file
        for (String item : csvList) {
            if (item == "Student") {
                // Read student data from CSV file
                Student.CsvToList();
                System.out.println("Reading student data from CSV file...");
            } else if (item == "Book") {
                // Read book data from CSV file
                System.out.println("Reading book data from CSV file...");
            } else if (item == "Issue") {
                // Read issue data from CSV file
                System.out.println("Reading issue data from CSV file...");
            } else if (item == "Return") {
                // Read return data from CSV file
                System.out.println("Reading return data from CSV file...");
            }
        }
    }

}
