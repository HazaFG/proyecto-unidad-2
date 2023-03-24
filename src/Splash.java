import javax.swing.*;
import java.awt.*;

public class Splash extends JDialog {

    private JLabel porcentaje;
    private JProgressBar barra;
    private JLabel l2;

    public Splash() {
        inicio();
        setSize(600,600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setUndecorated(true);

        inicioHilo();
    }

    private void inicio() {
        JLabel l1 = new JLabel("Splash Screen");
        l1.setFont(new Font("Tahoma", Font.PLAIN,18));
        l1.setBounds(49,11,147,32);
        getContentPane().add(l1);

        barra = new JProgressBar();
        barra.setBounds(26,54,229,32);
        getContentPane().add(barra);

        porcentaje = new JLabel("0%");
        porcentaje.setFont(new Font("Tahoma", Font.PLAIN,18));
        porcentaje.setBounds(206,23,46,14);
        getContentPane().add(porcentaje);

        l2 = new JLabel();
        l2.setFont(new Font("Tahoma", Font.PLAIN,18));
        l2.setBounds(50,50,100,100);
        getContentPane().add(l2);
    }

    private void inicioHilo(){
        Thread hilo = new Thread(new Runnable(){

            int x = 0;
            String texto = " ";

            public void run(){
                try{
                    while(x<=100){
                        barra.setValue(x);
                        porcentaje.setText(x+"%");
                        x++;
                        Thread.sleep(40);

                        if(x==5){
                            texto="Le recomendamos tomar matcha";
                            l2.setText(texto);
                        } else if (x==25){
                            texto="Cargando...";
                            l2.setText(texto);
                        } else if (x==50){
                            texto="Alt + F4";
                            l2.setText(texto);
                        } else if (x==75){
                            texto="jijijija";
                            l2.setText(texto);
                        } else if (x==95){
                            texto="Iniciando...";
                            l2.setText(texto);
                        }
                    }

                    dispose();
                    Ventana v1 = new Ventana();
                    v1.setVisible(true);

                } catch (Exception e){
                    System.out.println("Excepcion: "+e.getMessage());
                }

            }
        });
        hilo.start();
    }
}
