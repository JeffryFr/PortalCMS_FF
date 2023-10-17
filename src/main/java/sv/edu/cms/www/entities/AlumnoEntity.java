package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "alumno", schema = "portalweb", catalog = "")
@NamedQueries({
        @NamedQuery(name="AlumnoEntity.findAll", query="SELECT a FROM AlumnoEntity a"),
        @NamedQuery(name="AlumnoEntity.findAllGrado", query="SELECT a FROM AlumnoEntity a WHERE a.salonByCodigoSalon.codigoSalon = :codigoSalon"),
        @NamedQuery(name="AlumnoEntity.findAlumnos", query="SELECT a FROM AlumnoEntity a ORDER BY a.apellidos ASC"),
        @NamedQuery(name="AlumnoEntity.findAlumnosByCodigoSalon", query="SELECT a FROM AlumnoEntity a WHERE a.salonByCodigoSalon.codigoSalon = :codigosalon ORDER BY a.apellidos ASC"),
        //NamedQueries usado en login
        @NamedQuery(name="AlumnoEntity.verifyByCodigoAlumno", query="SELECT a.codigoAlumno FROM AlumnoEntity a WHERE a.codigoAlumno = :codigoAlumno"),
        @NamedQuery(name="AlumnoEntity.verifyByContra", query="SELECT a FROM AlumnoEntity a WHERE a.contra = :contra AND a.codigoAlumno = :codigoAlumno")
})
public class AlumnoEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigoAlumno", nullable = false, length = 6)
    private String codigoAlumno;
    @Basic
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    @Basic
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    @Basic
    @Column(name = "telefono", nullable = false, length = 8)
    private String telefono;
    @Basic
    @Column(name = "contra", nullable = false, length = 100)
    private String contra;
    @ManyToOne
    @JoinColumn(name = "codigoSalon", referencedColumnName = "codigoSalon", nullable = false)
    private SalonEntity salonByCodigoSalon;
    @OneToMany(mappedBy = "alumnoByCodigoAlumno")
    private Collection<AsistenciaEntity> asistenciasByCodigoAlumno;
    @OneToMany(mappedBy = "alumnoByCodigoAlumno")
    private Collection<DiarioEntity> diariosByCodigoAlumno;
    @OneToMany(mappedBy = "alumnoByCodigoAlumno")
    private Collection<NotaEntity> notasByCodigoAlumno;
    @OneToMany(mappedBy = "alumnoByCodigoAlumno")
    private Collection<PermisoEntity> permisosByCodigoAlumno;

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlumnoEntity that = (AlumnoEntity) o;

        if (codigoAlumno != null ? !codigoAlumno.equals(that.codigoAlumno) : that.codigoAlumno != null) return false;
        if (nombres != null ? !nombres.equals(that.nombres) : that.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (contra != null ? !contra.equals(that.contra) : that.contra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoAlumno != null ? codigoAlumno.hashCode() : 0;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (contra != null ? contra.hashCode() : 0);
        return result;
    }

    public SalonEntity getSalonByCodigoSalon() {
        return salonByCodigoSalon;
    }

    public void setSalonByCodigoSalon(SalonEntity salonByCodigoSalon) {
        this.salonByCodigoSalon = salonByCodigoSalon;
    }

    public Collection<AsistenciaEntity> getAsistenciasByCodigoAlumno() {
        return asistenciasByCodigoAlumno;
    }

    public void setAsistenciasByCodigoAlumno(Collection<AsistenciaEntity> asistenciasByCodigoAlumno) {
        this.asistenciasByCodigoAlumno = asistenciasByCodigoAlumno;
    }

    public Collection<DiarioEntity> getDiariosByCodigoAlumno() {
        return diariosByCodigoAlumno;
    }

    public void setDiariosByCodigoAlumno(Collection<DiarioEntity> diariosByCodigoAlumno) {
        this.diariosByCodigoAlumno = diariosByCodigoAlumno;
    }

    public Collection<NotaEntity> getNotasByCodigoAlumno() {
        return notasByCodigoAlumno;
    }

    public void setNotasByCodigoAlumno(Collection<NotaEntity> notasByCodigoAlumno) {
        this.notasByCodigoAlumno = notasByCodigoAlumno;
    }

    public Collection<PermisoEntity> getPermisosByCodigoAlumno() {
        return permisosByCodigoAlumno;
    }

    public void setPermisosByCodigoAlumno(Collection<PermisoEntity> permisosByCodigoAlumno) {
        this.permisosByCodigoAlumno = permisosByCodigoAlumno;
    }
}
