package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.Kind;

/**
 * A Requirents.
 */
@Entity
@Table(name = "requirents")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Requirents implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_2_course")
    private Long id2Course;

    @NotNull
    @Size(max = 20)
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "expiration_in_month")
    private Integer expirationInMonth;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind")
    private Kind kind;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "requirents")
    @JsonIgnoreProperties(value = { "reqCourses", "trainings", "requirents", "course", "jobs" }, allowSetters = true)
    private Set<Course> codes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Requirents id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Course() {
        return this.id2Course;
    }

    public Requirents id2Course(Long id2Course) {
        this.setId2Course(id2Course);
        return this;
    }

    public void setId2Course(Long id2Course) {
        this.id2Course = id2Course;
    }

    public String getCode() {
        return this.code;
    }

    public Requirents code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getExpirationInMonth() {
        return this.expirationInMonth;
    }

    public Requirents expirationInMonth(Integer expirationInMonth) {
        this.setExpirationInMonth(expirationInMonth);
        return this;
    }

    public void setExpirationInMonth(Integer expirationInMonth) {
        this.expirationInMonth = expirationInMonth;
    }

    public Kind getKind() {
        return this.kind;
    }

    public Requirents kind(Kind kind) {
        this.setKind(kind);
        return this;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return this.description;
    }

    public Requirents description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Course> getCodes() {
        return this.codes;
    }

    public void setCodes(Set<Course> courses) {
        if (this.codes != null) {
            this.codes.forEach(i -> i.removeRequirents(this));
        }
        if (courses != null) {
            courses.forEach(i -> i.addRequirents(this));
        }
        this.codes = courses;
    }

    public Requirents codes(Set<Course> courses) {
        this.setCodes(courses);
        return this;
    }

    public Requirents addCode(Course course) {
        this.codes.add(course);
        course.getRequirents().add(this);
        return this;
    }

    public Requirents removeCode(Course course) {
        this.codes.remove(course);
        course.getRequirents().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Requirents)) {
            return false;
        }
        return id != null && id.equals(((Requirents) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Requirents{" +
            "id=" + getId() +
            ", id2Course=" + getId2Course() +
            ", code='" + getCode() + "'" +
            ", expirationInMonth=" + getExpirationInMonth() +
            ", kind='" + getKind() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
