package app.db;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignments")
@Data
public class AssignedCostume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "costumeid")
    private int costumeId;

    @Column(name = "assignmentchannel")
    private String assignmentChannel;

    @Column(name = "assignmentdate")
    private Date assignmentDate;

    @Column(name = "sold")
    private Boolean sold;
}
