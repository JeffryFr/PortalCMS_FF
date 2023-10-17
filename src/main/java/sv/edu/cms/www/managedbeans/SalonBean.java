package sv.edu.cms.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.entities.SalonEntity;
import sv.edu.cms.www.models.SalonModel;
import sv.edu.cms.www.models.AlumnoModel;
import sv.edu.cms.www.models.MaestroModel;
import sv.edu.cms.www.utils.JsfUtil;

import java.time.LocalDate;
import java.util.List;

@ManagedBean
@RequestScoped
public class SalonBean {
    SalonModel salonModel = new SalonModel();
    private SalonEntity salon;
    private SalonEntity salonSeleccionado;
    private String maestro;
    private List<AlumnoEntity> alumnos = null;

    public SalonBean(){
        salon = new SalonEntity();
        salonSeleccionado = new SalonEntity();
    }

    public SalonModel getSalonModel() {
        return salonModel;
    }
    public void setSalonModel(SalonModel salonModel) {
        this.salonModel = salonModel;
    }

    public SalonEntity getSalon() {
        return salon;
    }
    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }

    public SalonEntity getSalonSeleccionado() {
        return salonSeleccionado;
    }
    public void setSalonSeleccionado(SalonEntity salonSeleccionado) {
        this.salonSeleccionado = salonSeleccionado;
    }

    public String getMaestro() {
        return maestro;
    }
    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public void setAlumnos(List<AlumnoEntity> alumnos) {
        this.alumnos = alumnos;
    }
    public List<AlumnoEntity> getAlumnos() {
        return alumnos;
    }

    public List<SalonEntity> getListaSalones(){
        return salonModel.getSalon();
    }

    public String guardarSalon(){
        if(salonModel.verifyOrientador(maestro) != 1){
            MaestroModel maestroModel = new MaestroModel();

            LocalDate currentDate = LocalDate.now();
            int year = currentDate.getYear();
            int año = year % 100;
            String codigo = salon.getGrado()+salon.getSeccion()+año;

            salon.setMaestroByOrientador(maestroModel.obtenerMaestro(maestro));
            salon.setCodigoSalon(codigo);
            if(salonModel.guardarSalon(salon) != 1) {
                JsfUtil.setErrorMessage(null, "Verifica los datos:\nSolo puede haber una sección por grado.\nSolo puede haber dos salones con el mismo grado.");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Salón registrado exitosamente");
            }
            return "Salones?faces-redirect=true";
        }else{
            JsfUtil.setErrorMessage(null, "El maestro solo puede orientar un salón");
            return null;//Regreso a la misma página
        }
    }

    public String actualizarSalon(){
        if(salonModel.verifyOrientador(maestro) != 1){
            MaestroModel maestroModel = new MaestroModel();
            salonSeleccionado.setMaestroByOrientador(maestroModel.obtenerMaestro(maestro));


            if(salonModel.editarSalon(salonSeleccionado) != 1) {
                JsfUtil.setErrorMessage(null, "Ocurrio un Error, intente más tarde.");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Salón modificado exitosamente");
            }
            return "Salones?faces-redirect=true";
        }else{
            JsfUtil.setErrorMessage(null, "El maestro solo puede orientar un salón");
            return null;//Regreso a la misma página
        }
    }

    public String eliminarSalon(){
        // Leyendo el parametro enviado desde la vista
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        AlumnoModel alumnoModel = new AlumnoModel();
        List<AlumnoEntity> alumnosLista = alumnoModel.getAlumnoByCodigoSalon(codigo);

        if(!(alumnosLista != null)){
            if (salonModel.eliminarSalon(codigo) > 0) {
                JsfUtil.setFlashMessage("exito", "Salon eliminado exitosamente");
            }
            else{
                JsfUtil.setErrorMessage(null, "No se pudo borrar a este Salon");
            }
            return "Salones?faces-redirect=true";
        }else{
            JsfUtil.setErrorMessage(null, "Para eliminar un salón este debe estar vacio");
            return null;//Regreso a la misma página
        }
    }

    public void verAlumnos(){
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        AlumnoModel alumnoModel = new AlumnoModel();
        List<AlumnoEntity> alumnosLista = alumnoModel.getAlumnoByCodigoSalon(codigo);

        alumnos = alumnosLista;
    }

    public void obtenerSalon(){
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        salonSeleccionado = salonModel.getSalonByCodigo(codigo);
    }
}
