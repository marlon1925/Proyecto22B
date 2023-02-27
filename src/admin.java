import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class admin extends Inicio{
    public JPanel admin;
    private JTextField cedulaAdmin;
    private JPasswordField contraseñaAdmin;
    private JButton botonInicioAdmin;
    private JButton regresarButton;

    public admin (){

        botonInicioAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con = getConection();

                try {
                    String usuario = cedulaAdmin.getText(); // obtener el usuario ingresado en el JTextField
                    char[] password = contraseñaAdmin.getPassword(); // obtener la contraseña ingresada en el JPasswordField
                    String passwordString = new String(password); // convertir la contraseña de char[] a String

                    String query = "SELECT * FROM minimarket.administrador WHERE CEDULA=? AND CONTRASEÑA=?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, usuario);
                    ps.setString(2, passwordString);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        // el usuario y la contraseña son correctos, hacer algo aquí

                    } else {
                        // el usuario y/o la contraseña son incorrectos, hacer algo aquí
                        System.out.println("Usuario o contraseña incorrectos");
                    }
                } catch (SQLException f) {
                    System.out.println(f);
                }
            }
        });


        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
