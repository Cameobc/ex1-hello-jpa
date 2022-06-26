package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.transform.Result;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // application 로딩 시점에 딱 하나만 만들고
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 실제 db 저장하거나 행위는 트랜잭션 단위로 em 을 만든다.
        // 쓰레드 간에 공유하면 안된다.(절대금지!!)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        //JPA 에서 데이터를 변경하는 모든 단위는 트랜잭션 단위에서 함
        tx.begin();
        
        try {

            //영속
            Member member = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            // 쓰기지연 sql 저장소에 저장함
            em.persist(member);
            em.persist(member2);
            System.out.println("====================");

            // 트랜잭션 커밋 시점에 db에 쿼리가 날아간다.
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        
        emf.close();
    }
}
