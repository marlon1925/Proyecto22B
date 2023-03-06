import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class loginAdmin extends Inicio{
    public JPanel admin;
    public JTextField cedulaAdmin;
    private JPasswordField contraseñaAdmin;
    private JButton botonInicioAdmin;
    private JButton botonRegresar;
    public static String usuario;


    public loginAdmin(){

        botonInicioAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con = getConection();

                try {
                    usuario = cedulaAdmin.getText(); // obtener el usuario ingresado en el JTextField
                    char[] password = contraseñaAdmin.getPassword(); // obtener la contraseña ingresada en el JPasswordField
                    String passwordString = new String(password); // convertir la contraseña de char[] a String

                    String query = "SELECT * FROM minimarket.administrador WHERE CEDULA_ADMIN=? AND CONTRASEÑA_ADMIN=?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, usuario);
                    ps.setString(2, passwordString);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        // el usuario y la contraseña son correctos, hacer algo aquí
                        inicioAdmin frame3 = new inicioAdmin();
                        frame3.setContentPane(frame3.PinicioAdmin);
                        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame3.pack();
                        frame3.setSize(400, 400);
                        frame3.setLocationRelativeTo(null);
                        frame3.setVisible(true);
                        dispose();

                    } else {
                        // el usuario y/o la contraseña son incorrectos, hacer algo aquí
                        JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                    }
                } catch (HeadlessException | SQLException f) {
                    System.out.println(f);
                }
            }
        });


        botonRegresar.addActionListener(new ActionListener() {
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
