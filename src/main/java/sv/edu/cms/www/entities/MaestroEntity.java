package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "maestro", schema = "portalweb", catalog = "")
@NamedQueries({
        @NamedQuery(name="MaestroEntity.findAll", query="SELECT m FROM MaestroEntity m"),
        @NamedQuery(name="MaestroEntity.findMaestro", query="SELECT m FROM MaestroEntity m WHERE m.rolByIdRol.idRol = 2"),
        //ANDRES
        @NamedQuery(name = "MaestroEntity.findByCodigo", query = "SELECT m FROM MaestroEntity m WHERE m.codigoMaestro = :codigo"),
        @NamedQuery(name = "MaestroEntity.findByNombres", query = "SELECT m FROM MaestroEntity m WHERE m.nombres = :nombres"),
        @NamedQuery(name = "MaestroEntity.findByApellidos", query = "SELECT m FROM MaestroEntity m WHERE m.apellidos = :apellidos"),
        @NamedQuery(name = "MaestroEntity.findByCorreo", query = "SELECT m FROM MaestroEntity m WHERE m.correo = :correo"),
        //NamedQueries usados en login
        @NamedQuery(name="MaestroEntity.verifyByCodigoMaestro", query="SELECT m.codigoMaestro FROM MaestroEntity m WHERE m.codigoMaestro = :codigoMaestro"),
        @NamedQuery(name="MaestroEntity.verifyByContra", query="SELECT m FROM MaestroEntity m WHERE m.contra = :contra AND m.codigoMaestro = :codigoMaestro")
})
public class MaestroEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigoMaestro", nullable = false, length = 6)
    private String codigoMaestro;
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
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    @Basic
    @Column(name = "contra", nullable = false, length = 100)
    private String contra;
    @OneToMany(mappedBy = "maestroByCodigoMaestro")
    private Collection<DiarioEntity> diariosByCodigoMaestro;
    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol", nullable = false)
    private RolEntity rolByIdRol;
    @OneToMany(mappedBy = "maestroByCodigoMaestro")
    private Collection<MaestromateriaEntity> maestromateriasByCodigoMaestro;
    @OneToMany(mappedBy = "maestroByOrientador")
    private Collection<SalonEntity> salonsByCodigoMaestro;

    public String getCodigoMaestro() {
        return codigoMaestro;
    }

    public void setCodigoMaestro(String codigoMaestro) {
        this.codigoMaestro = codigoMaestro;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

        MaestroEntity that = (MaestroEntity) o;

        if (codigoMaestro != null ? !codigoMaestro.equals(that.codigoMaestro) : that.codigoMaestro != null)
            return false;
        if (nombres != null ? !nombres.equals(that.nombres) : that.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;
        if (contra != null ? !contra.equals(that.contra) : that.contra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoMaestro != null ? codigoMaestro.hashCode() : 0;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (contra != null ? contra.hashCode() : 0);
        return result;
    }

    public Collection<DiarioEntity> getDiariosByCodigoMaestro() {
        return diariosByCodigoMaestro;
    }

    public void setDiariosByCodigoMaestro(Collection<DiarioEntity> diariosByCodigoMaestro) {
        this.diariosByCodigoMaestro = diariosByCodigoMaestro;
    }

    public RolEntity getRolByIdRol() {
        return rolByIdRol;
    }

    public void setRolByIdRol(RolEntity rolByIdRol) {
        this.rolByIdRol = rolByIdRol;
    }

    public Collection<MaestromateriaEntity> getMaestromateriasByCodigoMaestro() {
        return maestromateriasByCodigoMaestro;
    }

    public void setMaestromateriasByCodigoMaestro(Collection<MaestromateriaEntity> maestromateriasByCodigoMaestro) {
        this.maestromateriasByCodigoMaestro = maestromateriasByCodigoMaestro;
    }

    public Collection<SalonEntity> getSalonsByCodigoMaestro() {
        return salonsByCodigoMaestro;
    }

    public void setSalonsByCodigoMaestro(Collection<SalonEntity> salonsByCodigoMaestro) {
        this.salonsByCodigoMaestro = salonsByCodigoMaestro;
    }
}
