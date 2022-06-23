package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
          /*  Member member = new Member();
            member.setId(2l);
            member.setName("helloB");
            em.persist(member);*/
           /* Member findMember = em.find(Member.class, 1L);
            // 변경 사항이 있으면 update 쿼리를 날린다.
            findMember.setName("HelloJPA");
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());*/
            // 대상이 테이블이 아닌 객체가 된다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
            
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
