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

    @Column(name = "extra_1")
    private String extra1;

    @Column(name = "extra_2")
    private String extra2;

    @Column(name = "extra_3")
    private String extra3;

    @Column(name = "extra_4")
    private String extra4;

    @Column(name = "extra_5")
    private String extra5;

    @Column(name = "extra_6")
    private String extra6;

    @Column(name = "extra_7")
    private String extra7;

    @Column(name = "extra_8")
    private String extra8;

    @Column(name = "extra_9")
    private String extra9;

    @Column(name = "extra_10")
    private String extra10;

    @Column(name = "created")
    private String created;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "edited")
    private String edited;

    @Column(name = "edited_at")
    private Instant editedAt;

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

    public String getExtra1() {
        return this.extra1;
    }

    public ToDo extra1(String extra1) {
        this.setExtra1(extra1);
        return this;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return this.extra2;
    }

    public ToDo extra2(String extra2) {
        this.setExtra2(extra2);
        return this;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return this.extra3;
    }

    public ToDo extra3(String extra3) {
        this.setExtra3(extra3);
        return this;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return this.extra4;
    }

    public ToDo extra4(String extra4) {
        this.setExtra4(extra4);
        return this;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return this.extra5;
    }

    public ToDo extra5(String extra5) {
        this.setExtra5(extra5);
        return this;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    public String getExtra6() {
        return this.extra6;
    }

    public ToDo extra6(String extra6) {
        this.setExtra6(extra6);
        return this;
    }

    public void setExtra6(String extra6) {
        this.extra6 = extra6;
    }

    public String getExtra7() {
        return this.extra7;
    }

    public ToDo extra7(String extra7) {
        this.setExtra7(extra7);
        return this;
    }

    public void setExtra7(String extra7) {
        this.extra7 = extra7;
    }

    public String getExtra8() {
        return this.extra8;
    }

    public ToDo extra8(String extra8) {
        this.setExtra8(extra8);
        return this;
    }

    public void setExtra8(String extra8) {
        this.extra8 = extra8;
    }

    public String getExtra9() {
        return this.extra9;
    }

    public ToDo extra9(String extra9) {
        this.setExtra9(extra9);
        return this;
    }

    public void setExtra9(String extra9) {
        this.extra9 = extra9;
    }

    public String getExtra10() {
        return this.extra10;
    }

    public ToDo extra10(String extra10) {
        this.setExtra10(extra10);
        return this;
    }

    public void setExtra10(String extra10) {
        this.extra10 = extra10;
    }

    public String getCreated() {
        return this.created;
    }

    public ToDo created(String created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public ToDo createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEdited() {
        return this.edited;
    }

    public ToDo edited(String edited) {
        this.setEdited(edited);
        return this;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Instant getEditedAt() {
        return this.editedAt;
    }

    public ToDo editedAt(Instant editedAt) {
        this.setEditedAt(editedAt);
        return this;
    }

    public void setEditedAt(Instant editedAt) {
        this.editedAt = editedAt;
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
            ", extra1='" + getExtra1() + "'" +
            ", extra2='" + getExtra2() + "'" +
            ", extra3='" + getExtra3() + "'" +
            ", extra4='" + getExtra4() + "'" +
            ", extra5='" + getExtra5() + "'" +
            ", extra6='" + getExtra6() + "'" +
            ", extra7='" + getExtra7() + "'" +
            ", extra8='" + getExtra8() + "'" +
            ", extra9='" + getExtra9() + "'" +
            ", extra10='" + getExtra10() + "'" +
            ", created='" + getCreated() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", edited='" + getEdited() + "'" +
            ", editedAt='" + getEditedAt() + "'" +
            "}";
    }
}
