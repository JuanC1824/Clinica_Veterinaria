package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.MascotaVO;

public class MascotaDAO {

    public boolean insertar(MascotaVO m) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement psCheck = conn.prepareStatement("SELECT * FROM persona WHERE documento = ?");
            psCheck.setString(1, m.getDocumentoDueno());
            ResultSet rsCheck = psCheck.executeQuery();

            if (!rsCheck.next()) {
                System.out.println("Dueño no registrado: " + m.getDocumentoDueno());
                return false;
            }

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO mascota(nombre, especie, raza, documento_dueno) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getDocumentoDueno());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    m.setId(rs.getInt(1));
                    System.out.println("ID generado: " + m.getId());
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public MascotaVO consultar(int id) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mascota WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MascotaVO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getString("documento_dueno")
                );
            }
        } catch (Exception e) {}
        return null;
    }

    public boolean actualizar(MascotaVO m) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE mascota SET nombre=?, especie=?, raza=?, documento_dueno=? WHERE id=?");
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getDocumentoDueno());
            ps.setInt(5, m.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM mascota WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<MascotaVO> listar() {
        ArrayList<MascotaVO> lista = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mascota");
            while (rs.next()) {
                lista.add(new MascotaVO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getString("documento_dueno")
                ));
            }
        } catch (Exception e) {}
        return lista;
    }
}
