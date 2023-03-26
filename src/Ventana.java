import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private JPanel panelActual;
    private JPanel panelAnterior;
    String nombreUsuario = "";
    String passUsuario = "";
    private JComboBox<String> comboBox;
    private ArrayList<String> data = new ArrayList<>();


    public Ventana() {
        //File file = new File("users.txt");

        //PROPIEDADES VENTANA
        this.setTitle("Gestión de Usuarios");
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
    public JMenuBar menu() {
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
        panel.setBackground(Color.decode("#D0E6A5"));

        //menu().removeAll();
        menu().setVisible(false);

        /*
        JLabel correo = new JLabel("Correo Electronico");
        correo.setSize(100,100);
        correo.setLocation(50,50);
        panel.add(correo);
        */

        //Imagen de fondo
        ImageIcon imagen = new ImageIcon("proyecto-unidad-2/imagenes/loginIcon.png");
        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(600, 600);
        etiqueta.setLocation(0, 0);

        JTextField tf1 = new JTextField();
        tf1.setSize(267, 28);
        tf1.setLocation(167, 307);
        panel.add(tf1);

        JPasswordField pass = new JPasswordField();
        pass.setSize(267, 28);
        pass.setLocation(167, 384);
        panel.add(pass);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setSize(114, 31);
        cancelar.setLocation(167, 452);
        panel.add(cancelar);

        panel.add(etiqueta);

        cancelar.addActionListener(new ActionListener() {

            //BORRA EL TEXTO AL CANCELAR
            @Override
            public void actionPerformed(ActionEvent e) {
                tf1.setText("");
                pass.setText("");

            }

        });

        JButton btnAccess = new JButton("Iniciar Sesión");
        btnAccess.setSize(114, 31);
        btnAccess.setLocation(320, 452);
        panel.add(btnAccess);

        btnAccess.addActionListener(new ActionListener() {

            //VALIDACIÓN DE DATOS
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("users.txt");
                    BufferedReader lector = new BufferedReader(new FileReader(file));

                    String linea;
                    boolean usuarioEncontrado = false;

                    String usuario = tf1.getText();
                    String password = new String(pass.getPassword());

                    while ((linea = lector.readLine()) != null) {
                        String[] campo = linea.split(",");

                        if (campo[1].equals(usuario) && campo[3].equals(password)) {
                            usuarioEncontrado = true;

                        }
                    }

                    lector.close();

                    if (usuarioEncontrado) {
                        nombreUsuario = tf1.getText();
                        passUsuario = new String(pass.getPassword());

                        JOptionPane.showMessageDialog(null, "Bienvenido "+nombreUsuario);
                        cambiarPanel(cuenta());
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario y contraseña no coincide");
                    }

                } catch (IOException e2) {
                    e2.printStackTrace();
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

        JLabel l1 = new JLabel("Bienvenido " + nombreUsuario);
        l1.setSize(250, 40);
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
        panel.setBackground(Color.pink);

        JTextField tf1 = new JTextField();
        tf1.setSize(250, 40);
        tf1.setLocation(120, 100);
        panel.add(tf1);

        JTextField tf2 = new JTextField();
        tf2.setSize(250, 40);
        tf2.setLocation(120, 150);
        panel.add(tf2);

        JTextField tf3 = new JTextField();
        tf3.setSize(250, 40);
        tf3.setLocation(120, 200);
        panel.add(tf3);

        JTextField tf4 = new JTextField();
        tf4.setSize(250, 40);
        tf4.setLocation(120, 250);
        panel.add(tf4);

        JButton btn1 = new JButton("Cancelar");
        btn1.setSize(250, 40);
        btn1.setLocation(50, 300);
        panel.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(panelAnterior);
            }

        });

        JButton btn2 = new JButton("Actualizar Datos");
        btn2.setSize(250, 40);
        btn2.setLocation(300, 300);
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    File file = new File("users.txt");
                    BufferedReader lector = new BufferedReader(new FileReader(file));

                    String linea;
                    boolean usuarioEncontrado = false;

                    StringBuilder textoDinamico = new StringBuilder();

                    while ((linea = lector.readLine()) != null) {
                        String[] campo = linea.split(",");

                        //SE UBICA EN LA FILA CON LOS DATOS INGRESADOS DESDE EL INICIO DE SESIÓN
                        if(campo[1].equals(nombreUsuario) && campo[3].equals(passUsuario)) {
                            usuarioEncontrado = true;

                            String lineaEditada = tf1.getText() + "," + tf2.getText() + "," + tf3.getText() + "," + tf4.getText();
                            textoDinamico.append(lineaEditada).append(System.lineSeparator());
                            }else{
                            textoDinamico.append(linea).append(System.lineSeparator());
                        }
                    }

                    lector.close();

                    if(usuarioEncontrado) {
                        //SOBREESCRIBE EL ARCHIVO
                        BufferedWriter escritor = new BufferedWriter(new FileWriter(file));
                        escritor.write(textoDinamico.toString());

                        escritor.close();

                        JOptionPane.showMessageDialog(null, "Datos modificados");
                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar datos");
                    }

                } catch (IOException e2) {
                    e2.printStackTrace();
                }


                //cambiarPanel(modificarCuenta());
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

        //JCOMBOBOX USUARIO
        String[] fila;
        try {
            File file = new File("users.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                //VERIFICA QUE NO SE DUPLIQUEN DATOS EN LA LISTA ANTES DE AGREGARLOS
                if(!data.contains(parts[0])) {
                    data.add(parts[0]);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        comboBox = new JComboBox<>(data.toArray(new String[0]));
        comboBox.setSize(300, 25);
        comboBox.setLocation(50, 50);

        String usuarioSeleccionado = (String) comboBox.getSelectedItem();

        panel.add(comboBox);

        //BOTON EDITAR USUARIO SELECCIONADO
        JButton btn1 = new JButton("Editar a "+usuarioSeleccionado);
        btn1.setSize(250,40);
        btn1.setLocation(50, 100);
        panel.add(btn1);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(editarUsuario());
            }
        });

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedData = (String) comboBox.getSelectedItem();
                btn1.setText("Editar a "+selectedData);
            }
        });

        return panel;
    }


    //EDITAR USUARIO
    public JPanel editarUsuario() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));

        JTextField tf1 = new JTextField();
        tf1.setSize(250, 40);
        tf1.setLocation(120, 100);
        panel.add(tf1);

        JTextField tf2 = new JTextField();
        tf2.setSize(250, 40);
        tf2.setLocation(120, 150);
        panel.add(tf2);

        JTextField tf3 = new JTextField();
        tf3.setSize(250, 40);
        tf3.setLocation(120, 200);
        panel.add(tf3);

        JTextField tf4 = new JTextField();
        tf4.setSize(250, 40);
        tf4.setLocation(120, 250);
        panel.add(tf4);

        JButton btn1 = new JButton("Cancelar");
        btn1.setSize(250, 40);
        btn1.setLocation(50, 300);
        panel.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(listaUsuarios());
            }

        });

        JButton btn2 = new JButton("Actualizar Datos");
        btn2.setSize(250, 40);
        btn2.setLocation(300, 300);
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (1 == 0) {
                    JOptionPane.showMessageDialog(null, "Información actualizada");
                    cambiarPanel(listaUsuarios());

                } else {
                    JOptionPane.showMessageDialog(null, "La información no se ha podido actualizar");
                    cambiarPanel(panelActual);
                }


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

        JTextField tf1 = new JTextField();
        tf1.setSize(250, 40);
        tf1.setLocation(120, 100);
        panel.add(tf1);

        JTextField tf2 = new JTextField();
        tf2.setSize(250, 40);
        tf2.setLocation(120, 150);
        panel.add(tf2);

        JTextField tf3 = new JTextField();
        tf3.setSize(250, 40);
        tf3.setLocation(120, 200);
        panel.add(tf3);

        JTextField tf4 = new JTextField();
        tf4.setSize(250, 40);
        tf4.setLocation(120, 250);
        panel.add(tf4);

        JTextField tf5 = new JTextField();
        tf5.setSize(250, 40);
        tf5.setLocation(120, 300);
        panel.add(tf5);

        JButton btn1 = new JButton("Cancelar");
        btn1.setSize(250, 40);
        btn1.setLocation(50, 350);
        panel.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(panelAnterior);
            }

        });

        JButton btn2 = new JButton("crear usuario");
        btn2.setSize(250, 40);
        btn2.setLocation(300, 350);
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

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
        panel.setBackground(Color.decode("#D0E6A5"));

        JButton btnAdd = new JButton("Crear un usuario ahora");
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
