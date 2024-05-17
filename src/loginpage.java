import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class loginpage {
    private JTextField usernameTextField;
    private static String uname;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel login;
    private JLabel Title;
    private JLabel passwordlabel;
    private JButton createAccountButton;
    private MessageDigest digest;
    private final Dictionary pass = new Hashtable();
    private static boolean logedin = false;

    public loginpage() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
                String username = usernameTextField.getText();
                String password = Arrays.toString(passwordField1.getPassword());
                byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);
                try {
                    String hash = (String) pass.get(username);
                    if (hash.equals(hashedPasswordString)){
                        Title.setText("Welcome " + username);
                        passwordField1.setText(null);
                        usernameTextField.setText(null);
                        uname=username;
                        logedin=true;
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                    }else{
                        Title.setText("Invalid Password");
                    }
                } catch (NullPointerException _) {
                    Title.setText("Username not found");
                    passwordField1.setText(null);
                    usernameTextField.setText(null);
                }



            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
                String password = Arrays.toString(passwordField1.getPassword());
                byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);
                pass.put(usernameTextField.getText(),hashedPasswordString);
                passwordField1.setText(null);
                usernameTextField.setText(null);
            }
        });
    }
    public static String login(){
        JFrame frame = new JFrame("loginpage");
        frame.setContentPane(new loginpage().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        while (!logedin) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        frame.setVisible(false);
        return(uname);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new loginpage().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
