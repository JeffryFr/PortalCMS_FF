package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.entities.SalonEntity;
import sv.edu.cms.www.utils.JpaUtil;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.edu.cms.www.entities.MaestroEntity;
import sv.edu.cms.www.entities.RolEntity;

public class MaestroModel {
    public List<MaestroEntity> maestrosLista(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestroEntity.findMaestro");
            List<MaestroEntity> listado = consulta.getResultList();
            em.close();

            if(!listado.isEmpty()){
                return listado;
            }else{
                return null;
            }
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    public MaestroEntity obtenerMaestro(String codigoMaestro) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a travez del metodo find
            MaestroEntity maestro = em.find(MaestroEntity.class, codigoMaestro);
            em.close();
            return maestro;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public Boolean verificarMaestro(String codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestroEntity.verifyByCodigoMaestro");
            consulta.setParameter("codigoMaestro", codigo);
            String maestro = consulta.getSingleResult().toString();
            em.close();

            if(!maestro.isEmpty()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            em.close();
            return false;
        }
    }

    public MaestroEntity iniciarMaestro(String codigo, String contra){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestroEntity.verifyByContra");
            consulta.setParameter("codigoMaestro", codigo);
            consulta.setParameter("contra", contra);
            List<MaestroEntity> lista = consulta.getResultList();

            if (!lista.isEmpty()) {
                MaestroEntity maestro = lista.get(0);
                return maestro;
            }else{
                return null;
            }
        } catch (Exception e){
            System.out.println(e);
            em.close();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de que el EntityManager se cierre en cualquier caso
            }
        }
    }

    public List<MaestroEntity> listarMaestros(){
        //Se Obtiene la instancia del em
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("MaestroEntity.findAll");
            List<MaestroEntity> lista = consulta.getResultList();
            em.close();
            return lista;
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    public int insertMaestro(MaestroEntity maestro) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(maestro); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarMaestro(MaestroEntity maestro){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();//Iniciando transacción
            em.merge(maestro); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarEstudiante(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            MaestroEntity est = em.find(MaestroEntity.class, codigo);
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
}
