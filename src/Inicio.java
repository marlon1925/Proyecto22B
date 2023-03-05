import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Inicio extends  JFrame {
    private JButton Admin;
    private JButton cajeroButton;
    private JLabel Buho;
    private JPanel panel;


    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    Connection con;


    public Inicio(){
        super("LOGIN");
        setContentPane(panel);
        Admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAdmin frame = new loginAdmin();
                frame.setContentPane(frame.admin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                dispose();
            }
        });


        cajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cajeroInicio frame = new cajeroInicio();
                frame.setContentPane(frame.cajeroJ);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                dispose();
            }
        });

    }



    public static Connection getConection(){

        Connection con = null;
        String base= "minimarket";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        //String password = "marlon";
        //String password = "Pelota2002";
        String password = "123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            con.setAutoCommit(false);
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        return con;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Inicio();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(740, 550);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}


