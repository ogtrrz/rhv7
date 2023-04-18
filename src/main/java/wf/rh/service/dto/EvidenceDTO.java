package wf.rh.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link wf.rh.domain.Evidence} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvidenceDTO implements Serializable {

    private Long id;

    private Long id2Trining;

    private Long id2Requirents;

    @NotNull
    @Size(max = 500)
    private String description;

    private Instant expiration;

    private String link;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
            ", description='" + getDescription() + "'" +
            ", expiration='" + getExpiration() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
}
