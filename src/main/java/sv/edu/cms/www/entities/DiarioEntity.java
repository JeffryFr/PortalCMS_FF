package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "diario", schema = "portalweb", catalog = "")
public class DiarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idDiario", nullable = false)
    private int idDiario;
    @Basic
    @Column(name = "nota", nullable = false, length = 200)
    private String nota;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "codigoMaestro", referencedColumnName = "codigoMaestro", nullable = false)
    private MaestroEntity maestroByCodigoMaestro;
    @ManyToOne
    @JoinColumn(name = "codigoAlumno", referencedColumnName = "codigoAlumno", nullable = false)
    private AlumnoEntity alumnoByCodigoAlumno;
    @ManyToOne
    @JoinColumn(name = "idObservacion", referencedColumnName = "idObservacion", nullable = false)
    private ObservacionEntity observacionByIdObservacion;

    public int getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(int idDiario) {
        this.idDiario = idDiario;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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

        DiarioEntity that = (DiarioEntity) o;

        if (idDiario != that.idDiario) return false;
        if (nota != null ? !nota.equals(that.nota) : that.nota != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDiario;
        result = 31 * result + (nota != null ? nota.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public MaestroEntity getMaestroByCodigoMaestro() {
        return maestroByCodigoMaestro;
    }

    public void setMaestroByCodigoMaestro(MaestroEntity maestroByCodigoMaestro) {
        this.maestroByCodigoMaestro = maestroByCodigoMaestro;
    }

    public AlumnoEntity getAlumnoByCodigoAlumno() {
        return alumnoByCodigoAlumno;
    }

    public void setAlumnoByCodigoAlumno(AlumnoEntity alumnoByCodigoAlumno) {
        this.alumnoByCodigoAlumno = alumnoByCodigoAlumno;
    }

    public ObservacionEntity getObservacionByIdObservacion() {
        return observacionByIdObservacion;
    }

    public void setObservacionByIdObservacion(ObservacionEntity observacionByIdObservacion) {
        this.observacionByIdObservacion = observacionByIdObservacion;
    }
}
