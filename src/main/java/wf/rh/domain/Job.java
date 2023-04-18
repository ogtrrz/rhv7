package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.Handling;
import wf.rh.domain.enumeration.Rol;

/**
 * A Job.
 */
@Entity
@Table(name = "job")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "job_title", length = 100, nullable = false)
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "handling")
    private Handling handling;

    @ManyToMany
    @JoinTable(name = "rel_job__course", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnoreProperties(value = { "reqCourses", "trainings", "requirents", "course", "jobs" }, allowSetters = true)
    private Set<Course> courses = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_job__employee",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @JsonIgnoreProperties(value = { "managers", "trainings", "todos", "historicData", "employee", "jobs" }, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Job id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public Job jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Rol getRol() {
        return this.rol;
    }

    public Job rol(Rol rol) {
        this.setRol(rol);
        return this;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Handling getHandling() {
        return this.handling;
    }

    public Job handling(Handling handling) {
        this.setHandling(handling);
        return this;
    }

    public void setHandling(Handling handling) {
        this.handling = handling;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Job courses(Set<Course> courses) {
        this.setCourses(courses);
        return this;
    }

    public Job addCourse(Course course) {
        this.courses.add(course);
        course.getJobs().add(this);
        return this;
    }

    public Job removeCourse(Course course) {
        this.courses.remove(course);
        course.getJobs().remove(this);
        return this;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Job employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public Job addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getJobs().add(this);
        return this;
    }

    public Job removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.getJobs().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        return id != null && id.equals(((Job) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Job{" +
            "id=" + getId() +
            ", jobTitle='" + getJobTitle() + "'" +
            ", rol='" + getRol() + "'" +
            ", handling='" + getHandling() + "'" +
            "}";
    }
}
