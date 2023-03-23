import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public Ventana(){
        this.setVisible(true);
        this.setSize(600,600);
        this.setTitle("HOLA");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.RED);
        //this.setLayout(null);

        JPanel fondo = new JPanel();
        fondo.setOpaque(true);
        fondo.setBackground(Color.RED);
        fondo.setLayout(null);
        this.add(fondo);

        JButton boton = new JButton("Hola");
        boton.setSize(80,80);
        boton.setOpaque(true);
        boton.setLocation(50,50);
        boton.setBackground(Color.BLUE);
        fondo.add(boton);

        //HOLA
    }
}
