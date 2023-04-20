package wf.rh.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import wf.rh.domain.Course;
import wf.rh.domain.Requirents;
import wf.rh.domain.Training;
import wf.rh.service.dto.CourseDTO;
import wf.rh.service.dto.RequirentsDTO;
import wf.rh.service.dto.TrainingDTO;

/**
 * Mapper for the entity {@link Course} and its DTO {@link CourseDTO}.
 */
@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {
    @Mapping(target = "trainings", source = "trainings", qualifiedByName = "trainingCodeSet")
    @Mapping(target = "requirents", source = "requirents", qualifiedByName = "requirentsCodeSet")
    @Mapping(target = "course", source = "course", qualifiedByName = "courseId")
    CourseDTO toDto(Course s);

    @Mapping(target = "removeTraining", ignore = true)
    @Mapping(target = "removeRequirents", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    @Named("courseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CourseDTO toDtoCourseId(Course course);

    @Named("trainingCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TrainingDTO toDtoTrainingCode(Training training);

    @Named("trainingCodeSet")
    default Set<TrainingDTO> toDtoTrainingCodeSet(Set<Training> training) {
        return training.stream().map(this::toDtoTrainingCode).collect(Collectors.toSet());
    }

    @Named("requirentsCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    RequirentsDTO toDtoRequirentsCode(Requirents requirents);

    @Named("requirentsCodeSet")
    default Set<RequirentsDTO> toDtoRequirentsCodeSet(Set<Requirents> requirents) {
        return requirents.stream().map(this::toDtoRequirentsCode).collect(Collectors.toSet());
    }
}
