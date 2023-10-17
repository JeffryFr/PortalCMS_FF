package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "salon", schema = "portalweb", catalog = "")
@NamedQueries({
        @NamedQuery(name="SalonEntity.findAll", query="SELECT s FROM SalonEntity s"),
        @NamedQuery(name="SalonEntity.findByCodigo", query="SELECT s FROM SalonEntity s WHERE s.codigoSalon = :codigosalon"),
        @NamedQuery(name="SalonEntity.findByOrientador", query="SELECT s FROM SalonEntity s WHERE s.maestroByOrientador.codigoMaestro = :codigomaestro")
})
public class SalonEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigoSalon", nullable = false, length = 4)
    private String codigoSalon;
    @Basic
    @Column(name = "grado", nullable = false)
    private int grado;
    @Basic
    @Column(name = "seccion", nullable = false, length = 1)
    private String seccion;
    @OneToMany(mappedBy = "salonByCodigoSalon")
    private Collection<AlumnoEntity> alumnosByCodigoSalon;
    @OneToMany(mappedBy = "salonByCodigoSalon")
    private Collection<ClaseEntity> clasesByCodigoSalon;
    @ManyToOne
    @JoinColumn(name = "orientador", referencedColumnName = "codigoMaestro", nullable = false)
    private MaestroEntity maestroByOrientador;

    public String getCodigoSalon() {
        return codigoSalon;
    }

    public void setCodigoSalon(String codigoSalon) {
        this.codigoSalon = codigoSalon;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalonEntity that = (SalonEntity) o;

        if (grado != that.grado) return false;
        if (codigoSalon != null ? !codigoSalon.equals(that.codigoSalon) : that.codigoSalon != null) return false;
        if (seccion != null ? !seccion.equals(that.seccion) : that.seccion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoSalon != null ? codigoSalon.hashCode() : 0;
        result = 31 * result + grado;
        result = 31 * result + (seccion != null ? seccion.hashCode() : 0);
        return result;
    }

    public Collection<AlumnoEntity> getAlumnosByCodigoSalon() {
        return alumnosByCodigoSalon;
    }

    public void setAlumnosByCodigoSalon(Collection<AlumnoEntity> alumnosByCodigoSalon) {
        this.alumnosByCodigoSalon = alumnosByCodigoSalon;
    }

    public Collection<ClaseEntity> getClasesByCodigoSalon() {
        return clasesByCodigoSalon;
    }

    public void setClasesByCodigoSalon(Collection<ClaseEntity> clasesByCodigoSalon) {
        this.clasesByCodigoSalon = clasesByCodigoSalon;
    }

    public MaestroEntity getMaestroByOrientador() {
        return maestroByOrientador;
    }

    public void setMaestroByOrientador(MaestroEntity maestroByOrientador) {
        this.maestroByOrientador = maestroByOrientador;
    }
}
