import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin extends Inicio{
    public JPanel admin;
    private JTextField cedulaAdmin;
    private JPasswordField contraseñaAdmin;
    private JButton botonInicioAdmin;
    private JButton regresarButton;

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
