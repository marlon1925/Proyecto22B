import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ventasMostrarAdmin extends inicioAdmin{
    private JTable tableCajeros;
    private JTextField cajeroTetx;
    private JTable tableVentasReview;
    private JButton buscarButton;
    public JPanel JpVentasReview;
    private JButton regresarButtonVentasAdmin;

    public ventasMostrarAdmin (){
        conectar();
        crear_mostrar_cajeros();

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               crear_mostrar_ventasReview();
            }
        });
        regresarButtonVentasAdmin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                inicioAdmin frame6 = new inicioAdmin();
                frame6.setContentPane(frame6.PinicioAdmin);
                frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame6.pack();
                frame6.setSize(400, 400);
                frame6.setLocationRelativeTo(null);
                frame6.setVisible(true);
                dispose();
            }
        });
    }

    public void crear_mostrar_cajeros() {
        conectar();
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT CEDULA_CAJERO, NOMBRE_CAJERO, APELLIDO_CAJERO  FROM CAJERO");

            // Get metadata object
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create JTable and set model
            DefaultTableModel model = (DefaultTableModel) tableCajeros.getModel();

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
    public void crear_mostrar_ventasReview() {
        conectar();
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT COD_VENTA, COD_PRODFK, PRODUCTO_VENTA, CANTIDAD_VENTA, PRECIOPROD_VENTA FROM VENTAS WHERE CEDULA_CAJEROFK =" + cajeroTetx.getText());

            // Get metadata object
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create JTable and set model
            DefaultTableModel model = (DefaultTableModel) tableVentasReview.getModel();

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
            //con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","12345");
            //con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","123456");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","Pelota2002");

            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
