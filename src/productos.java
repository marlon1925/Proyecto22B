import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class productos extends inicioAdmin {
    public JPanel jp_produtos;

    public productos(){
        con = getConection();
        try{
            String query = "SELECT * FROM PRODUCTOS";
            st = con.createStatement();
            rs = st.executeQuery(query);

            // Get metadata object
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create JTable and set model
            JTable table = new JTable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

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
        }catch(HeadlessException | SQLException f){
            System.out.println(f);
        }

    }
}

