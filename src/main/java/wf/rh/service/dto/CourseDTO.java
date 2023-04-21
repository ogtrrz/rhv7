package wf.rh.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;
import wf.rh.domain.enumeration.TypeCourse;

/**
 * A DTO for the {@link wf.rh.domain.Course} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CourseDTO implements Serializable {

    private Long id;

    private Long id2Job;

    @NotNull
    @Size(max = 20)
    private String code;

    @NotNull
    @Size(max = 100)
    private String name;

    private Integer expirationInMonth;

    private TypeCourse typeCourse;

    @Size(max = 100)
    private String autorizationBy;

    private Integer durationAuthorizationInMonth;

    @Size(max = 500)
    private String description;

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

    private Set<TrainingDTO> trainings = new HashSet<>();

    private Set<RequirentsDTO> requirents = new HashSet<>();

    private CourseDTO course;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExpirationInMonth() {
        return expirationInMonth;
    }

    public void setExpirationInMonth(Integer expirationInMonth) {
        this.expirationInMonth = expirationInMonth;
    }

    public TypeCourse getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(TypeCourse typeCourse) {
        this.typeCourse = typeCourse;
    }

    public String getAutorizationBy() {
        return autorizationBy;
    }

    public void setAutorizationBy(String autorizationBy) {
        this.autorizationBy = autorizationBy;
    }

    public Integer getDurationAuthorizationInMonth() {
        return durationAuthorizationInMonth;
    }

    public void setDurationAuthorizationInMonth(Integer durationAuthorizationInMonth) {
        this.durationAuthorizationInMonth = durationAuthorizationInMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<TrainingDTO> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<TrainingDTO> trainings) {
        this.trainings = trainings;
    }

    public Set<RequirentsDTO> getRequirents() {
        return requirents;
    }

    public void setRequirents(Set<RequirentsDTO> requirents) {
        this.requirents = requirents;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseDTO)) {
            return false;
        }

        CourseDTO courseDTO = (CourseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, courseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CourseDTO{" +
            "id=" + getId() +
            ", id2Job=" + getId2Job() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", expirationInMonth=" + getExpirationInMonth() +
            ", typeCourse='" + getTypeCourse() + "'" +
            ", autorizationBy='" + getAutorizationBy() + "'" +
            ", durationAuthorizationInMonth=" + getDurationAuthorizationInMonth() +
            ", description='" + getDescription() + "'" +
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
            ", trainings=" + getTrainings() +
            ", requirents=" + getRequirents() +
            ", course=" + getCourse() +
            "}";
    }
}
