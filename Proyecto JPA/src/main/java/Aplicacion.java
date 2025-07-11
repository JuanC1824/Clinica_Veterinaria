import javax.swing.JOptionPane;
import java.util.List;

public class Aplicacion {
    MascotaDao dao = new MascotaDao();

    public void iniciar() {
        String menu = "MENU DE OPCIONES\n\n"
                    + "1. Registrar Mascota\n"
                    + "2. Consultar Mascota\n"
                    + "3. Lista de Mascotas\n"
                    + "4. Lista por Sexo\n"
                    + "5. Actualizar Mascota\n"
                    + "6. Eliminar Mascota\n"
                    + "7. Salir\n";

     int opcion = 0;
while (opcion != 7) {
    opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
    switch (opcion) {
        case 1:
            registrar();
            break;
        case 2:
            consultar();
            break;
        case 3:
            listar();
            break;
        case 4:
            listarPorSexo();
            break;
        case 5:
            actualizar();
            break;
        case 6:
            eliminar();
            break;
        case 7:
            dao.close();
            break;
    }
}

    }

    private void registrar() {
        Mascota m = new Mascota();
        m.setNombre(JOptionPane.showInputDialog("Nombre:"));
        m.setRaza(JOptionPane.showInputDialog("Raza:"));
        m.setColorMascota(JOptionPane.showInputDialog("Color:"));
        m.setSexo(JOptionPane.showInputDialog("Sexo:"));
        System.out.println(dao.registrarMascota(m));
    }

    private void consultar() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("ID:"));
        Mascota m = dao.consultarMascota(id);
        System.out.println(m != null ? m : "No encontrada.");
    }

    private void listar() {
        List<Mascota> lista = dao.consultarListaMascotas();
        lista.forEach(System.out::println);
    }

    private void listarPorSexo() {
        String sexo = JOptionPane.showInputDialog("Sexo:");
        List<Mascota> lista = dao.consultarListaMascotasPorSexo(sexo);
        lista.forEach(System.out::println);
    }

    private void actualizar() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("ID:"));
        Mascota m = dao.consultarMascota(id);
        if (m != null) {
            m.setNombre(JOptionPane.showInputDialog("Nuevo nombre:", m.getNombre()));
            m.setRaza(JOptionPane.showInputDialog("Nueva raza:", m.getRaza()));
            m.setColorMascota(JOptionPane.showInputDialog("Nuevo color:", m.getColorMascota()));
            m.setSexo(JOptionPane.showInputDialog("Nuevo sexo:", m.getSexo()));
            System.out.println(dao.actualizarMascota(m));
        }
    }

    private void eliminar() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("ID:"));
        Mascota m = dao.consultarMascota(id);
        if (m != null) {
            System.out.println(dao.eliminarMascota(m));
        }
    }
}
