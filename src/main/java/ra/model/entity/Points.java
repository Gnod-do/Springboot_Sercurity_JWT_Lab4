package ra.model.entity;

//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Points")
@Data
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PointId")
    private int pointId;

    @Column
    private Double x;

    @Column
    private Double y;

    @Column
    private Double r;

    @Column
    private String result;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId")
    private Users user;

}
