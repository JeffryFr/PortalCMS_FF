package sv.edu.cms.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.entities.MaestroEntity;
import sv.edu.cms.www.entities.RolEntity;
import sv.edu.cms.www.entities.SalonEntity;
import sv.edu.cms.www.models.AlumnoModel;
import sv.edu.cms.www.models.SalonModel;
import sv.edu.cms.www.models.MaestroModel;
import sv.edu.cms.www.utils.JsfUtil;
import java.util.List;
import java.util.Random;

@ManagedBean
@RequestScoped
public class MaestroBean {
    MaestroModel maestroModel = new MaestroModel();
    private MaestroEntity maestro;
    private SalonEntity salon;

    //ANDRES
    private RolEntity rol = new RolEntity();
    private List<MaestroEntity> listaMaestro;

    public MaestroBean() {
        salon = new SalonEntity();
        maestro = new MaestroEntity();
    }

    public MaestroModel getMaestroModel() {
        return maestroModel;
    }
    public void setMaestroModel(MaestroModel maestroModel) {
        this.maestroModel = maestroModel;
    }

    public MaestroEntity getMaestro() {
        return maestro;
    }
    public void setMaestro(MaestroEntity maestro) {
        this.maestro = maestro;
    }

    //ANDRESS
    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol){
        this.rol = rol;
    }

    public List<MaestroEntity> getListaMaestro(){
        return maestroModel.listarMaestros();
    }

    ///Parte RODRIGO
    public SalonEntity getSalon() {
        return salon;
    }
    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }

    public String MiSalon(String id) {
        SalonModel salonModel = new SalonModel();
        salon = salonModel.obtenerGrado(id);
        // Devuelve la cadena de navegación a la próxima página
        return "paginaSiguient";
    }

    public List<AlumnoEntity> getListaAlumnos(String id) {
        AlumnoModel alumnoModel = new AlumnoModel();
        return alumnoModel.listarAlumnos(id);
    }

    //ANDRES
    public String guardarMaestro(){
        boolean insertado = false;
        int resultado = 0;
        String nuevoCodigo = "";

        //Verficando
        MaestroEntity maestroExistente = maestroModel.obtenerMaestro(maestro.getCodigoMaestro());
        if (maestroExistente != null){
            //Actualizar
            rol.setIdRol(2);
            rol.setRol("Maestro");
            maestro.setRolByIdRol(rol);
            resultado =maestroModel.modificarMaestro(maestro);
        }else  {
            //GENERANDO LOS 3 NUMEROS ALEATORIOS
            Random random = new Random();
            int numero = random.nextInt(900) + 100;
            nuevoCodigo = "MTR"+numero;
            maestro.setCodigoMaestro(nuevoCodigo);
            rol.setIdRol(2);
            rol.setRol("Maestro");
            maestro.setRolByIdRol(rol);
            resultado = maestroModel.insertMaestro(maestro);
            insertado = true;
        }

        if (resultado > 0){
            if (insertado){
                JsfUtil.setFlashMessage("Exito", "El maestro ha sido registrado con exito");
            }else {
                JsfUtil.setFlashMessage("Exito", "Se ha modificado el Maestro con exito!!");
            }
        }else {
            JsfUtil.setErrorMessage(null, "No se ha podido ejecutar la solicitud");
        }
        return "maestroAdmin?faces-redirect=true";
    }

    public String eliminarMaestro(){
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        if (maestroModel.eliminarEstudiante(codigo)>0){
            JsfUtil.setFlashMessage("Exito", "Se ha eliminado con exito");
        }else {
            JsfUtil.setErrorMessage(null, "No se logro elimnar el maestro");
        }
        return "maestroAdmin?faces-redirect=true";
    }

    public void cargarMaestro(MaestroEntity maestroSelec){
        maestro = maestroSelec;
    }
}
