import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public Ventana(){
        this.setVisible(true);
        this.setSize(600,600);
        this.setTitle("a");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.RED);

        JPanel fondo = new JPanel();
        fondo.setOpaque(true);
        this.setBackground(Color.RED);
    }
}
