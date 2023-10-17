package sv.edu.cms.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.entities.SalonEntity;
import sv.edu.cms.www.models.SalonModel;
import sv.edu.cms.www.models.AlumnoModel;
import sv.edu.cms.www.utils.JsfUtil;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@ManagedBean
@RequestScoped
public class AlumnoBean {
    AlumnoModel alumnoModel = new AlumnoModel();
    SalonModel salonModel = new SalonModel();
    private AlumnoEntity alumno;
    private AlumnoEntity alumnoSeleccionado;
    private String codigoSalon;
    private SalonEntity salon;
    private static final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$*+=?";


    public AlumnoBean(){
        alumno = new AlumnoEntity();
        alumnoSeleccionado = new AlumnoEntity();
    }

    public SalonEntity getSalon() {
        return salon;
    }
    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }

    public String getCodigoSalon() {
        return codigoSalon;
    }
    public void setCodigoSalon(String codigoSalon) {
        this.codigoSalon = codigoSalon;
    }

    public AlumnoEntity getAlumno() {
        return alumno;
    }
    public void setAlumno(AlumnoEntity alumno) {
        this.alumno = alumno;
    }

    public AlumnoEntity getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }
    public void setAlumnoSeleccionado(AlumnoEntity alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }

    public List<AlumnoEntity> getListaAlumnos(){
        return alumnoModel.getAlumno();
    }

    public String guardarAlumno(){
        alumno.setCodigoAlumno(this.obtenerCarnet(alumno.getApellidos()));
        alumno.setContra(this.generarContrasena());
        alumno.setSalonByCodigoSalon(salonModel.getSalonByCodigo(codigoSalon));
        if (alumnoModel.guardarAlumno(alumno) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
        }
        return "Alumnos?faces-redirect=true";
    }

    public String actualizarAlumno(){
        alumnoSeleccionado.setSalonByCodigoSalon(salonModel.getSalonByCodigo(codigoSalon));
        if (alumnoModel.editarAlumno(alumnoSeleccionado) != 1) {
            JsfUtil.setErrorMessage(null, "Ocurrio un error");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Alumno modificado exitosamente");
        }
        return "Alumnos?faces-redirect=true";
    }

    public String eliminarAlumno(){
        // Leyendo el parametro enviado desde la vista
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        if (alumnoModel.eliminarAlumno(codigo) > 0) {
            JsfUtil.setFlashMessage("exito", "Alumno eliminado exitosamente");
        }
        else{
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "Alumnos?faces-redirect=true";
    }

    public String cambiarContra(){
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        alumnoSeleccionado = alumnoModel.obtenerAlumno(codigo);
        alumnoSeleccionado.setContra(this.generarContrasena());
        if (alumnoModel.editarAlumno(alumnoSeleccionado) != 1) {
            JsfUtil.setErrorMessage(null, "Ocurrio un error");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "La contraseña fue cambiada");
        }
        return "Alumnos?faces-redirect=true";
    }

    public void obtenerAlumno() {
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        alumnoSeleccionado = alumnoModel.obtenerAlumno(codigo);
    }

    public String obtenerCarnet(String apellidos) {
        String[] palabras = apellidos.split(" ");

        String iniciales = "";
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales += palabra.charAt(0);
            }
        }

        LocalDate fechaActual = LocalDate.now();
        int year = fechaActual.getYear();
        int ultimosDosDigitos = year % 100;

        Random rand = new Random();
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int numeroAleatorio = rand.nextInt(10);
            resultado.append(numeroAleatorio);
        }
        String cadenaConcatenada = resultado.toString();

        return iniciales+ultimosDosDigitos+cadenaConcatenada;
    }

    public static String generarContrasena() {
        int longitud = 7;
        SecureRandom random = new SecureRandom();
        StringBuilder contrasena = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            char caracter = caracteres.charAt(indice);
            contrasena.append(caracter);
        }

        return contrasena.toString();
    }
}
