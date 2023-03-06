import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class agregarCajero extends inicioAdmin {
    Connection con;
    private JTextField cedulaCajeroTxt;
    private JTextField nombreCajeroTxt;
    private JTextField apellidoCajeroTxt;
    private JTextField contraseñaCajeroTxt;
    private JLabel Apellido;
    private JButton crearButton;
    private JButton buscarButton;
    private JButton borrarButton;
    private JButton actualizarButton;
    private JTable tablaCajero;
    public JPanel JpCajeroAgregar;
    private JButton regresarButtonAdminCajero;

    public agregarCajero() {

        crear_mostrar_cajeros();

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                try {
                    // Agregar consulta SELECT para buscar la cédula antes de la inserción
                    ps = con.prepareStatement("SELECT CEDULA_CAJERO FROM CAJERO WHERE CEDULA_CAJERO = ?");
                    ps.setString(1, cedulaCajeroTxt.getText());
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "La cédula ya existe en la base de datos.");
                    } else {
                        // Código existente de inserción de registro
                        ps = con.prepareStatement("INSERT INTO CAJERO (CEDULA_CAJERO,NOMBRE_CAJERO,APELLIDO_CAJERO, CONTRASEÑA_CAJERO)" + "VALUES(?,?,?,?)");

                        ps.setString(1, cedulaCajeroTxt.getText());
                        ps.setString(2, nombreCajeroTxt.getText());
                        ps.setString(3, apellidoCajeroTxt.getText());
                        ps.setString(4, contraseñaCajeroTxt.getText());
                        int res = ps.executeUpdate();
                        if (res > 0) {
                            JOptionPane.showMessageDialog(null, "El cajero se guardo correctamente");
                            cedulaCajeroTxt.setText("");
                            nombreCajeroTxt.setText("");
                            apellidoCajeroTxt.setText("");
                            contraseñaCajeroTxt.setText("");
                            actualizarTablaCajero(); // Actualizar tabla después de insertar un nuevo producto
                        } else {
                            JOptionPane.showMessageDialog(null, "No se creo");
                        }
                    }

                    // Cerrar ResultSet
                    rs.close();

                } catch (HeadlessException | SQLException f) {
                    System.out.println(f);
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                try{
                    st = con.createStatement();
                    rs = st.executeQuery("SELECT * from CAJERO where CEDULA_CAJERO = " + cedulaCajeroTxt.getText());

                    while (rs.next()){
                        nombreCajeroTxt.setText(rs.getString("NOMBRE_CAJERO"));
                        apellidoCajeroTxt.setText(rs.getString("APELLIDO_CAJERO"));
                        contraseñaCajeroTxt.setText(rs.getString("CONTRASEÑA_CAJERO"));
                    }
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();

                try{
                    ps = con.prepareStatement("UPDATE CAJERO where CEDULA_CAJERO =" + cedulaCajeroTxt.getText());

                    ps.setString(1,nombreCajeroTxt.getText());
                    ps.setString(2, apellidoCajeroTxt.getText());
                    ps.setString(3, contraseñaCajeroTxt.getText());

                    int res = ps.executeUpdate();

                    if(res > 0){
                        JOptionPane.showMessageDialog(null,"Se actualizo correctamente");
                    }else {
                        JOptionPane.showMessageDialog(null,"No se actualizo correctamente");
                    }

                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                try{
                    ps = con.prepareStatement("DELETE FROM CAJERO WHERE CEDULA_CAJERO =" + cedulaCajeroTxt.getText());
                    int res = ps.executeUpdate();
                    if(res>0){
                        JOptionPane.showMessageDialog(null,"El cajero se elimino correctamente");
                        cedulaCajeroTxt.setText("");
                        nombreCajeroTxt.setText("");
                        apellidoCajeroTxt.setText("");
                        contraseñaCajeroTxt.setText("");
                    }else {
                        JOptionPane.showMessageDialog(null,"El cajero no se elimino correctamente");
                    }
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });

        regresarButtonAdminCajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicioAdmin frame3 = new inicioAdmin();
                frame3.setContentPane(frame3.PinicioAdmin);
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.pack();
                frame3.setSize(400, 400);
                frame3.setLocationRelativeTo(null);
                frame3.setVisible(true);
                dispose();
            }
        });
    }


        // Método para actualizar la tabla de productos
        private void actualizarTablaCajero () {
            DefaultTableModel model = (DefaultTableModel) tablaCajero.getModel();
            model.setRowCount(0); // Eliminar todas las filas actuales
            model.setColumnCount(0);
            crear_mostrar_cajeros(); // Volver a agregar las filas actualizadas
        }


        public void crear_mostrar_cajeros() {
        conectar();
            try {
                st = con.createStatement();
                rs = st.executeQuery("SELECT * FROM CAJERO");

                // Get metadata object
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // Create JTable and set model
                DefaultTableModel model = (DefaultTableModel) tablaCajero.getModel();

                // Add columns to table model

                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }

                // Add rows to table model
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }


            } catch (HeadlessException | SQLException f) {
                System.out.println(f);
            }

        }


    public void conectar(){
        try{
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","Pelota2002");
            //con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","123456");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


