package app.peluargo.user.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
