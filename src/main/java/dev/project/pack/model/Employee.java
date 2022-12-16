package dev.project.pack.model;

import dev.project.pack.annotation.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "data_employee",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "email"
                }
        )
)
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "First name is compulsary!")
    private String firstName;

    private String lastName;

    @Column(
            nullable = false,
            length = 30,
            unique = true
    )
    private String email;

    @NotEmpty(message = "Age is compulsary!")
    private Integer age;

//    @Column(name = "date_create",nullable = false)
//    @Temporal(TemporalType.DATE)
//    private Date createDate;

    public Employee(String firstName,
                    String lastName,
                    String email,
                    Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
}
