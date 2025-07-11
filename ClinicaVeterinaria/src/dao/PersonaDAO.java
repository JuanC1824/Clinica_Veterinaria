package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.PersonaVO;

public class PersonaDAO {
    public boolean insertar(PersonaVO p) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO persona VALUES (?, ?, ?)");
            ps.setString(1, p.getDocumento());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
        e.printStackTrace();
        return false;
        }
    }

    public PersonaVO consultar(String documento) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM persona WHERE documento = ?");
            ps.setString(1, documento.trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PersonaVO(rs.getString("documento"), rs.getString("nombre"), rs.getString("telefono"));
            }
        } catch (Exception e) {}
        return null;
    }

    public boolean actualizar(PersonaVO p) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE persona SET nombre = ?, telefono = ? WHERE documento = ?");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getDocumento());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(String documento) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM persona WHERE documento = ?");
            ps.setString(1, documento);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<PersonaVO> listar() {
        ArrayList<PersonaVO> lista = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM persona");
            while (rs.next()) {
                lista.add(new PersonaVO(rs.getString("documento"), rs.getString("nombre"), rs.getString("telefono")));
            }
        } catch (Exception e) {}
        return lista;
    }
}
