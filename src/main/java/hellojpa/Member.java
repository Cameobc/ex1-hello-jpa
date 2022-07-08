package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USENAME")
    private String useraname;

   /* @Column(name ="TEAM_ID")
    private Long teamId;*/

    // 멤버가 M 팀이 1
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUseraname() {
        return useraname;
    }

    public void setUseraname(String useraname) {
        this.useraname = useraname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
