package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.StateToDo;

/**
 * A ToDo.
 */
@Entity
@Table(name = "to_do")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ToDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_2_employee")
    private Long id2Employee;

    @Column(name = "date")
    private Instant date;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateToDo state;

    @Column(name = "link")
    private String link;

    @ManyToMany(mappedBy = "todos")
    @JsonIgnoreProperties(value = { "managers", "trainings", "todos", "historicData", "employee", "jobs" }, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ToDo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Employee() {
        return this.id2Employee;
    }

    public ToDo id2Employee(Long id2Employee) {
        this.setId2Employee(id2Employee);
        return this;
    }

    public void setId2Employee(Long id2Employee) {
        this.id2Employee = id2Employee;
    }

    public Instant getDate() {
        return this.date;
    }

    public ToDo date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public ToDo description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateToDo getState() {
        return this.state;
    }

    public ToDo state(StateToDo state) {
        this.setState(state);
        return this;
    }

    public void setState(StateToDo state) {
        this.state = state;
    }

    public String getLink() {
        return this.link;
    }

    public ToDo link(String link) {
        this.setLink(link);
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.removeTodo(this));
        }
        if (employees != null) {
            employees.forEach(i -> i.addTodo(this));
        }
        this.employees = employees;
    }

    public ToDo employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public ToDo addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getTodos().add(this);
        return this;
    }

    public ToDo removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.getTodos().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToDo)) {
            return false;
        }
        return id != null && id.equals(((ToDo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ToDo{" +
            "id=" + getId() +
            ", id2Employee=" + getId2Employee() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", state='" + getState() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
}
