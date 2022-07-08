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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUseraname("member1");
            //member.changeTeam(team);
            em.persist(member);

            //역방향(주인이 아닌 방향)에 값을 설정하면 안 된다. -> 그런데 이거 안 넣어주면 1차 캐시에 등록된 값이 없어서 하단  for 문에서 값을 못찾음
            // 객체 지향적으로 생각해봤을 때 양방향으로 다 값을 넣어줘야함 -> member 의 setTeam 시점에 넣어줌
            //team.getMembers().add(member);

            //연관관계 세팅 메서드는 양쪽에 다 있으면 문제가 생길 수 있으니 addMember 를 냅두고, changeTeam 을 삭제함
            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m.getUseraname() = " + m.getUseraname());
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
