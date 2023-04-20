package wf.rh.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link wf.rh.domain.Employee} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmployeeDTO implements Serializable {

    private Long id;

    private Long id2Job;

    @NotNull
    @Size(max = 100)
    private String user;

    @NotNull
    @Size(max = 100)
    private String firstName;

    @NotNull
    @Size(max = 100)
    private String lastName;

    private String email;

    private String phoneNumber;

    private Instant hireDate;

    @Size(max = 100)
    private String emergencyContact;

    private String emergencyPhone;

    @Size(max = 2)
    private String blodeType;

    @Size(max = 500)
    private String allergies;

    private Instant birthDate;

    @Size(max = 2000)
    private String note;

    private String created;

    private Instant createdAt;

    private String edited;

    private Instant editedAt;

    private Set<TrainingDTO> trainings = new HashSet<>();

    private Set<ToDoDTO> todos = new HashSet<>();

    private Set<HistoricDataDTO> historicData = new HashSet<>();

    private EmployeeDTO employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Job() {
        return id2Job;
    }

    public void setId2Job(Long id2Job) {
        this.id2Job = id2Job;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getBlodeType() {
        return blodeType;
    }

    public void setBlodeType(String blodeType) {
        this.blodeType = blodeType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Instant getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Instant editedAt) {
        this.editedAt = editedAt;
    }

    public Set<TrainingDTO> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<TrainingDTO> trainings) {
        this.trainings = trainings;
    }

    public Set<ToDoDTO> getTodos() {
        return todos;
    }

    public void setTodos(Set<ToDoDTO> todos) {
        this.todos = todos;
    }

    public Set<HistoricDataDTO> getHistoricData() {
        return historicData;
    }

    public void setHistoricData(Set<HistoricDataDTO> historicData) {
        this.historicData = historicData;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeDTO)) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, employeeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeDTO{" +
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
            ", created='" + getCreated() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", edited='" + getEdited() + "'" +
            ", editedAt='" + getEditedAt() + "'" +
            ", trainings=" + getTrainings() +
            ", todos=" + getTodos() +
            ", historicData=" + getHistoricData() +
            ", employee=" + getEmployee() +
            "}";
    }
}
