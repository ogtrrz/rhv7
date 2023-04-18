package wf.rh.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.Handling;
import wf.rh.domain.enumeration.Rol;

/**
 * A DTO for the {@link wf.rh.domain.Job} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JobDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String jobTitle;

    private Rol rol;

    private Handling handling;

    private Set<CourseDTO> courses = new HashSet<>();

    private Set<EmployeeDTO> employees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Handling getHandling() {
        return handling;
    }

    public void setHandling(Handling handling) {
        this.handling = handling;
    }

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobDTO)) {
            return false;
        }

        JobDTO jobDTO = (JobDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, jobDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobDTO{" +
            "id=" + getId() +
            ", jobTitle='" + getJobTitle() + "'" +
            ", rol='" + getRol() + "'" +
            ", handling='" + getHandling() + "'" +
            ", courses=" + getCourses() +
            ", employees=" + getEmployees() +
            "}";
    }
}
