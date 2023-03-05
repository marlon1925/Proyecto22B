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
                frame3.setSize(400, 200);
                frame3.setLocationRelativeTo(null);
                frame3.setVisible(true);
                dispose();
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
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MINIMARKET","root","12345");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
