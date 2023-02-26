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

    public admin (){
        /*
        botonInicioAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try{
                  con = getConection();
                  st = con.prepareStatement("SELECT * FROM minimarket.administrador where CEDULA=" +
                          cedulaAdmin.getText() + "AND CONTRASEÑA=" + contraseñaAdmin);
                  while (rs.next()){
                      System.out.println("si vale");
                  }
              }catch (HeadlessException | SQLException f){
                  System.out.println(f);
              }
            }
        });*/
    }
}
