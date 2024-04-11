import java.awt.Component; // Import the Component class
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class Global {
    public static KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ((Component)e.getSource()).transferFocus();
                if(e.getSource() instanceof JButton){
                    ((JButton)e.getSource()).doClick();
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_COMMA) {
                e.consume();
            }
        }
    };
     
}
