package sv.edu.cms.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.edu.cms.www.entities.ClaseEntity;
import sv.edu.cms.www.entities.SalonEntity;
import sv.edu.cms.www.entities.MaestromateriaEntity;
import sv.edu.cms.www.models.ClaseModel;
import sv.edu.cms.www.models.SalonModel;
import sv.edu.cms.www.models.MaestroMateriaModel;
import sv.edu.cms.www.utils.JsfUtil;
import java.util.List;

@ManagedBean
@RequestScoped
public class ClaseBean {
    ClaseModel claseModel = new ClaseModel();
    private ClaseEntity clase;
    private String salon = "";
    private String salonForm;
    private int maestroMateriaForm;

    public ClaseBean(){
        clase = new ClaseEntity();
    }

    public ClaseModel getClaseModel() {
        return claseModel;
    }
    public void setClaseModel(ClaseModel claseModel) {
        this.claseModel = claseModel;
    }

    public ClaseEntity getClase() {
        return clase;
    }
    public void setClase(ClaseEntity clase) {
        this.clase = clase;
    }

    public String getSalon() {
        return salon;
    }
    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getSalonForm() {
        return salonForm;
    }
    public void setSalonForm(String salonForm) {
        this.salonForm = salonForm;
    }

    public int getMaestroMateriaForm() {
        return maestroMateriaForm;
    }
    public void setMaestroMateriaForm(int maestroMateriaForm) {
        this.maestroMateriaForm = maestroMateriaForm;
    }

    public String guardarClase(){
        SalonModel salonModel = new SalonModel();
        SalonEntity salon1 = salonModel.getSalonByCodigo(salonForm);

        MaestroMateriaModel mmModel = new MaestroMateriaModel();
        MaestromateriaEntity mm1 = mmModel.getDatoById(maestroMateriaForm);

        clase.setMaestromateriaByIdMateriaMaestro(mm1);
        clase.setSalonByCodigoSalon(salon1);

        clase.setCodigoClase(crearCodigo(mm1.getMateriaByIdMateria().getMateria(), salon1.getCodigoSalon()));

        if(claseModel.verificarClase(salon1, mm1) != 1){
            if(claseModel.guardarClase(clase) != 1){
                JsfUtil.setErrorMessage(null, "Ocurrio un error al registrar la clase");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Clase registrado exitosamente");
            }
        }else{
            JsfUtil.setErrorMessage(null, "Esta materia ya es impartida en el salón");
            return null;//Regreso a la misma página
        }
        return "Clases?faces-redirect=true";
    }

    public String crearCodigo(String materia, String salon){
        String[] palabras = materia.split(" ");

        if (palabras.length == 1) {
            // Si solo hay una palabra, tomamos sus primeras tres letras
            if (materia.length() >= 3) {
                return materia.substring(0, 3)+salon;
            } else {
                return materia+salon;
            }
        } else {
            // Si hay múltiples palabras, tomamos la inicial de cada palabra
            StringBuilder resultado = new StringBuilder();

            for (String palabra : palabras) {
                if (!palabra.isEmpty()) {
                    resultado.append(palabra.charAt(0));
                }
            }

            return resultado.toString()+salon;
        }
    }

    public String eliminarClase(){
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        if(claseModel.eliminarClase(codigo) > 0){
            JsfUtil.setFlashMessage("exito", "Clase eliminada exitosamente");
        }else{
            JsfUtil.setErrorMessage(null, "Hubo un error al eliminar la clase");
        }
        return "Clases?faces-redirect=true";
    }
}
