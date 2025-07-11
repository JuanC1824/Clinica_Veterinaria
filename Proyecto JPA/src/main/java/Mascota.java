import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "mascotas")
public class Mascota implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(length = 45)
    private String raza;

    @Column(name = "color", length = 45)
    private String colorMascota;

    @Column(length = 45)
    private String sexo;

    public Mascota() {}

    public Mascota(String nombre, String raza, String colorMascota, String sexo) {
        this.nombre = nombre;
        this.raza = raza;
        this.colorMascota = colorMascota;
        this.sexo = sexo;
    }

    public Long getIdMascota() { return idMascota; }
    public void setIdMascota(Long idMascota) { this.idMascota = idMascota; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getColorMascota() { return colorMascota; }
    public void setColorMascota(String colorMascota) { this.colorMascota = colorMascota; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    @Override
    public String toString() {
        return "Mascota [id=" + idMascota + ", nombre=" + nombre + ", raza=" + raza + ", color=" + colorMascota + ", sexo=" + sexo + "]";
    }
}
