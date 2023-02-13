import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends  JFrame {
    private JButton Admin;
    private JButton cajeroButton;
    private JLabel Buho;
    private JPanel panel;

    public Inicio(){
        super("LOGIN");
        setContentPane(panel);
        Admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Inicio();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(640, 550);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}


