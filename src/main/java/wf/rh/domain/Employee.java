package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_2_job")
    private Long id2Job;

    @NotNull
    @Size(max = 100)
    @Column(name = "jhi_user", length = 100, nullable = false)
    private String user;

    @NotNull
    @Size(max = 100)
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private Instant hireDate;

    @Size(max = 100)
    @Column(name = "emergency_contact", length = 100)
    private String emergencyContact;

    @Column(name = "emergency_phone")
    private String emergencyPhone;

    @Size(max = 2)
    @Column(name = "blode_type", length = 2)
    private String blodeType;

    @Size(max = 500)
    @Column(name = "allergies", length = 500)
    private String allergies;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Size(max = 2000)
    @Column(name = "note", length = 2000)
    private String note;

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

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties(value = { "managers", "trainings", "todos", "historicData", "employee", "jobs" }, allowSetters = true)
    private Set<Employee> managers = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_employee__training",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    @JsonIgnoreProperties(value = { "evidences", "courses", "employees" }, allowSetters = true)
    private Set<Training> trainings = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_employee__todo",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "todo_id")
    )
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private Set<ToDo> todos = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_employee__historic_data",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "historic_data_id")
    )
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private Set<HistoricData> historicData = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "managers", "trainings", "todos", "historicData", "employee", "jobs" }, allowSetters = true)
    private Employee employee;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnoreProperties(value = { "courses", "employees" }, allowSetters = true)
    private Set<Job> jobs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Job() {
        return this.id2Job;
    }

    public Employee id2Job(Long id2Job) {
        this.setId2Job(id2Job);
        return this;
    }

    public void setId2Job(Long id2Job) {
        this.id2Job = id2Job;
    }

    public String getUser() {
        return this.user;
    }

    public Employee user(String user) {
        this.setUser(user);
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Employee firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Employee lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Employee email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Employee phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getHireDate() {
        return this.hireDate;
    }

    public Employee hireDate(Instant hireDate) {
        this.setHireDate(hireDate);
        return this;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmergencyContact() {
        return this.emergencyContact;
    }

    public Employee emergencyContact(String emergencyContact) {
        this.setEmergencyContact(emergencyContact);
        return this;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return this.emergencyPhone;
    }

    public Employee emergencyPhone(String emergencyPhone) {
        this.setEmergencyPhone(emergencyPhone);
        return this;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getBlodeType() {
        return this.blodeType;
    }

    public Employee blodeType(String blodeType) {
        this.setBlodeType(blodeType);
        return this;
    }

    public void setBlodeType(String blodeType) {
        this.blodeType = blodeType;
    }

    public String getAllergies() {
        return this.allergies;
    }

    public Employee allergies(String allergies) {
        this.setAllergies(allergies);
        return this;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Instant getBirthDate() {
        return this.birthDate;
    }

    public Employee birthDate(Instant birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getNote() {
        return this.note;
    }

    public Employee note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getExtra1() {
        return this.extra1;
    }

    public Employee extra1(String extra1) {
        this.setExtra1(extra1);
        return this;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return this.extra2;
    }

    public Employee extra2(String extra2) {
        this.setExtra2(extra2);
        return this;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return this.extra3;
    }

    public Employee extra3(String extra3) {
        this.setExtra3(extra3);
        return this;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return this.extra4;
    }

    public Employee extra4(String extra4) {
        this.setExtra4(extra4);
        return this;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return this.extra5;
    }

    public Employee extra5(String extra5) {
        this.setExtra5(extra5);
        return this;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    public String getExtra6() {
        return this.extra6;
    }

    public Employee extra6(String extra6) {
        this.setExtra6(extra6);
        return this;
    }

    public void setExtra6(String extra6) {
        this.extra6 = extra6;
    }

    public String getExtra7() {
        return this.extra7;
    }

    public Employee extra7(String extra7) {
        this.setExtra7(extra7);
        return this;
    }

    public void setExtra7(String extra7) {
        this.extra7 = extra7;
    }

    public String getExtra8() {
        return this.extra8;
    }

    public Employee extra8(String extra8) {
        this.setExtra8(extra8);
        return this;
    }

    public void setExtra8(String extra8) {
        this.extra8 = extra8;
    }

    public String getExtra9() {
        return this.extra9;
    }

    public Employee extra9(String extra9) {
        this.setExtra9(extra9);
        return this;
    }

    public void setExtra9(String extra9) {
        this.extra9 = extra9;
    }

    public String getExtra10() {
        return this.extra10;
    }

    public Employee extra10(String extra10) {
        this.setExtra10(extra10);
        return this;
    }

    public void setExtra10(String extra10) {
        this.extra10 = extra10;
    }

    public String getCreated() {
        return this.created;
    }

    public Employee created(String created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Employee createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEdited() {
        return this.edited;
    }

    public Employee edited(String edited) {
        this.setEdited(edited);
        return this;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Instant getEditedAt() {
        return this.editedAt;
    }

    public Employee editedAt(Instant editedAt) {
        this.setEditedAt(editedAt);
        return this;
    }

    public void setEditedAt(Instant editedAt) {
        this.editedAt = editedAt;
    }

    public Set<Employee> getManagers() {
        return this.managers;
    }

    public void setManagers(Set<Employee> employees) {
        if (this.managers != null) {
            this.managers.forEach(i -> i.setEmployee(null));
        }
        if (employees != null) {
            employees.forEach(i -> i.setEmployee(this));
        }
        this.managers = employees;
    }

    public Employee managers(Set<Employee> employees) {
        this.setManagers(employees);
        return this;
    }

    public Employee addManager(Employee employee) {
        this.managers.add(employee);
        employee.setEmployee(this);
        return this;
    }

    public Employee removeManager(Employee employee) {
        this.managers.remove(employee);
        employee.setEmployee(null);
        return this;
    }

    public Set<Training> getTrainings() {
        return this.trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public Employee trainings(Set<Training> trainings) {
        this.setTrainings(trainings);
        return this;
    }

    public Employee addTraining(Training training) {
        this.trainings.add(training);
        training.getEmployees().add(this);
        return this;
    }

    public Employee removeTraining(Training training) {
        this.trainings.remove(training);
        training.getEmployees().remove(this);
        return this;
    }

    public Set<ToDo> getTodos() {
        return this.todos;
    }

    public void setTodos(Set<ToDo> toDos) {
        this.todos = toDos;
    }

    public Employee todos(Set<ToDo> toDos) {
        this.setTodos(toDos);
        return this;
    }

    public Employee addTodo(ToDo toDo) {
        this.todos.add(toDo);
        toDo.getEmployees().add(this);
        return this;
    }

    public Employee removeTodo(ToDo toDo) {
        this.todos.remove(toDo);
        toDo.getEmployees().remove(this);
        return this;
    }

    public Set<HistoricData> getHistoricData() {
        return this.historicData;
    }

    public void setHistoricData(Set<HistoricData> historicData) {
        this.historicData = historicData;
    }

    public Employee historicData(Set<HistoricData> historicData) {
        this.setHistoricData(historicData);
        return this;
    }

    public Employee addHistoricData(HistoricData historicData) {
        this.historicData.add(historicData);
        historicData.getEmployees().add(this);
        return this;
    }

    public Employee removeHistoricData(HistoricData historicData) {
        this.historicData.remove(historicData);
        historicData.getEmployees().remove(this);
        return this;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        if (this.jobs != null) {
            this.jobs.forEach(i -> i.removeEmployee(this));
        }
        if (jobs != null) {
            jobs.forEach(i -> i.addEmployee(this));
        }
        this.jobs = jobs;
    }

    public Employee jobs(Set<Job> jobs) {
        this.setJobs(jobs);
        return this;
    }

    public Employee addJob(Job job) {
        this.jobs.add(job);
        job.getEmployees().add(this);
        return this;
    }

    public Employee removeJob(Job job) {
        this.jobs.remove(job);
        job.getEmployees().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", id2Job=" + getId2Job() +
            ", user='" + getUser() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", emergencyContact='" + getEmergencyContact() + "'" +
            ", emergencyPhone='" + getEmergencyPhone() + "'" +
            ", blodeType='" + getBlodeType() + "'" +
            ", allergies='" + getAllergies() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", note='" + getNote() + "'" +
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
