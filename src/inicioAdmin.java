
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class inicioAdmin extends loginAdmin {

    private JButton agregarP;
    private JButton ventasButton;
    private JButton cajerosButton;
    public JPanel PinicioAdmin;
    private JLabel bienvenidoAdmin;
    private JButton regresarButtonInicioAdmin;

    public inicioAdmin(){
        try {
            con = getConection();
            ps = con.prepareStatement("SELECT NOMBRE_ADMIN FROM administrador WHERE CEDULA_ADMIN = ?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                String nombreAdmin = rs.getString("NOMBRE_ADMIN");
                bienvenidoAdmin.setText("Bienvenido, " + nombreAdmin + "!");
            }
        } catch (HeadlessException | SQLException f) {
            System.out.println(f);
        }

        agregarP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductos frame4 = new agregarProductos();
                frame4.setContentPane(frame4.jp_produtos);
                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame4.pack();
                frame4.setSize(800, 500);
                frame4.setLocationRelativeTo(null);
                frame4.setVisible(true);
                dispose();
            }
        });
        cajerosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCajero frame5 = new agregarCajero();
                frame5.setContentPane(frame5.JpCajeroAgregar);
                frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame5.pack();
                frame5.setSize(800, 500);
                frame5.setLocationRelativeTo(null);
                frame5.setVisible(true);
                dispose();
            }
        });

        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventasMostrarAdmin frame6 = new ventasMostrarAdmin();
                frame6.setContentPane(frame6.JpVentasReview);
                frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame6.pack();
                frame6.setSize(800, 500);
                frame6.setLocationRelativeTo(null);
                frame6.setVisible(true);
                dispose();
            }
        });

        regresarButtonInicioAdmin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio frame6 = new Inicio();
                frame6.setContentPane(frame6.panel);
                frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame6.pack();
                frame6.setSize(740, 550);
                frame6.setLocationRelativeTo(null);
                frame6.setVisible(true);
                dispose();
            }
        });
    }

}
