package login.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employe")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long empid;

    @Column(nullable = false)
    private String empName;
    @Column(unique = true)
    private String empMailid;


    private String role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;







}
