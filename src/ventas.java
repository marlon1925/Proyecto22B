import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ventas extends cajeroLogin {
    Connection con;
    public JPanel Jventas ;
    private JTable tablaProductos;
    private JTextField txtIdProductos;
    private JTextField txtPrecioProducto;
    private JButton agregarbutton;
    private JButton buscarbotton;
    private JTextField txtNombreProducto;
    private JTextField txtCantidadProducto;
    private JTextField txtIdCodigoVenta;
    private JTextField txtIdtotalProducto;
    private JTable tablaVentas;
    private JButton finalizarVentaButton;
    private JButton regresarButton;

    public ventas(){
    conectar();

      crear_mostrar_productos();
      crear_mostrar_ventas();

      nuevoCodigoVenta();

      txtNombreProducto.setEditable(false);
      txtPrecioProducto.setEditable(false);
      txtIdtotalProducto.setEditable(false);


        buscarbotton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

              conectar();

              try{
                  st = con.createStatement();
                  rs = st.executeQuery("SELECT * FROM PRODUCTOS WHERE COD_PROD = '"+txtIdProductos.getText()+"';");

                  while (rs.next()){
                      txtPrecioProducto.setText(rs.getString("PRECIO"));
                      txtNombreProducto.setText(rs.getString("NOM_PROD"));
                      txtIdProductos.setEditable(false);
                  }
              }catch (Exception w){
                  System.out.println(w);
              }
          }
      });


        agregarbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();

                try {
                    // Obtener los valores de los campos de texto y convertirlos a números
                    double precio = Double.parseDouble(txtPrecioProducto.getText());
                    int cantidad1 = Integer.parseInt(txtCantidadProducto.getText());

                    // Calcular el total de la venta
                    double totalVenta = precio * cantidad1;

                    txtIdtotalProducto.setText(String.valueOf(totalVenta));

                    ps = con.prepareStatement("INSERT INTO VENTAS(COD_VENTA, Producto_Venta, PRECIOPROD_VENTA, CANTIDAD_VENTA, TOTAL, " +
                            "CEDULA_CAJEROFK, COD_PRODFK)" +
                            "values (?,?,?,?,?,?,?)");
                    ps.setString(1, txtIdCodigoVenta.getText());
                    ps.setString(2, txtNombreProducto.getText());
                    ps.setString(3, txtPrecioProducto.getText());
                    ps.setString(4, txtCantidadProducto.getText());
                    ps.setString(5, txtIdtotalProducto.getText());
                    ps.setString(6, usuarioCajero);
                    ps.setString(7, txtIdProductos.getText());
                    rs = st.executeQuery("SELECT STOCK FROM PRODUCTOS WHERE COD_PROD = '"+txtIdProductos.getText()+"';");
                    rs.next();
                    int stockActual = rs.getInt("STOCK");

                    // Restar la cantidad ingresada por el usuario del stock actual
                    int cantidad = Integer.parseInt(txtCantidadProducto.getText());
                    int stockNuevo = stockActual - cantidad;

                    // Actualizar el valor de STOCK en la base de datos
                    st.executeUpdate("UPDATE PRODUCTOS SET STOCK = "+stockNuevo+" WHERE COD_PROD = '"+txtIdProductos.getText()+"';");
                    int res = ps.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(null,"Se creo");

                        // Obtener el valor actual de STOCK del producto


                        txtIdProductos.setText("");
                        txtNombreProducto.setText("");
                        txtCantidadProducto.setText("");
                        txtIdtotalProducto.setText("");
                        txtPrecioProducto.setText("");
                        nuevoCodigoVenta();
                        actualizarTablaVentas();
                        actualizarTablaProductos();

                    }else {
                        JOptionPane.showMessageDialog(null, "No se creo");
                    }
                } catch (HeadlessException|SQLException ex) {
                JOptionPane.showMessageDialog(null,ex);
                }
            }
        });

        regresarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                ventas frame6 = new ventas();
                frame6.setContentPane(frame6.Jventas);
                frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame6.pack();
                frame6.setSize(600, 400);
                frame6.setLocationRelativeTo(null);
                frame6.setVisible(true);
                dispose();
            }
        });
    }

    public void crear_mostrar_productos(){
        conectar();
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM PRODUCTOS");

            // Get metadata object
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create JTable and set model
            DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();

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

        }catch (HeadlessException | SQLException f){
            JOptionPane.showMessageDialog(null,f);
        }
    }

    private void actualizarTablaProductos () {
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setRowCount(0); // Eliminar todas las filas actuales
        model.setColumnCount(0);
        crear_mostrar_productos(); // Volver a agregar las filas actualizadas
    }

    public void crear_mostrar_ventas(){
        conectar();
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM VENTAS");

            // Get metadata object
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create JTable and set model
            DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();

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

        }catch (HeadlessException | SQLException f){
            //JOptionPane.showMessageDialog(null,f);
        }
    }

    private void actualizarTablaVentas () {
        DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();
        model.setRowCount(0); // Eliminar todas las filas actuales
        model.setColumnCount(0);
        crear_mostrar_ventas(); // Volver a agregar las filas actualizadas
    }

    private void nuevoCodigoVenta(){
        conectar();
        try{
            ps = con.prepareStatement("SELECT MAX(COD_VENTA) FROM VENTAS");
            rs = ps.executeQuery();
            if(rs.next()){
                String ultimoCodigo = rs.getString(1);
                String nuevoCodigo = siguienteCodigo(ultimoCodigo);
                txtIdCodigoVenta.setText(nuevoCodigo);
                txtIdCodigoVenta.setEditable(false);
            }
        }catch (HeadlessException | SQLException f){
            //JOptionPane.showMessageDialog(null,f);
        }
    }
    public String siguienteCodigo(String ultimoCodigo) {
        String numero = ultimoCodigo.substring(1);
        int siguienteNumero = Integer.parseInt(numero) + 1;
        String nuevoNumero = String.format("%03d", siguienteNumero);
        return "V" + nuevoNumero;
    }
    public void conectar(){
        try{
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","Pelota2002");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
