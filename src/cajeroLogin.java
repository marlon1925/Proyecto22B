import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cajeroLogin extends Inicio {

    public JPanel cajeroJ;
    private JTextField txtCedulaCajero;
    private JButton regresarButton;
    private JLabel LOGINCAJERO;

    private JPasswordField contraseñaCajero;
    private JButton botonIngresarCajero;
    public static String usuarioCajero;



    public cajeroLogin(){

        botonIngresarCajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con = getConection();


                try{
                    usuarioCajero = txtCedulaCajero.getText(); //Obtiene el numero de cedula del cajero
                    char[] passwordCajero = contraseñaCajero.getPassword(); // obtener la contraseña ingresada en el JPasswordField
                    String passwordStringCajero = new String(passwordCajero); // convertir la contraseña de char[] a String


                    String query = "SELECT * FROM minimarket.CAJERO WHERE CEDULA_CAJERO =? AND CONTRASEÑA_CAJERO=?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, usuarioCajero);
                    ps.setString(2, passwordStringCajero);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        ventas frame6 = new ventas();
                        frame6.setContentPane(frame6.Jventas);
                        frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame6.pack();
                        frame6.setSize(600, 400);
                        frame6.setLocationRelativeTo(null);
                        frame6.setVisible(true);
                        dispose();

                    } else {
                        // el usuario y/o la contraseña son incorrectos, hacer algo aquí
                        //JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                        // aqui falta codigo
                        //tonto verga de marlon
                    }

                }catch (SQLException f){
                    System.out.println(f);
                }
            }
        });



        //FUNCIONALIDAD DEL BOTON DE REGRESAR
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Inicio();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(740, 550);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                dispose();
            }
        });
    }
    public void conectar(){
        try{
            //con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","Pelota2002");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","123456");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
