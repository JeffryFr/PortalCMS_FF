package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.utils.JpaUtil;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.edu.cms.www.models.SalonModel;

public class ClaseModel {
    public List<ClaseEntity> obtenerClases(String salon){
        SalonModel salonModel = new SalonModel();
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta;
            if(salon.isEmpty() || salon.equals("0")){
                consulta = em.createNamedQuery("ClaseEntity.findAll");
            }else{
                SalonEntity listaSalon = salonModel.getSalonByCodigo(salon);
                consulta = em.createNamedQuery("ClaseEntity.findBySalon");
                consulta.setParameter("codigosalon", listaSalon);
            }
            List<ClaseEntity> clase = consulta.getResultList();
            em.close();

            if(!clase.isEmpty()){
                return clase;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    public int verificarClase(SalonEntity salon, MaestromateriaEntity maestroMateria){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            MateriaEntity materia = maestroMateria.getMateriaByIdMateria();

            Query consulta;
            consulta = em.createNamedQuery("ClaseEntity.compare");
            consulta.setParameter("codigosalon", salon);
            consulta.setParameter("materia", materia);
            List<ClaseEntity> clase = consulta.getResultList();
            em.close();

            if(!clase.isEmpty()){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            em.close();
            return 0;
        }
    }

    public int guardarClase(ClaseEntity clase){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(clase);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarClase(String codigo){
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try{
            ClaseEntity clase = em.find(ClaseEntity.class, codigo);
            if (clase != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(clase);//Borrando la instancia
                tran.commit();//Confirmando la transacción
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
}
