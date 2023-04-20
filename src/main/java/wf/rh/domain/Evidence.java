package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.Kind;
import wf.rh.domain.enumeration.StateToDo;

/**
 * A Evidence.
 */
@Entity
@Table(name = "evidence")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Evidence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_2_trining")
    private Long id2Trining;

    @Column(name = "id_2_requirents")
    private Long id2Requirents;

    @Column(name = "id_2_course")
    private Long id2Course;

    @Column(name = "id_2_employee")
    private Long id2Employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateToDo state;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind")
    private Kind kind;

    @NotNull
    @Size(max = 500)
    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Size(max = 500)
    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "expiration")
    private Instant expiration;

    @Column(name = "link")
    private String link;

    @Column(name = "created")
    private String created;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "edited")
    private String edited;

    @Column(name = "edited_at")
    private Instant editedAt;

    @ManyToMany(mappedBy = "evidences")
    @JsonIgnoreProperties(value = { "evidences", "courses", "employees" }, allowSetters = true)
    private Set<Training> trainings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Evidence id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Trining() {
        return this.id2Trining;
    }

    public Evidence id2Trining(Long id2Trining) {
        this.setId2Trining(id2Trining);
        return this;
    }

    public void setId2Trining(Long id2Trining) {
        this.id2Trining = id2Trining;
    }

    public Long getId2Requirents() {
        return this.id2Requirents;
    }

    public Evidence id2Requirents(Long id2Requirents) {
        this.setId2Requirents(id2Requirents);
        return this;
    }

    public void setId2Requirents(Long id2Requirents) {
        this.id2Requirents = id2Requirents;
    }

    public Long getId2Course() {
        return this.id2Course;
    }

    public Evidence id2Course(Long id2Course) {
        this.setId2Course(id2Course);
        return this;
    }

    public void setId2Course(Long id2Course) {
        this.id2Course = id2Course;
    }

    public Long getId2Employee() {
        return this.id2Employee;
    }

    public Evidence id2Employee(Long id2Employee) {
        this.setId2Employee(id2Employee);
        return this;
    }

    public void setId2Employee(Long id2Employee) {
        this.id2Employee = id2Employee;
    }

    public StateToDo getState() {
        return this.state;
    }

    public Evidence state(StateToDo state) {
        this.setState(state);
        return this;
    }

    public void setState(StateToDo state) {
        this.state = state;
    }

    public Kind getKind() {
        return this.kind;
    }

    public Evidence kind(Kind kind) {
        this.setKind(kind);
        return this;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return this.description;
    }

    public Evidence description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return this.note;
    }

    public Evidence note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Instant getExpiration() {
        return this.expiration;
    }

    public Evidence expiration(Instant expiration) {
        this.setExpiration(expiration);
        return this;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public String getLink() {
        return this.link;
    }

    public Evidence link(String link) {
        this.setLink(link);
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreated() {
        return this.created;
    }

    public Evidence created(String created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Evidence createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEdited() {
        return this.edited;
    }

    public Evidence edited(String edited) {
        this.setEdited(edited);
        return this;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Instant getEditedAt() {
        return this.editedAt;
    }

    public Evidence editedAt(Instant editedAt) {
        this.setEditedAt(editedAt);
        return this;
    }

    public void setEditedAt(Instant editedAt) {
        this.editedAt = editedAt;
    }

    public Set<Training> getTrainings() {
        return this.trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        if (this.trainings != null) {
            this.trainings.forEach(i -> i.removeEvidence(this));
        }
        if (trainings != null) {
            trainings.forEach(i -> i.addEvidence(this));
        }
        this.trainings = trainings;
    }

    public Evidence trainings(Set<Training> trainings) {
        this.setTrainings(trainings);
        return this;
    }

    public Evidence addTraining(Training training) {
        this.trainings.add(training);
        training.getEvidences().add(this);
        return this;
    }

    public Evidence removeTraining(Training training) {
        this.trainings.remove(training);
        training.getEvidences().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evidence)) {
            return false;
        }
        return id != null && id.equals(((Evidence) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Evidence{" +
            "id=" + getId() +
            ", id2Trining=" + getId2Trining() +
            ", id2Requirents=" + getId2Requirents() +
            ", id2Course=" + getId2Course() +
            ", id2Employee=" + getId2Employee() +
            ", state='" + getState() + "'" +
            ", kind='" + getKind() + "'" +
            ", description='" + getDescription() + "'" +
            ", note='" + getNote() + "'" +
            ", expiration='" + getExpiration() + "'" +
            ", link='" + getLink() + "'" +
            ", created='" + getCreated() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", edited='" + getEdited() + "'" +
            ", editedAt='" + getEditedAt() + "'" +
            "}";
    }
}
