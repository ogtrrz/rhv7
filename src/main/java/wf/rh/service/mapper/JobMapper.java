package wf.rh.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import wf.rh.domain.Course;
import wf.rh.domain.Employee;
import wf.rh.domain.Job;
import wf.rh.service.dto.CourseDTO;
import wf.rh.service.dto.EmployeeDTO;
import wf.rh.service.dto.JobDTO;

/**
 * Mapper for the entity {@link Job} and its DTO {@link JobDTO}.
 */
@Mapper(componentModel = "spring")
public interface JobMapper extends EntityMapper<JobDTO, Job> {
    @Mapping(target = "courses", source = "courses", qualifiedByName = "courseCodeSet")
    @Mapping(target = "employees", source = "employees", qualifiedByName = "employeeUserSet")
    JobDTO toDto(Job s);

    @Mapping(target = "removeCourse", ignore = true)
    @Mapping(target = "removeEmployee", ignore = true)
    Job toEntity(JobDTO jobDTO);

    @Named("courseCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    CourseDTO toDtoCourseCode(Course course);

    @Named("courseCodeSet")
    default Set<CourseDTO> toDtoCourseCodeSet(Set<Course> course) {
        return course.stream().map(this::toDtoCourseCode).collect(Collectors.toSet());
    }

    @Named("employeeUser")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", source = "user")
    EmployeeDTO toDtoEmployeeUser(Employee employee);

    @Named("employeeUserSet")
    default Set<EmployeeDTO> toDtoEmployeeUserSet(Set<Employee> employee) {
        return employee.stream().map(this::toDtoEmployeeUser).collect(Collectors.toSet());
    }
}
