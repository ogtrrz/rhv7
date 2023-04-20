package wf.rh.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.Kind;
import wf.rh.domain.enumeration.StateToDo;

/**
 * A DTO for the {@link wf.rh.domain.Evidence} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvidenceDTO implements Serializable {

    private Long id;

    private Long id2Trining;

    private Long id2Requirents;

    private Long id2Course;

    private Long id2Employee;

    private StateToDo state;

    private Kind kind;

    @NotNull
    @Size(max = 500)
    private String description;

    @Size(max = 500)
    private String note;

    private Instant expiration;

    private String link;

    private String created;

    private Instant createdAt;

    private String edited;

    private Instant editedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Trining() {
        return id2Trining;
    }

    public void setId2Trining(Long id2Trining) {
        this.id2Trining = id2Trining;
    }

    public Long getId2Requirents() {
        return id2Requirents;
    }

    public void setId2Requirents(Long id2Requirents) {
        this.id2Requirents = id2Requirents;
    }

    public Long getId2Course() {
        return id2Course;
    }

    public void setId2Course(Long id2Course) {
        this.id2Course = id2Course;
    }

    public Long getId2Employee() {
        return id2Employee;
    }

    public void setId2Employee(Long id2Employee) {
        this.id2Employee = id2Employee;
    }

    public StateToDo getState() {
        return state;
    }

    public void setState(StateToDo state) {
        this.state = state;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvidenceDTO)) {
            return false;
        }

        EvidenceDTO evidenceDTO = (EvidenceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, evidenceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvidenceDTO{" +
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
