package wf.rh.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link wf.rh.domain.HistoricData} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistoricDataDTO implements Serializable {

    private Long id;

    private Long id2Employee;

    @NotNull
    @Size(max = 100)
    private String name;

    private String link;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof HistoricDataDTO)) {
            return false;
        }

        HistoricDataDTO historicDataDTO = (HistoricDataDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, historicDataDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoricDataDTO{" +
            "id=" + getId() +
            ", id2Employee=" + getId2Employee() +
            ", name='" + getName() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
}
