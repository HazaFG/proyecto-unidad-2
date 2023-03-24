import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private JPanel panelActual;
    private JPanel panelAnterior;

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
                cambiarPanel(cerrarSesion());
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

        JButton btnAccess = new JButton("A C C E D E R");
        btnAccess.setSize(250,40);
        btnAccess.setLocation(120,205);
        panel.add(btnAccess);

        btnAccess.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(cuenta());
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

        JButton btnAdd = new JButton("tercera ventana");
        btnAdd.setSize(250,40);
        btnAdd.setLocation(120, 390);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(modificarCuenta());
            }

        });

        return panel;
    }

    //MODIFICAR CUENTA PERSONAL
    public JPanel modificarCuenta() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));

        JButton btnAdd = new JButton("cuarta ventana");
        btnAdd.setSize(250,40);
        btnAdd.setLocation(120, 390);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(cerrarSesion());
            }

        });

        return panel;
    }

    //CERRAR SESIÓN
    public JPanel cerrarSesion() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.green);

        JButton btnAdd = new JButton("quinta ventana");
        btnAdd.setSize(250,40);
        btnAdd.setLocation(120, 390);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(listaUsuarios());
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
