package wf.rh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.Kind;

/**
 * A Requirents.
 */
@Entity
@Table(name = "requirents")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Requirents implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_2_course")
    private Long id2Course;

    @NotNull
    @Size(max = 20)
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "expiration_in_month")
    private Integer expirationInMonth;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind")
    private Kind kind;

    @Column(name = "description")
    private String description;

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

    @ManyToMany(mappedBy = "requirents")
    @JsonIgnoreProperties(value = { "reqCourses", "trainings", "requirents", "course", "jobs" }, allowSetters = true)
    private Set<Course> codes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Requirents id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2Course() {
        return this.id2Course;
    }

    public Requirents id2Course(Long id2Course) {
        this.setId2Course(id2Course);
        return this;
    }

    public void setId2Course(Long id2Course) {
        this.id2Course = id2Course;
    }

    public String getCode() {
        return this.code;
    }

    public Requirents code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getExpirationInMonth() {
        return this.expirationInMonth;
    }

    public Requirents expirationInMonth(Integer expirationInMonth) {
        this.setExpirationInMonth(expirationInMonth);
        return this;
    }

    public void setExpirationInMonth(Integer expirationInMonth) {
        this.expirationInMonth = expirationInMonth;
    }

    public Kind getKind() {
        return this.kind;
    }

    public Requirents kind(Kind kind) {
        this.setKind(kind);
        return this;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return this.description;
    }

    public Requirents description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtra1() {
        return this.extra1;
    }

    public Requirents extra1(String extra1) {
        this.setExtra1(extra1);
        return this;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return this.extra2;
    }

    public Requirents extra2(String extra2) {
        this.setExtra2(extra2);
        return this;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return this.extra3;
    }

    public Requirents extra3(String extra3) {
        this.setExtra3(extra3);
        return this;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return this.extra4;
    }

    public Requirents extra4(String extra4) {
        this.setExtra4(extra4);
        return this;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return this.extra5;
    }

    public Requirents extra5(String extra5) {
        this.setExtra5(extra5);
        return this;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    public String getExtra6() {
        return this.extra6;
    }

    public Requirents extra6(String extra6) {
        this.setExtra6(extra6);
        return this;
    }

    public void setExtra6(String extra6) {
        this.extra6 = extra6;
    }

    public String getExtra7() {
        return this.extra7;
    }

    public Requirents extra7(String extra7) {
        this.setExtra7(extra7);
        return this;
    }

    public void setExtra7(String extra7) {
        this.extra7 = extra7;
    }

    public String getExtra8() {
        return this.extra8;
    }

    public Requirents extra8(String extra8) {
        this.setExtra8(extra8);
        return this;
    }

    public void setExtra8(String extra8) {
        this.extra8 = extra8;
    }

    public String getExtra9() {
        return this.extra9;
    }

    public Requirents extra9(String extra9) {
        this.setExtra9(extra9);
        return this;
    }

    public void setExtra9(String extra9) {
        this.extra9 = extra9;
    }

    public String getExtra10() {
        return this.extra10;
    }

    public Requirents extra10(String extra10) {
        this.setExtra10(extra10);
        return this;
    }

    public void setExtra10(String extra10) {
        this.extra10 = extra10;
    }

    public String getCreated() {
        return this.created;
    }

    public Requirents created(String created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Requirents createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEdited() {
        return this.edited;
    }

    public Requirents edited(String edited) {
        this.setEdited(edited);
        return this;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Instant getEditedAt() {
        return this.editedAt;
    }

    public Requirents editedAt(Instant editedAt) {
        this.setEditedAt(editedAt);
        return this;
    }

    public void setEditedAt(Instant editedAt) {
        this.editedAt = editedAt;
    }

    public Set<Course> getCodes() {
        return this.codes;
    }

    public void setCodes(Set<Course> courses) {
        if (this.codes != null) {
            this.codes.forEach(i -> i.removeRequirents(this));
        }
        if (courses != null) {
            courses.forEach(i -> i.addRequirents(this));
        }
        this.codes = courses;
    }

    public Requirents codes(Set<Course> courses) {
        this.setCodes(courses);
        return this;
    }

    public Requirents addCode(Course course) {
        this.codes.add(course);
        course.getRequirents().add(this);
        return this;
    }

    public Requirents removeCode(Course course) {
        this.codes.remove(course);
        course.getRequirents().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Requirents)) {
            return false;
        }
        return id != null && id.equals(((Requirents) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Requirents{" +
            "id=" + getId() +
            ", id2Course=" + getId2Course() +
            ", code='" + getCode() + "'" +
            ", expirationInMonth=" + getExpirationInMonth() +
            ", kind='" + getKind() + "'" +
            ", description='" + getDescription() + "'" +
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
