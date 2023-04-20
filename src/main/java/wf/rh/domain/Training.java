package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Training.
 */
@Entity
@Table(name = "training")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_2_course")
    private Long id2Course;

    @Column(name = "id_2_employee")
    private Long id2Employee;

    @NotNull
    @Size(max = 20)
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "date")
    private Instant date;

    @Column(name = "expiry")
    private Instant expiry;

    @Column(name = "created")
    private String created;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "edited")
    private String edited;

    @Column(name = "edited_at")
    private Instant editedAt;

    @ManyToMany
    @JoinTable(
        name = "rel_training__evidence",
        joinColumns = @JoinColumn(name = "training_id"),
        inverseJoinColumns = @JoinColumn(name = "evidence_id")
    )
    @JsonIgnoreProperties(value = { "trainings" }, allowSetters = true)
    private Set<Evidence> evidences = new HashSet<>();

    @ManyToMany(mappedBy = "trainings")
    @JsonIgnoreProperties(value = { "reqCourses", "trainings", "requirents", "course", "jobs" }, allowSetters = true)
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(mappedBy = "trainings")
    @JsonIgnoreProperties(value = { "managers", "trainings", "todos", "historicData", "employee", "jobs" }, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Training id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Course() {
        return this.id2Course;
    }

    public Training id2Course(Long id2Course) {
        this.setId2Course(id2Course);
        return this;
    }

    public void setId2Course(Long id2Course) {
        this.id2Course = id2Course;
    }

    public Long getId2Employee() {
        return this.id2Employee;
    }

    public Training id2Employee(Long id2Employee) {
        this.setId2Employee(id2Employee);
        return this;
    }

    public void setId2Employee(Long id2Employee) {
        this.id2Employee = id2Employee;
    }

    public String getCode() {
        return this.code;
    }

    public Training code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDate() {
        return this.date;
    }

    public Training date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Instant getExpiry() {
        return this.expiry;
    }

    public Training expiry(Instant expiry) {
        this.setExpiry(expiry);
        return this;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }

    public String getCreated() {
        return this.created;
    }

    public Training created(String created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Training createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEdited() {
        return this.edited;
    }

    public Training edited(String edited) {
        this.setEdited(edited);
        return this;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Instant getEditedAt() {
        return this.editedAt;
    }

    public Training editedAt(Instant editedAt) {
        this.setEditedAt(editedAt);
        return this;
    }

    public void setEditedAt(Instant editedAt) {
        this.editedAt = editedAt;
    }

    public Set<Evidence> getEvidences() {
        return this.evidences;
    }

    public void setEvidences(Set<Evidence> evidences) {
        this.evidences = evidences;
    }

    public Training evidences(Set<Evidence> evidences) {
        this.setEvidences(evidences);
        return this;
    }

    public Training addEvidence(Evidence evidence) {
        this.evidences.add(evidence);
        evidence.getTrainings().add(this);
        return this;
    }

    public Training removeEvidence(Evidence evidence) {
        this.evidences.remove(evidence);
        evidence.getTrainings().remove(this);
        return this;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        if (this.courses != null) {
            this.courses.forEach(i -> i.removeTraining(this));
        }
        if (courses != null) {
            courses.forEach(i -> i.addTraining(this));
        }
        this.courses = courses;
    }

    public Training courses(Set<Course> courses) {
        this.setCourses(courses);
        return this;
    }

    public Training addCourse(Course course) {
        this.courses.add(course);
        course.getTrainings().add(this);
        return this;
    }

    public Training removeCourse(Course course) {
        this.courses.remove(course);
        course.getTrainings().remove(this);
        return this;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.removeTraining(this));
        }
        if (employees != null) {
            employees.forEach(i -> i.addTraining(this));
        }
        this.employees = employees;
    }

    public Training employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public Training addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getTrainings().add(this);
        return this;
    }

    public Training removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.getTrainings().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Training)) {
            return false;
        }
        return id != null && id.equals(((Training) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Training{" +
            "id=" + getId() +
            ", id2Course=" + getId2Course() +
            ", id2Employee=" + getId2Employee() +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", expiry='" + getExpiry() + "'" +
            ", created='" + getCreated() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", edited='" + getEdited() + "'" +
            ", editedAt='" + getEditedAt() + "'" +
            "}";
    }
}
