import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private JPanel panelActual;
    private JPanel panelAnterior;
    String nombreUsuario="";

    public Ventana() {
        //PROPIEDADES VENTANA
        this.setTitle("Gestión de Usuarios");
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.RED);
        this.setVisible(true);

        cambiarPanel(acceder());

        this.repaint();
        this.revalidate();
    }

    private void cambiarPanel(JPanel nuevoPanel) {
        panelAnterior = panelActual;
        panelActual = nuevoPanel;

        limpiarVentana();
    }

    private void limpiarVentana() {
        if (panelAnterior != null) {
            this.remove(panelAnterior);
        }

        this.add(panelActual);

        this.repaint();
        this.revalidate();
    }

    //MENU
    public JMenuBar menu(){
        //BARRA
        JMenuBar menuBar = new JMenuBar();

        //MENUS
        JMenu cuenta = new JMenu("Cuenta");
        JMenu usuarios = new JMenu("Usuarios");
        JMenu ayuda = new JMenu("Ayuda");

        //ITEMS
        JMenuItem miCuenta = new JMenuItem("Mi Cuenta");
        JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesión");

        JMenuItem listaU = new JMenuItem("Lista de Usuarios");
        JMenuItem crearU = new JMenuItem("Crear Usuarios");

        JMenuItem comoCrear = new JMenuItem("¿Como Crear Usuarios?");

        //AGREGAR ITEM AL MENU
        cuenta.add(miCuenta);
        cuenta.addSeparator();
        cuenta.add(cerrarSesion);

        usuarios.add(listaU);
        usuarios.addSeparator();
        usuarios.add(crearU);

        ayuda.add(comoCrear);

        //AGREGAR MENUS A LA BARRA
        menuBar.add(cuenta);
        menuBar.add(usuarios);
        menuBar.add(ayuda);

        //MENU A LA VENTANA
        setJMenuBar(menuBar);

        //PROPIEDADES VENTANA
        setLocationRelativeTo(null);
        setVisible(true);

        miCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(modificarCuenta());
            }
        });

        cerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(acceder());
            }
        });

        listaU.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(listaUsuarios());
            }
        });

        crearU.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(crearUsuario());
            }
        });

        comoCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(comoCrearUsuario());
            }
        });

        return menuBar;
    }

    //ACCEDER CUENTA
    public JPanel acceder() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#7A984C"));

        //menu().removeAll();
        menu().setVisible(false);

        JTextField usuario = new JTextField();
        usuario.setSize(250,40);
        usuario.setLocation(120,100);
        panel.add(usuario);

        JPasswordField pass = new JPasswordField();
        pass.setSize(250,40);
        pass.setLocation(120,150);
        panel.add(pass);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setSize(250,40);
        cancelar.setLocation(30,205);
        panel.add(cancelar);

        cancelar.addActionListener(new ActionListener() {

            //BORRA EL TEXTO AL CANCELAR
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario.setText("");
                pass.setText("");

            }

        });

        JButton btnAccess = new JButton("Iniciar Sesión");
        btnAccess.setSize(250,40);
        btnAccess.setLocation(300,205);
        panel.add(btnAccess);

        btnAccess.addActionListener(new ActionListener() {

            //VALIDACIÓN DE DATOS
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = usuario.getText();
                nombreUsuario=nombre;
                String password = new String(pass.getPassword());

                if(nombre.equals(password)) {
                    JOptionPane.showMessageDialog(null, "BIENVENIDO "+nombre,"BIENVENIDO",JOptionPane.CLOSED_OPTION);
                    cambiarPanel(cuenta());

                }else {
                    JOptionPane.showMessageDialog(null, "EL USUARIO Y CONTRASEÑA NO COINCIDE");

                }
            }

        });

        return panel;
    }

    //CUENTA
    public JPanel cuenta() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#C45EDF"));

        JMenuBar menuBar = menu();
        this.setJMenuBar(menuBar);

        JLabel l1 = new JLabel("Bienvenido "+nombreUsuario);
        l1.setSize(250,40);
        l1.setLocation(120, 390);
        panel.add(l1);


        return panel;
    }

    //MODIFICAR CUENTA PERSONAL
    public JPanel modificarCuenta() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));

        JTextField tf1 = new JTextField();
        tf1.setSize(250,40);
        tf1.setLocation(120, 100);
        panel.add(tf1);

        JTextField tf2 = new JTextField();
        tf2.setSize(250,40);
        tf2.setLocation(120, 150);
        panel.add(tf2);

        JTextField tf3 = new JTextField();
        tf3.setSize(250,40);
        tf3.setLocation(120, 200);
        panel.add(tf3);

        JTextField tf4 = new JTextField();
        tf4.setSize(250,40);
        tf4.setLocation(120, 250);
        panel.add(tf4);

        JButton btn1 = new JButton("Cancelar");
        btn1.setSize(250,40);
        btn1.setLocation(50, 300);
        panel.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(cuenta());
            }

        });

        JButton btn2 = new JButton("Actualizar Datos");
        btn2.setSize(250,40);
        btn2.setLocation(300, 300);
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(1==1) {
                    JOptionPane.showMessageDialog(null, "Información actualizada");
                    cambiarPanel(cuenta());

                }else {
                    JOptionPane.showMessageDialog(null, "La información no se ha podido actualizar");

                }

                cambiarPanel(modificarCuenta());
            }

        });

        return panel;
    }

    //LISTA DE USUARIOS
    public JPanel listaUsuarios() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.red);

        JButton btnAdd = new JButton("6 ventana");
        btnAdd.setSize(250,40);
        btnAdd.setLocation(120, 390);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(borrarUsuarios());
            }

        });
        return panel;
    }
    //BORRAR USUARIOS
    public JPanel borrarUsuarios() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.PINK);

        JButton btnAdd = new JButton("borrar");
        btnAdd.setSize(250,40);
        btnAdd.setLocation(120, 390);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(crearUsuario());
            }

        });
        return panel;
    }

    //CREAR USUARIO
    public JPanel crearUsuario() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.BLUE);

        JButton btnAdd = new JButton("crear usuario");
        btnAdd.setSize(250,40);
        btnAdd.setLocation(120, 390);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(comoCrearUsuario());
            }

        });
        return panel;
    }

    //COMO CREAR USUARIO
    public JPanel comoCrearUsuario() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.orange);

        JButton btnAdd = new JButton("ayuda");
        btnAdd.setSize(250,40);
        btnAdd.setLocation(120, 390);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(crearUsuario());
            }

        });
        return panel;
    }
}
