import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class agregarProductos extends inicioAdmin {
    Connection con;
    public JPanel jp_produtos;
    private JTextField codigoProdTxt;
    private JTextField nombreProdTxt;
    private JTextField precioProdTxt;
    private JTextField stockProdTxt;
    private JButton guardarButton;
    public JTable tablaProductos;
    private JButton regresarBotton;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JTextField txtBuscar;
    private JButton limpiarButton;


    public agregarProductos(){

        crear_mostrar_productos();
        nuevoCodigoProd();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                try{
                    ps = con.prepareStatement("INSERT INTO PRODUCTOS (COD_PROD,NOM_PROD,PRECIO, STOCK)" + "VALUES(?,?,?,?)");

                    ps.setString(1,codigoProdTxt.getText());
                    ps.setString(2,nombreProdTxt.getText());
                    ps.setString(3, precioProdTxt.getText());
                    ps.setString(4, stockProdTxt.getText());
                    int res = ps.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(null,"Se creo");
                        codigoProdTxt.setText("");
                        nombreProdTxt.setText("");
                        precioProdTxt.setText("");
                        stockProdTxt.setText("");
                        actualizarTablaProductos(); // Actualizar tabla después de insertar un nuevo producto
                        nuevoCodigoProd();
                    }else {
                        JOptionPane.showMessageDialog(null, "No se creo");
                    }
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });

        regresarBotton.addActionListener(new ActionListener() {
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

        eliminarButton.setEnabled(false);
        actualizarButton.setEnabled(false);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarButton.setEnabled(true);
                eliminarButton.setEnabled(true);
                conectar();

                try {
                    ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE COD_PROD = ?");
                    ps.setString(1, txtBuscar.getText());

                    rs = ps.executeQuery();

                    if (rs.next()) {
                        nombreProdTxt.setText(rs.getString("NOM_PROD"));
                        precioProdTxt.setText(rs.getString("PRECIO"));
                        stockProdTxt.setText(rs.getString("STOCK"));
                    } else {
                        JOptionPane.showMessageDialog(null, "El código " + txtBuscar.getText() + " no existe.");
                    }

                } catch (SQLException f) {
                    System.out.println(f);
                }
            }
        });


        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreProdTxt.setText("");
                precioProdTxt.setText("");
                stockProdTxt.setText("");
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();

                try {
                    ps = con.prepareStatement("UPDATE PRODUCTOS SET NOM_PROD = ?, PRECIO = ?, STOCK = ? WHERE COD_PROD = ?");
                    ps.setString(1, nombreProdTxt.getText());
                    ps.setDouble(2, Double.parseDouble(precioProdTxt.getText()));
                    ps.setInt(3, Integer.parseInt(stockProdTxt.getText()));
                    ps.setString(4, txtBuscar.getText());

                    int rest = ps.executeUpdate();

                    if (rest > 0) {
                        JOptionPane.showMessageDialog(null, "ACTUALIZADO");
                        actualizarTablaProductos();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR");
                    }
                } catch (HeadlessException | SQLException w) {
                    System.out.println(w);
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();

                try {
                    ps = con.prepareStatement("DELETE FROM PRODUCTOS WHERE COD_PROD = ?");
                    ps.setString(1, txtBuscar.getText());

                    int rest = ps.executeUpdate();

                    if (rest > 0) {
                        JOptionPane.showMessageDialog(null, "BORRADO CON EXITO");
                        actualizarTablaProductos();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
                    }
                } catch (HeadlessException | SQLException f) {
                    System.out.println(f);
                }
            }
        });



    }

    // Método para actualizar la tabla de productos
    private void actualizarTablaProductos() {
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setRowCount(0); // Eliminar todas las filas actuales
        model.setColumnCount(0);
        crear_mostrar_productos(); // Volver a agregar las filas actualizadas
    }

    private void nuevoCodigoProd(){
        conectar();
        try{
            ps = con.prepareStatement("SELECT MAX(COD_PROD) FROM PRODUCTOS");
            rs = ps.executeQuery();
            if(rs.next()){
                String ultimoCodigo = rs.getString(1);
                String nuevoCodigo = siguienteCodigo(ultimoCodigo);
                codigoProdTxt.setText(nuevoCodigo);
                codigoProdTxt.setEditable(false);
            }
        }catch (HeadlessException | SQLException f){
            JOptionPane.showMessageDialog(null,f);
        }
    }

    public void crear_mostrar_productos() {
        conectar();
        try {

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


        } catch (HeadlessException | SQLException f) {
            System.out.println(f);
        }
    }
    public String siguienteCodigo(String ultimoCodigo) {
        String numero = ultimoCodigo.substring(1);
        int siguienteNumero = Integer.parseInt(numero) + 1;
        String nuevoNumero = String.format("%03d", siguienteNumero);
        return "P" + nuevoNumero;
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
