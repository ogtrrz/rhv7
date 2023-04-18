package wf.rh.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.Kind;

/**
 * A DTO for the {@link wf.rh.domain.Requirents} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequirentsDTO implements Serializable {

    private Long id;

    private Long id2Course;

    @NotNull
    @Size(max = 20)
    private String code;

    private Integer expirationInMonth;

    private Kind kind;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Course() {
        return id2Course;
    }

    public void setId2Course(Long id2Course) {
        this.id2Course = id2Course;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getExpirationInMonth() {
        return expirationInMonth;
    }

    public void setExpirationInMonth(Integer expirationInMonth) {
        this.expirationInMonth = expirationInMonth;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequirentsDTO)) {
            return false;
        }

        RequirentsDTO requirentsDTO = (RequirentsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, requirentsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequirentsDTO{" +
            "id=" + getId() +
            ", id2Course=" + getId2Course() +
            ", code='" + getCode() + "'" +
            ", expirationInMonth=" + getExpirationInMonth() +
            ", kind='" + getKind() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
