import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class cajeroInicio extends Inicio {

        public JPanel cajeroJ;
        private JTextField txtCedulaCajero;
        private JTextField txtContraseñaCajero;
        private JButton regresarButton;
        private JButton iniciarButton;
        private JLabel LOGINCAJERO;



    public cajeroInicio(){



        //FUNCIONALIDAD DEL BOTON DE REGRESAR
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Inicio();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(740, 550);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
