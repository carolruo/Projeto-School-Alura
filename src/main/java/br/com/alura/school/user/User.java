package br.com.alura.school.user;

import br.com.alura.school.enroll.Enroll;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static br.com.alura.school.user.UserRole.STUDENT;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public
class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role = STUDENT;

    @OneToMany(mappedBy = "id.user")
    private Set<Enroll> enrolls = new HashSet<>();

    @Deprecated
    protected User() {}

    User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Set<Enroll> getEnrolls() {
        return enrolls;
    }

    public void addCourseEnroll(Enroll enroll) {
        this.enrolls.add(enroll);
    }

    String getUsername() {
        return username;
    }

    String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
