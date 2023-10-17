package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.MaestroEntity;
import sv.edu.cms.www.entities.SalonEntity;
import sv.edu.cms.www.utils.JpaUtil;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.edu.cms.www.entities.AlumnoEntity;

public class AlumnoModel {
    public List<AlumnoEntity> getAlumno(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("AlumnoEntity.findAlumnos");
            List<AlumnoEntity> alumno = consulta.getResultList();
            em.close();

            if(!alumno.isEmpty()){
                return alumno;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    public List<AlumnoEntity> getAlumnoByCodigoSalon(String codigoSalon){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("AlumnoEntity.findAlumnosByCodigoSalon");
            consulta.setParameter("codigosalon", codigoSalon);
            List<AlumnoEntity> alumno = consulta.getResultList();
            em.close();

            if(!alumno.isEmpty()){
                return alumno;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    public int guardarAlumno(AlumnoEntity alumno){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(alumno);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int editarAlumno(AlumnoEntity alumno) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(alumno); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarAlumno(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            AlumnoEntity est = em.find(AlumnoEntity.class, codigo);
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

    public AlumnoEntity obtenerAlumno(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a travez del metodo find
            AlumnoEntity alumno = em.find(AlumnoEntity.class, codigo);
            em.close();
            return alumno;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public Boolean verificarAlumno(String codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("AlumnoEntity.verifyByCodigoAlumno");
            consulta.setParameter("codigoAlumno", codigo);
            String alumno = consulta.getSingleResult().toString();
            em.close();

            if(!alumno.isEmpty()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            em.close();
            return false;
        }
    }

    public AlumnoEntity iniciarAlumno(String codigo, String contra){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("AlumnoEntity.verifyByContra");
            consulta.setParameter("codigoAlumno", codigo);
            consulta.setParameter("contra", contra);
            List<AlumnoEntity> lista = consulta.getResultList();

            if (!lista.isEmpty()) {
                AlumnoEntity alumno = lista.get(0);
                return alumno;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            em.close();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de que el EntityManager se cierre en cualquier caso
            }
        }
    }

    //RODRIGO
    public List<AlumnoEntity> listarAlumnos (String idSalon) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            Query consulta = em.createNamedQuery("AlumnoEntity.findAllGrado");
            consulta.setParameter("codigoSalon", idSalon);
            List<AlumnoEntity> lista = consulta.getResultList();
            em.close(); //Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
}
