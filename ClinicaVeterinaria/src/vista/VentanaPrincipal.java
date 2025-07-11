package vista;

import vista.VentanaPersonas;
import vista.VentanaMascotas;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Clínica Veterinaria ABC");
        setSize(600, 400); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnPersonas = new JButton("Gestionar Personas");
        JButton btnMascotas = new JButton("Gestionar Mascotas");

        panelBotones.add(btnPersonas);
        panelBotones.add(btnMascotas);

        add(panelBotones, BorderLayout.SOUTH);

        btnPersonas.addActionListener(e -> new VentanaPersonas());
        btnMascotas.addActionListener(e -> new VentanaMascotas());

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
