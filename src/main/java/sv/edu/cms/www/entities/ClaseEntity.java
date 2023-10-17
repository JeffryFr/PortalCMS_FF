package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "clase", schema = "portalweb", catalog = "")
@NamedQueries({
        @NamedQuery(name="ClaseEntity.findAll", query="SELECT c FROM ClaseEntity c"),
        @NamedQuery(name="ClaseEntity.findBySalon", query="SELECT c FROM ClaseEntity c WHERE c.salonByCodigoSalon = :codigosalon"),
        @NamedQuery(name="ClaseEntity.compare", query="SELECT c FROM ClaseEntity c WHERE c.salonByCodigoSalon = :codigosalon AND c.maestromateriaByIdMateriaMaestro.materiaByIdMateria = :materia")
})
public class ClaseEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigoClase", nullable = false, length = 10)
    private String codigoClase;
    @OneToMany(mappedBy = "claseByCodigoClase")
    private Collection<AsistenciaEntity> asistenciasByCodigoClase;
    @ManyToOne
    @JoinColumn(name = "idMateriaMaestro", referencedColumnName = "idMaestroMateria", nullable = false)
    private MaestromateriaEntity maestromateriaByIdMateriaMaestro;
    @ManyToOne
    @JoinColumn(name = "codigoSalon", referencedColumnName = "codigoSalon", nullable = false)
    private SalonEntity salonByCodigoSalon;
    @OneToMany(mappedBy = "claseByCodigoClase")
    private Collection<EvaluacionEntity> evaluacionsByCodigoClase;
    @OneToMany(mappedBy = "claseByCodigoClase")
    private Collection<HorarioEntity> horariosByCodigoClase;

    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClaseEntity that = (ClaseEntity) o;

        if (codigoClase != null ? !codigoClase.equals(that.codigoClase) : that.codigoClase != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoClase != null ? codigoClase.hashCode() : 0;
    }

    public Collection<AsistenciaEntity> getAsistenciasByCodigoClase() {
        return asistenciasByCodigoClase;
    }

    public void setAsistenciasByCodigoClase(Collection<AsistenciaEntity> asistenciasByCodigoClase) {
        this.asistenciasByCodigoClase = asistenciasByCodigoClase;
    }

    public MaestromateriaEntity getMaestromateriaByIdMateriaMaestro() {
        return maestromateriaByIdMateriaMaestro;
    }

    public void setMaestromateriaByIdMateriaMaestro(MaestromateriaEntity maestromateriaByIdMateriaMaestro) {
        this.maestromateriaByIdMateriaMaestro = maestromateriaByIdMateriaMaestro;
    }

    public SalonEntity getSalonByCodigoSalon() {
        return salonByCodigoSalon;
    }

    public void setSalonByCodigoSalon(SalonEntity salonByCodigoSalon) {
        this.salonByCodigoSalon = salonByCodigoSalon;
    }

    public Collection<EvaluacionEntity> getEvaluacionsByCodigoClase() {
        return evaluacionsByCodigoClase;
    }

    public void setEvaluacionsByCodigoClase(Collection<EvaluacionEntity> evaluacionsByCodigoClase) {
        this.evaluacionsByCodigoClase = evaluacionsByCodigoClase;
    }

    public Collection<HorarioEntity> getHorariosByCodigoClase() {
        return horariosByCodigoClase;
    }

    public void setHorariosByCodigoClase(Collection<HorarioEntity> horariosByCodigoClase) {
        this.horariosByCodigoClase = horariosByCodigoClase;
    }
}
