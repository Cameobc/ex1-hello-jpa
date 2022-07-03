package hellojpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")  set
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1
)
public class Member {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) : DB 너가 알아서 해줘
    //@GeneratedValue(strategy = GenerationType.SEQUENCE) : ORACLE?
   // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator") set
    @GeneratedValue(strategy = GenerationType.TABLE,
        generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name="name", nullable = false)
    private String useraname;

    public Member() {

    }

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
}
