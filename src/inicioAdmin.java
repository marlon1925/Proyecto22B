import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class inicioAdmin extends admin{
    private JButton agregarP;
    private JButton button2;
    private JButton button3;
    public JPanel PinicioAdmin;
    private JLabel bienvenidoAdmin;

    public inicioAdmin(){
        agregarP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productos frame4 = new productos();
                frame4.setContentPane(frame4.jp_produtos);
                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame4.pack();
                frame4.setSize(400, 200);
                frame4.setLocationRelativeTo(null);
                frame4.setVisible(true);
                dispose();
            }
        });
    }

}
