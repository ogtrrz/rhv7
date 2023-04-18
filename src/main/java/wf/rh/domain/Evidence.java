package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

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

    @NotNull
    @Size(max = 500)
    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "expiration")
    private Instant expiration;

    @Column(name = "link")
    private String link;

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
            ", description='" + getDescription() + "'" +
            ", expiration='" + getExpiration() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
}
