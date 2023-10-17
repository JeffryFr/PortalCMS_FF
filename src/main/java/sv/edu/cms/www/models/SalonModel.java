package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.utils.JpaUtil;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.edu.cms.www.entities.SalonEntity;

public class SalonModel {
    public List<SalonEntity> getSalon(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("SalonEntity.findAll");
            List<SalonEntity> salon = consulta.getResultList();
            em.close();

            if(!salon.isEmpty()){
                return salon;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    public SalonEntity getSalonByCodigo(String codigoSalon){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("SalonEntity.findByCodigo");
            consulta.setParameter("codigosalon", codigoSalon);
            List<SalonEntity> lista = consulta.getResultList();
            em.close();

            if(!lista.isEmpty()){
                SalonEntity salon = lista.get(0);
                return salon;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    public int verifyOrientador(String codigoMaestro){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("SalonEntity.findByOrientador");
            consulta.setParameter("codigomaestro", codigoMaestro);
            List<SalonEntity> lista = consulta.getResultList();
            em.close();

            if(!lista.isEmpty()){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            em.close();
            return 0;
        }
    }

    public int guardarSalon(SalonEntity salon){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(salon);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int editarSalon(SalonEntity salon) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(salon); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarSalon(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            SalonEntity est = em.find(SalonEntity.class, codigo);
            if (est != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(est);//Borrando la instancia
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

    // METODOS RODRIGO
    public SalonEntity obtenerGrado (String id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("SalonEntity.findByOrientador");
            consulta.setParameter("codigomaestro", id);
            List<SalonEntity> salon = consulta.getResultList();
            if (!salon.isEmpty()) {
                SalonEntity salon1 = salon.get(0);
                return salon1;
            }else{
                return null;
            }
        } catch (Exception e) {
            em.close();
            return null;
        }finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de que el EntityManager se cierre en cualquier caso
            }
        }
    }
}
