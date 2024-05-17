import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class gui {
    private JButton button1;
    private JPanel panel1;
    private JTextField celsiusTextField;
    private JLabel fahrenheitLabel;
    private JPasswordField fahrenheitPasswordField;
    private JTextField fahrenheitTextField;
    private boolean cellast;

    public gui() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                fahrenheitTextField.setText(String.valueOf((Double.parseDouble(celsiusTextField.getText()) * 9/5) + 32));
            }
        });
        celsiusTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                cellast=true;
            }
        });
        fahrenheitTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                cellast=false;
            }
        });
    }
    public static void main(String Username){
        JFrame frame = new JFrame("gui");
        frame.setContentPane(new gui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("gui");
        frame.setContentPane(new gui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
