import jakarta.persistence.*;
import java.util.List;

public class MascotaDao {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarMascota(Mascota m) {
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        return "Mascota registrada.";
    }

    public Mascota consultarMascota(Long id) {
        return em.find(Mascota.class, id);
    }

    public List<Mascota> consultarListaMascotas() {
        return em.createQuery("SELECT m FROM Mascota m", Mascota.class).getResultList();
    }

    public List<Mascota> consultarListaMascotasPorSexo(String sexo) {
        return em.createQuery("SELECT m FROM Mascota m WHERE m.sexo = :sexo", Mascota.class)
                 .setParameter("sexo", sexo)
                 .getResultList();
    }

    public String actualizarMascota(Mascota m) {
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        return "Mascota actualizada.";
    }

    public String eliminarMascota(Mascota m) {
        em.getTransaction().begin();
        em.remove(em.merge(m));
        em.getTransaction().commit();
        return "Mascota eliminada.";
    }

    public void close() {
        em.close();
        JPAUtil.shutdown();
    }
}
