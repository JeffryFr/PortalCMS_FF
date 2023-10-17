package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "horario", schema = "portalweb", catalog = "")
public class HorarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idHorario", nullable = false)
    private int idHorario;
    @Basic
    @Column(name = "horaInicio", nullable = false)
    private Time horaInicio;
    @Basic
    @Column(name = "horaFin", nullable = false)
    private Time horaFin;
    @ManyToOne
    @JoinColumn(name = "codigoClase", referencedColumnName = "codigoClase", nullable = false)
    private ClaseEntity claseByCodigoClase;
    @ManyToOne
    @JoinColumn(name = "idDia", referencedColumnName = "idDia", nullable = false)
    private DiaEntity diaByIdDia;

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorarioEntity that = (HorarioEntity) o;

        if (idHorario != that.idHorario) return false;
        if (horaInicio != null ? !horaInicio.equals(that.horaInicio) : that.horaInicio != null) return false;
        if (horaFin != null ? !horaFin.equals(that.horaFin) : that.horaFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHorario;
        result = 31 * result + (horaInicio != null ? horaInicio.hashCode() : 0);
        result = 31 * result + (horaFin != null ? horaFin.hashCode() : 0);
        return result;
    }

    public ClaseEntity getClaseByCodigoClase() {
        return claseByCodigoClase;
    }

    public void setClaseByCodigoClase(ClaseEntity claseByCodigoClase) {
        this.claseByCodigoClase = claseByCodigoClase;
    }

    public DiaEntity getDiaByIdDia() {
        return diaByIdDia;
    }

    public void setDiaByIdDia(DiaEntity diaByIdDia) {
        this.diaByIdDia = diaByIdDia;
    }
}
