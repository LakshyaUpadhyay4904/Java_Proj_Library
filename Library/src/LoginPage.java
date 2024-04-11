import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JButton cancelButton;

  public LoginPage() {
    super("Login");
    InitializeComponent();
    setLocationRelativeTo(null);
  }


  private void InitializeComponent(){
    setSize(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0,2,10,10));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    GridBagConstraints c = new GridBagConstraints();

    JLabel usernameLabel = new JLabel("Email or Phone");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    panel.add(usernameLabel, c);

    usernameField = new JTextField(20);
    c.gridx = 1;
    c.gridy = 0;
    usernameField.addKeyListener(Global.keyAdapter);
    
    panel.add(usernameField, c);
    
    JLabel passwordLabel = new JLabel("Password");
    c.gridx = 0;
    c.gridy = 1;
    passwordLabel.addKeyListener(Global.keyAdapter);
    panel.add(passwordLabel, c);

    passwordField = new JPasswordField(20);
    c.gridx = 1;
    c.gridy = 1;
    panel.add(passwordField, c);

    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    c.gridx = 0;
    c.gridy = 2;
    cancelButton.addKeyListener(Global.keyAdapter);
    cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    cancelButton.setBackground(new Color(128,128,255));
    panel.add(cancelButton, c);

    loginButton = new JButton("Login");
    loginButton.addActionListener(this);
    c.gridx = 1;
    c.gridy = 2;
    loginButton.addKeyListener(Global.keyAdapter);
    loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    loginButton.setBackground(new Color(128,128,255));

    panel.add(loginButton, c);

    add(panel);
    setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton) {
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());

      // Simulate login process (normally you would connect to a database or authentication service)
      if (username.equals("a") && password.equals("a")) {
        new MainWindow();
        dispose();
      } else {
        JOptionPane.showMessageDialog(this, "Invalid username or password.");
      }
    }
    else if (e.getSource() == cancelButton) {
      System.exit(0);
    }
  }
}