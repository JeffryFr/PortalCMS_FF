package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "asistencia", schema = "portalweb", catalog = "")
public class AsistenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAsistencia", nullable = false)
    private int idAsistencia;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado", nullable = false)
    private EstadoasistenciaEntity estadoasistenciaByIdEstado;
    @ManyToOne
    @JoinColumn(name = "codigoClase", referencedColumnName = "codigoClase", nullable = false)
    private ClaseEntity claseByCodigoClase;
    @ManyToOne
    @JoinColumn(name = "codigoAlumno", referencedColumnName = "codigoAlumno", nullable = false)
    private AlumnoEntity alumnoByCodigoAlumno;

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsistenciaEntity that = (AsistenciaEntity) o;

        if (idAsistencia != that.idAsistencia) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAsistencia;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public EstadoasistenciaEntity getEstadoasistenciaByIdEstado() {
        return estadoasistenciaByIdEstado;
    }

    public void setEstadoasistenciaByIdEstado(EstadoasistenciaEntity estadoasistenciaByIdEstado) {
        this.estadoasistenciaByIdEstado = estadoasistenciaByIdEstado;
    }

    public ClaseEntity getClaseByCodigoClase() {
        return claseByCodigoClase;
    }

    public void setClaseByCodigoClase(ClaseEntity claseByCodigoClase) {
        this.claseByCodigoClase = claseByCodigoClase;
    }

    public AlumnoEntity getAlumnoByCodigoAlumno() {
        return alumnoByCodigoAlumno;
    }

    public void setAlumnoByCodigoAlumno(AlumnoEntity alumnoByCodigoAlumno) {
        this.alumnoByCodigoAlumno = alumnoByCodigoAlumno;
    }
}
