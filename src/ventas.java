import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ventas extends cajeroInicio{
    public JPanel Jventas ;
    private JTable table1;
    private JTextField txtIdProductos;
    private JTextField txtPrecioProducto;
    private JButton agregarbutton;
    private JButton buscarbotton;
    private JTextField txtNombreProducto;
    private JTextField txtCantidadProducto;

    public ventas(){
        con = getConection();
      try{
          st = con.createStatement();
          rs = st.executeQuery("SELECT * FROM PRODUCTOS");

          // Get metadata object
          ResultSetMetaData rsmd = rs.getMetaData();
          int columnCount = rsmd.getColumnCount();

          // Create JTable and set model
          DefaultTableModel model = (DefaultTableModel) table1.getModel();

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

      buscarbotton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

              con = getConection();

              try{

                  st = con.createStatement();
                  rs = st.executeQuery("SELECT * FROM PRODUCTOS WHERE COD_PROD = "+txtIdProductos.getText());

                  while (rs.next()){
                      txtPrecioProducto.setText(rs.getString("PRECIO"));
                      txtNombreProducto.setText(rs.getString("NOM_PROD"));
                  }

              }catch (Exception w){
                  System.out.println(w);
              }
          }
      });


  }



}
