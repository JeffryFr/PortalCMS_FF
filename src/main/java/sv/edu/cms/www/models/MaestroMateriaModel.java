package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.entities.ClaseEntity;
import sv.edu.cms.www.managedbeans.MaestroMateriaBean;
import sv.edu.cms.www.utils.JpaUtil;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sv.edu.cms.www.entities.MaestromateriaEntity;

public class MaestroMateriaModel {
    public List<MaestromateriaEntity> obtenerDatos(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestromateriaEntity.findAll");
            List<MaestromateriaEntity> lista = consulta.getResultList();
            em.close();

            if(!lista.isEmpty()){
                return lista;
            }else{
                return null;
            }
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    public MaestromateriaEntity getDatoById(int materiaMaestro){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            MaestromateriaEntity lista = em.find(MaestromateriaEntity.class, materiaMaestro);
            em.close();

            if(lista != null){
                return lista;
            }else{
                return null;
            }
        }catch (Exception e){
            em.close();
            return null;
        }
    }
}
