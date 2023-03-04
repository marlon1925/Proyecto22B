import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class cajeroInicio extends Inicio {

    public JPanel cajeroJ;
    private JTextField txtCedulaCajero;
    private JButton regresarButton;
    private JLabel LOGINCAJERO;
    private JPasswordField passwordField1;
    private JPasswordField contraseñaCajero;
    private JButton botonIngresarCajero;
    static String usuarioCajero;



    public cajeroInicio(){

        botonIngresarCajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con = getConection();


                try{
                    usuarioCajero = txtCedulaCajero.getText(); //Obtiene el numero de cedula del cajero
                    char[] passwordCajero = contraseñaCajero.getPassword(); // obtener la contraseña ingresada en el JPasswordField
                    String passwordStringCajero = new String(passwordCajero); // convertir la contraseña de char[] a String

                    String query = "SELECT * FROM minimarket.administrador WHERE CEDULA_CAJERO=? AND CONTRASEÑA_CAJERO=?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, usuarioCajero);
                    ps.setString(2, passwordStringCajero);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        // el usuario y la contraseña son correctos, hacer algo aquí
                        inicioAdmin frame3 = new inicioAdmin();
                        frame3.setContentPane(frame3.PinicioAdmin);
                        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame3.pack();
                        frame3.setSize(400, 200);
                        frame3.setLocationRelativeTo(null);
                        frame3.setVisible(true);
                        dispose();

                    } else {
                        // el usuario y/o la contraseña son incorrectos, hacer algo aquí
                        JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
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
}
