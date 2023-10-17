package sv.edu.cms.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.edu.cms.www.entities.MaestromateriaEntity;
import sv.edu.cms.www.models.MaestroMateriaModel;
import sv.edu.cms.www.utils.JsfUtil;
import java.util.List;

@ManagedBean
@RequestScoped
public class MaestroMateriaBean {
    MaestroMateriaModel mmModel = new MaestroMateriaModel();
    private MaestromateriaEntity maestroMateria;

    public MaestroMateriaBean(){
        maestroMateria = new MaestromateriaEntity();
    }

    public void setMaestroMateria(MaestromateriaEntity maestroMateria) {
        this.maestroMateria = maestroMateria;
    }

    public MaestromateriaEntity getMaestroMateria() {
        return maestroMateria;
    }

    public MaestroMateriaModel getMmModel() {
        return mmModel;
    }
    public void setMmModel(MaestroMateriaModel mmModel) {
        this.mmModel = mmModel;
    }
}
