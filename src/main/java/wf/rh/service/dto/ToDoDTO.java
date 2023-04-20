package wf.rh.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.StateToDo;

/**
 * A DTO for the {@link wf.rh.domain.ToDo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ToDoDTO implements Serializable {

    private Long id;

    private Long id2Employee;

    private Instant date;

    @NotNull
    @Size(max = 100)
    private String description;

    private StateToDo state;

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

    public Long getId2Employee() {
        return id2Employee;
    }

    public void setId2Employee(Long id2Employee) {
        this.id2Employee = id2Employee;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateToDo getState() {
        return state;
    }

    public void setState(StateToDo state) {
        this.state = state;
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
        if (!(o instanceof ToDoDTO)) {
            return false;
        }

        ToDoDTO toDoDTO = (ToDoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, toDoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ToDoDTO{" +
            "id=" + getId() +
            ", id2Employee=" + getId2Employee() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", state='" + getState() + "'" +
            ", link='" + getLink() + "'" +
            ", created='" + getCreated() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", edited='" + getEdited() + "'" +
            ", editedAt='" + getEditedAt() + "'" +
            "}";
    }
}
