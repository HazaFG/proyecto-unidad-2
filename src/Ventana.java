import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Ventana extends JFrame {
    private JPanel panelActual;
    private JPanel panelAnterior;
    String nombreUsuario = "";

    String nombreUsuarioDos = "";

    String usuarioSeleccionado = "";
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

        //PLACEHOLDER
        TextPrompt placeholder = new TextPrompt("Usuario", tf1);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);

        //LO METEMOS
        panel.add(tf1);

        JPasswordField pass = new JPasswordField();
        pass.setSize(267, 28);
        pass.setLocation(167, 384);

        //PLACEHOLDER
        TextPrompt placeholderPass = new TextPrompt("Password", pass);
        placeholderPass.changeAlpha(0.75f);
        placeholderPass.changeStyle(Font.ITALIC);

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
                    File file = new File("proyecto-unidad-2/users.txt");
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

        panel.revalidate();
        panel.repaint();
        return panel;
    }

    //CUENTA
    public JPanel cuenta() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#3E4532"));

        JMenuBar menuBar = menu();
        this.setJMenuBar(menuBar);

        JLabel bienvenida = new JLabel("Bienvenido " + nombreUsuario + "!!.");
        bienvenida.setSize(800, 40);
        bienvenida.setLocation(33, 72);
        bienvenida.setForeground(Color.decode("#D0E6A5"));
        bienvenida.setFont( new Font( "Inter",Font.PLAIN,27));
        panel.add(bienvenida);

        JLabel sientete = new JLabel("Sientete libre de interactuar con todo lo que ofrece nuestro sistema");
        sientete.setSize(800, 40);
        sientete.setLocation(33, 170);
        sientete.setForeground(Color.decode("#D0E6A5"));
        sientete.setFont( new Font( "Inter",Font.ITALIC,17));
        panel.add(sientete);

        JLabel deregistro = new JLabel("de registro de usuarios, puedes pasearte por todo lo que");
        deregistro.setSize(800, 40);
        deregistro.setLocation(33, 190);
        deregistro.setForeground(Color.decode("#D0E6A5"));
        deregistro.setFont( new Font( "Inter",Font.ITALIC,17));
        panel.add(deregistro);

        JLabel sistema = new JLabel("nuestro sistema ofrece para ti.");
        sistema.setSize(800, 40);
        sistema.setLocation(33, 210);
        sistema.setForeground(Color.decode("#D0E6A5"));
        sistema.setFont( new Font( "Inter",Font.ITALIC,17));
        panel.add(sistema);

        JLabel esperamos = new JLabel("Esperamos tu experiencia sea muy grata!");
        esperamos.setSize(800, 40);
        esperamos.setLocation(33, 270);
        esperamos.setForeground(Color.decode("#D0E6A5"));
        esperamos.setFont( new Font( "Inter",Font.ITALIC,17));
        panel.add(esperamos);

        JLabel powered = new JLabel("Powered by Andrea & Hazael");
        powered.setSize(800, 40);
        powered.setLocation(194, 480);
        powered.setForeground(Color.decode("#D0E6A5"));
        powered.setFont( new Font( "Inter",Font.PLAIN,15));
        panel.add(powered);

        //IMAGEN PRINCIPAL
        ImageIcon bienvenidaPlanta = new ImageIcon("proyecto-unidad-2/imagenes/bienvenida.png");

        // Crea el objeto JLabel
        JLabel etiquetaPlanta = new JLabel(bienvenidaPlanta);

        // Agrega la etiqueta de la planta al panel
        etiquetaPlanta.setSize(600, 600);
        etiquetaPlanta.setLocation(-10, -20);

        //IMAGEN DE LA PLANTA
        ImageIcon imagen = new ImageIcon("proyecto-unidad-2/imagenes/planta.png");
        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(170, 162);
        etiqueta.setLocation(200, 420);

        panel.add(etiquetaPlanta);
        //panel.add(etiqueta);

        panel.repaint();
        panel.revalidate();
        return panel;
    }

    //MODIFICAR CUENTA PERSONAL
    public JPanel modificarCuenta() {

        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.pink);

        JLabel bienvenida = new JLabel("Modificar " + nombreUsuario + "");
        bienvenida.setSize(800, 40);
        bienvenida.setLocation(200, 18);
        bienvenida.setForeground(Color.decode("#D0E6A5"));
        bienvenida.setFont( new Font( "Inter",Font.PLAIN,22));
        panel.add(bienvenida);

        JTextField tf1 = new JTextField();
        tf1.setSize(294, 22);
        tf1.setLocation(147, 99);
        panel.add(tf1);

        //PLACEHOLDER
        TextPrompt placeholder = new TextPrompt("Nombre Completo", tf1);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);

        JTextField tf2 = new JTextField();
        tf2.setSize(294, 22);
        tf2.setLocation(147, 166);
        panel.add(tf2);

        //PLACEHOLDER
        TextPrompt placeholder2 = new TextPrompt("Usuario", tf2);
        placeholder2.changeAlpha(0.75f);
        placeholder2.changeStyle(Font.ITALIC);

        JTextField tf3 = new JTextField();
        tf3.setSize(294, 22);
        tf3.setLocation(147, 238);
        panel.add(tf3);

        //PLACEHOLDER
        TextPrompt placeholder3 = new TextPrompt("Correo Electrónico", tf3);
        placeholder2.changeAlpha(0.75f);
        placeholder2.changeStyle(Font.ITALIC);

        JTextField tf4 = new JTextField();
        tf4.setSize(294, 22);
        tf4.setLocation(147, 316);
        panel.add(tf4);

        //PLACEHOLDER
        TextPrompt placeholder4 = new TextPrompt("Correo Electrónico", tf4);
        placeholder2.changeAlpha(0.75f);
        placeholder2.changeStyle(Font.ITALIC);

        JButton btn1 = new JButton("Cancelar");
        btn1.setSize(130, 22);
        btn1.setLocation(146, 442);
        panel.add(btn1);

        //Imagen de fondo
        ImageIcon imagen = new ImageIcon("proyecto-unidad-2/imagenes/editarUsuarios.png");

        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(600, 600);
        etiqueta.setLocation(-8, -40);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(panelAnterior);
            }

        });

        JButton btn2 = new JButton("Actualizar Datos");
        btn2.setSize(130, 22);
        btn2.setLocation(311, 442);
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    File file = new File("proyecto-unidad-2/users.txt");
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
                        nombreUsuario = tf2.getText();
                        cambiarPanel(cuenta());
                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar datos");

                    }



                } catch (IOException e2) {
                    e2.printStackTrace();
                }


                //cambiarPanel(modificarCuenta());
            }

        });

        panel.add(etiqueta);

        return panel;
    }


    //LISTA DE USUARIOS
    public JPanel listaUsuarios() {


        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#D0E6A5"));


        JLabel bienvenida = new JLabel("Lista de usuarios registrados");
        bienvenida.setSize(800, 40);
        bienvenida.setLocation(165, 80);
        bienvenida.setForeground(Color.decode("#3E4532"));
        bienvenida.setFont( new Font( "Inter",Font.PLAIN,20));
        panel.add(bienvenida);


        JLabel nombre = new JLabel("Editar");
        nombre.setSize(800, 40);
        nombre.setLocation(147, 160);
        nombre.setForeground(Color.decode("#3E4532"));
        nombre.setFont( new Font( "Inter",Font.PLAIN,17));
        panel.add(nombre);

        JLabel editar = new JLabel("Nombre");
        editar.setSize(800, 40);
        editar.setLocation(80, 280);
        editar.setForeground(Color.decode("#3E4532"));
        editar.setFont( new Font( "Inter",Font.PLAIN,17));
        panel.add(editar);

        JLabel usuario = new JLabel("Usuario");
        usuario.setSize(800, 40);
        usuario.setLocation(245, 280);
        usuario.setForeground(Color.decode("#3E4532"));
        usuario.setFont( new Font( "Inter",Font.PLAIN,17));
        panel.add(usuario);

        JLabel Accion = new JLabel("Acción");
        Accion.setSize(800, 40);
        Accion.setLocation(425, 280);
        Accion.setForeground(Color.decode("#3E4532"));
        Accion.setFont( new Font( "Inter",Font.PLAIN,17));
        panel.add(Accion);

        JLabel powered = new JLabel("Powered by Andrea & Hazael");
        powered.setSize(800, 40);
        powered.setLocation(205, 500);
        powered.setForeground(Color.decode("#3E4532"));
        powered.setFont( new Font( "Inter",Font.PLAIN,13));
        panel.add(powered);

        //Imagen de fondo
        ImageIcon imagen = new ImageIcon("proyecto-unidad-2/imagenes/editarUsuariio.png");
        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(600, 600);
        etiqueta.setLocation(-8, -40);



        //JCOMBOBOX USUARIO
        String[] fila;
        try {
            File file = new File("proyecto-unidad-2/users.txt");
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
        comboBox.setSize(294, 22);
        comboBox.setLocation(147, 192);

        usuarioSeleccionado = (String) comboBox.getSelectedItem();

        System.out.println("Combo" +usuarioSeleccionado);

        panel.add(comboBox);

        //BOTON EDITAR USUARIO SELECCIONADO
        JButton btn1 = new JButton("Editar a "+usuarioSeleccionado);
        btn1.setSize(294,32);
        btn1.setLocation(147, 235);
        panel.add(btn1);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(editarUsuario());
            }
        });

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn1.setEnabled(true);

                String usuarioSeleccionado = (String) comboBox.getSelectedItem();
                btn1.setText("Editar a "+usuarioSeleccionado);

                nombreUsuarioDos = usuarioSeleccionado;
            }
        });
        //IMAGEN DE FONDO


        //TABLA ------------------------------------------------------------------------------------------------------------------------------------------------

        /*
        setPreferredSize(new Dimension(200, 200));

        // Crear modelo de la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Accion");

        // Leer datos del archivo y añadirlos al modelo de la tabla
        try {
            BufferedReader br = new BufferedReader(new FileReader("proyecto-unidad-2/users.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                modeloTabla.addRow(datos);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear la tabla
        JTable tabla = new JTable(modeloTabla);

        // Ajustar tamaño de las columnas
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Añadir la tabla a un JScrollPane para poder hacer scroll si es necesario
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(38, 285, 509, 192);
        add(scrollPane);

        // ----------------------------------------------------------------------------------------------------------------------------

        // Crear botón para eliminar fila seleccionada de la tabla
        JButton btnEliminarFila = new JButton("Eliminar Fila");
        btnEliminarFila.setBounds(50, 50, 120, 30);
        add(btnEliminarFila);

        // Añadir acción al botón para eliminar la fila seleccionada de la tabla
        btnEliminarFila.addActionListener(e -> {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada >= 0) {
                modelo.removeRow(filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una fila para eliminar.");
            }
        });
        */

        // Creamos el modelo de la tabla

        //Creamos el modelo de la tabla



        final JTable tabla;
        DefaultTableModel modelo;

        modelo = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? JButton.class : Object.class;
            }
        };

        // Creamos la tabla con el modelo de datos
        tabla = new JTable(modelo);
        tabla.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(38, 285, 509, 192);

        Container container = new Container();
        container.add(scrollPane);

        // Creamos la tabla con el modelo de datos
        //tabla = new JTable(modelo);

        // Agregamos las columnas al modelo de la tabla
        modelo.addColumn("Nombre Completo");
        modelo.addColumn("Usuario");
        modelo.addColumn("Eliminar");



        // Cargamos los datos desde el archivo de texto
        try {
            FileReader archivo = new FileReader("proyecto-unidad-2/users.txt");
            BufferedReader br = new BufferedReader(archivo);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Object[] fila1 = {datos[0], datos[1], new JButton("ELIMINAR").getText()};
                modelo.addRow(fila1);
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }

        // Ajustar tamaño de las columnas
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);

        // Agregamos un listener al botón de eliminar
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columna = tabla.columnAtPoint(e.getPoint());
                int fila = tabla.rowAtPoint(e.getPoint());

                if (columna == 2) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la fila?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        // Obtener usuario de la fila seleccionada
                        String usuario = (String) modelo.getValueAt(fila, 1);

                        // Eliminar fila de la tabla
                        modelo.removeRow(fila);

                        // Eliminar usuario del archivo de texto
                        try {
                            FileReader archivo = new FileReader("proyecto-unidad-2/users.txt");
                            BufferedReader br = new BufferedReader(archivo);
                            String linea;
                            StringBuilder contenido = new StringBuilder();
                            while ((linea = br.readLine()) != null) {
                                if (linea.contains(usuario)) {
                                    continue; // Saltar la línea del usuario que se eliminó
                                }
                                contenido.append(linea).append("\n");
                            }
                            br.close();

                            FileWriter archivoNuevo = new FileWriter("proyecto-unidad-2/users.txt");
                            archivoNuevo.write(contenido.toString());
                            archivoNuevo.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error al leer o escribir el archivo");
                        }
                    }
                }
            }
        });


        tabla.setBounds(38, 315, 509, 192);
        panel.add(tabla);



        // Añadir la tabla a un JScrollPane para poder hacer scroll si es necesario
        //add(scrollPane);


        //FIN TABLA --------------------------------------------------------------------------------------------------------------------------------------------

        panel.repaint();
        panel.revalidate();
        return panel;
    }

    /*
    panelDePestañas.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            // Obtener el panel actual
            JPanel panelActual = (JPanel) panelDePestañas.getSelectedComponent();

            // Remover la tabla del panel actual
            panelActual.removeAll();
        }
    });
*/

    //EDITAR USUARIO
    public JPanel editarUsuario() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));

        listaUsuarios().setVisible(false);

        JTextField tf1 = new JTextField();
        tf1.setSize(294, 22);
        tf1.setLocation(147, 99);
        panel.add(tf1);

        //PLACEHOLDER
        TextPrompt placeholder = new TextPrompt("Nombre Completo", tf1);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);

        JTextField tf2 = new JTextField();
        tf2.setSize(294, 22);
        tf2.setLocation(147, 166);
        panel.add(tf2);

        //PLACEHOLDER
        TextPrompt placeholder2 = new TextPrompt("Usuario", tf2);
        placeholder2.changeAlpha(0.75f);
        placeholder2.changeStyle(Font.ITALIC);

        JTextField tf3 = new JTextField();
        tf3.setSize(294, 22);
        tf3.setLocation(147, 238);
        panel.add(tf3);

        //PLACEHOLDER
        TextPrompt placeholder3 = new TextPrompt("Correo Electrónico", tf3);
        placeholder2.changeAlpha(0.75f);
        placeholder2.changeStyle(Font.ITALIC);

        JTextField tf4 = new JTextField();
        tf4.setSize(294, 22);
        tf4.setLocation(147, 316);
        panel.add(tf4);

        //PLACEHOLDER
        TextPrompt placeholder4 = new TextPrompt("Correo Electrónico", tf4);
        placeholder2.changeAlpha(0.75f);
        placeholder2.changeStyle(Font.ITALIC);

        /*
        JTextField tf5 = new JTextField();
        tf5.setSize(294, 22);
        tf5.setLocation(155, 431);
        panel.add(tf5);
         */

        JButton btn1 = new JButton("Cancelar");
        btn1.setSize(130, 22);
        btn1.setLocation(146, 442);
        panel.add(btn1);

        //Imagen de fondo
        ImageIcon imagen = new ImageIcon("proyecto-unidad-2/imagenes/editarUsuarios.png");

        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(600, 600);
        etiqueta.setLocation(-8, -40);

        JLabel bienvenida = new JLabel("Editar a " + nombreUsuarioDos);
        System.out.println("editar: "+ nombreUsuarioDos);
        bienvenida.setSize(800, 40);
        bienvenida.setLocation(220, 24);
        bienvenida.setForeground(Color.decode("#D0E6A5"));
        bienvenida.setFont( new Font( "Inter",Font.PLAIN,20));
        panel.add(bienvenida);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(listaUsuarios());
            }

        });

        JButton btn2 = new JButton("Actualizar Datos");
        btn2.setSize(130, 22);
        btn2.setLocation(311, 442);
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    File file = new File("proyecto-unidad-2/users.txt");
                    BufferedReader lector = new BufferedReader(new FileReader(file));

                    String linea;
                    boolean usuarioEncontrado = false;

                    StringBuilder textoDinamico = new StringBuilder();

                    System.out.println(nombreUsuarioDos);

                    while ((linea = lector.readLine()) != null) {
                        String[] campo = linea.split(",");

                        if(campo[0].equals(nombreUsuarioDos)) {
                            usuarioEncontrado = true;

                            System.out.println(nombreUsuarioDos);

                            String lineaEditada = tf1.getText() + "," + tf2.getText() + "," + tf3.getText() + "," + tf4.getText();
                            textoDinamico.append(lineaEditada).append(System.lineSeparator());
                        } else {
                            textoDinamico.append(linea).append(System.lineSeparator());
                        }


                    }


                    lector.close();

                    if(usuarioEncontrado) {
                        // Sobrescribe el archivo con los datos modificados
                        System.out.println("Hola");
                        BufferedWriter escritor = new BufferedWriter(new FileWriter(file));
                        escritor.write(textoDinamico.toString());
                        escritor.close();

                        JOptionPane.showMessageDialog(null, "Datos modificados");
                        cambiarPanel(panelAnterior);
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario no se encontró en el archivo.");
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }



            	/*
                JOptionPane.showMessageDialog(null, "Información actualizada");
                cambiarPanel(listaUsuarios());


                JOptionPane.showMessageDialog(null, "La información no se ha podido actualizar");
                cambiarPanel(panelActual);
*/

            }

        });

        panel.add(etiqueta);
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
        panel.setLocation(-8, -40);
        panel.setLayout(null);
        panel.setBackground(Color.BLUE);

        //Imagen de fondo
        ImageIcon imagen = new ImageIcon("proyecto-unidad-2/imagenes/crearUsuarios.png");

        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(600, 600);
        etiqueta.setLocation(0, 0);

        JTextField tf1 = new JTextField();
        tf1.setSize(294, 22);
        tf1.setLocation(155, 139);
        panel.add(tf1);

        //PLACEHOLDER
        TextPrompt placeholder = new TextPrompt("Nombre Completo", tf1);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);

        JTextField tf2 = new JTextField();
        tf2.setSize(294, 22);
        tf2.setLocation(155, 206);
        panel.add(tf2);

        //PLACEHOLDER
        TextPrompt placeholder2 = new TextPrompt("Usuario", tf2);
        placeholder2.changeAlpha(0.75f);
        placeholder2.changeStyle(Font.ITALIC);

        JTextField tf3 = new JTextField();
        tf3.setSize(294, 22);
        tf3.setLocation(155, 278);
        panel.add(tf3);

        //PLACEHOLDER
        TextPrompt placeholder3 = new TextPrompt("Correo Electrónico", tf3);
        placeholder3.changeAlpha(0.75f);
        placeholder3.changeStyle(Font.ITALIC);

        JTextField tf4 = new JTextField();
        tf4.setSize(294, 22);
        tf4.setLocation(155, 356);
        panel.add(tf4);

        //PLACEHOLDER
        TextPrompt placeholder4 = new TextPrompt("Password", tf4);
        placeholder4.changeAlpha(0.75f);
        placeholder4.changeStyle(Font.ITALIC);

        JTextField tf5 = new JTextField();
        tf5.setSize(294, 22);
        tf5.setLocation(155, 431);
        panel.add(tf5);

        //PLACEHOLDER
        TextPrompt placeholder5 = new TextPrompt("Confirmar Password", tf5);
        placeholder5.changeAlpha(0.75f);
        placeholder5.changeStyle(Font.ITALIC);

        JButton btn1 = new JButton("Cancelar");
        btn1.setSize(130, 22);
        btn1.setLocation(154, 482);
        panel.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(panelAnterior);

            }

        });

        JButton btn2 = new JButton("Crear usuario");
        btn2.setSize(130, 22);
        btn2.setLocation(319, 482);
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(panelAnterior);

                String contra = new String(tf4.getText());
                String contraConf = new String(tf5.getText());

                try {

                    File file = new File("proyecto-unidad-2/users.txt");
                    BufferedReader lector = new BufferedReader(new FileReader(file));

                    String linea;
                    String  usuarioExistente = tf2.getText();
                    boolean usuarioEncontrado = false;

                    while ((linea = lector.readLine()) != null) {
                        String[] campo = linea.split(",");

                        if(contra.equals(contraConf)) {
                            JOptionPane.showMessageDialog(null, "Verificando...");

                            //VERIFICA SI EL USUARIO EXISTE
                            if (campo[1].equals(usuarioExistente)) {
                                JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe, Intentelo de nuevo!", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                            } else {
                                BufferedWriter escritor = new BufferedWriter(new FileWriter(file, true));
                                escritor.write(tf1.getText() + "," + tf2.getText() + "," + tf3.getText() + "," + tf4.getText());
                                escritor.newLine();
                                escritor.close();

                                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente!");
                                cambiarPanel(acceder());
                                break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden, intentelo de nuevo!", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                        lector.close();

                        /*
                        //VERIFICA SI EL USUARIO EXISTE
                        if (campo[1].equals(usuarioExistente)) {
                            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe");
                            break;
                        } else {
                            BufferedWriter escritor = new BufferedWriter(new FileWriter(file, true));
                            escritor.write(tf1.getText() + "," + tf2.getText() + "," + tf3.getText() + "," + tf4.getText());
                            escritor.newLine();
                            escritor.close();

                            JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
                            cambiarPanel(acceder());
                            break;
                        }
                         */

                    }
                    lector.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

            }

        });

        panel.add(etiqueta);

        return panel;
    }

    

    //COMO CREAR USUARIO
    public JPanel comoCrearUsuario() {
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#D0E6A5"));


        //Imagen de fondo
        ImageIcon imagen = new ImageIcon("proyecto-unidad-2/imagenes/crearUsuario.png");
        // Crea el objeto JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agrega la etiqueta al panel
        etiqueta.setSize(600, 600);
        etiqueta.setLocation(-8, -40);

        JButton btnAdd = new JButton("Crear un usuario ahora");
        btnAdd.setSize(294,32);
        btnAdd.setLocation(146, 437);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel(crearUsuario());
            }

        });

        panel.add(etiqueta);

        panel.revalidate();
        panel.repaint();
        return panel;
    }
}
