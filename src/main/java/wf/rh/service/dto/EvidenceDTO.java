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

    private String extra1;

    private String extra2;

    private String extra3;

    private String extra4;

    private String extra5;

    private String extra6;

    private String extra7;

    private String extra8;

    private String extra9;

    private String extra10;

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

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    public String getExtra6() {
        return extra6;
    }

    public void setExtra6(String extra6) {
        this.extra6 = extra6;
    }

    public String getExtra7() {
        return extra7;
    }

    public void setExtra7(String extra7) {
        this.extra7 = extra7;
    }

    public String getExtra8() {
        return extra8;
    }

    public void setExtra8(String extra8) {
        this.extra8 = extra8;
    }

    public String getExtra9() {
        return extra9;
    }

    public void setExtra9(String extra9) {
        this.extra9 = extra9;
    }

    public String getExtra10() {
        return extra10;
    }

    public void setExtra10(String extra10) {
        this.extra10 = extra10;
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
