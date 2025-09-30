package semana16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CapturaTeclaC extends JFrame {

    private JList<String> listaNotas;
    private DefaultListModel<String> modeloNotas;
    private JButton botonEjemplo;

    public CapturaTeclaC() {
        super("Ejemplo: Detectar tecla 'C'");

        // Configuramos la ventana básica
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos el modelo de la lista y agregamos algunos elementos
        modeloNotas = new DefaultListModel<>();
        modeloNotas.addElement("Hacer compras");
        modeloNotas.addElement("Estudiar Java");
        modeloNotas.addElement("Salir a caminar");
        modeloNotas.addElement("Llamar a un amigo");

        // Creamos la lista con el modelo
        listaNotas = new JList<>(modeloNotas);
        listaNotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaNotas.setFocusable(true); // Muy importante para detectar teclas

        // Creamos un botón cualquiera
        botonEjemplo = new JButton("Botón de ejemplo");

        // Panel principal con borde y espacio
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(listaNotas), BorderLayout.CENTER);
        panel.add(botonEjemplo, BorderLayout.SOUTH);

        // Listener de teclado: detecta cuando presionamos la tecla 'C'
        listaNotas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    // Obtenemos el índice del elemento seleccionado
                    int seleccionado = listaNotas.getSelectedIndex();

                    if (seleccionado != -1) {
                        String nota = modeloNotas.getElementAt(seleccionado);

                        // Cambiamos el estado de la nota (marcado o sin marcar)
                        if (nota.startsWith("✅ ")) {
                            nota = nota.substring(2); // Quitamos el check
                            System.out.println("Tarea desmarcada: " + nota);
                        } else {
                            nota = "✅ " + nota; // Agregamos check
                            System.out.println("Tarea marcada: " + nota);
                        }

                        // Actualizamos la lista
                        modeloNotas.set(seleccionado, nota);
                        listaNotas.setSelectedIndex(seleccionado); // Mantener selección
                    } else {
                        System.out.println("Presionaste 'C', pero no hay elemento seleccionado.");
                    }

                    e.consume(); // Evita que la tecla haga otra cosa
                }
            }
        });

        // Mostramos todo
        setContentPane(panel);
        setVisible(true);

        // Damos foco a la lista para que pueda capturar la tecla desde el inicio
        listaNotas.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CapturaTeclaC());
    }
}