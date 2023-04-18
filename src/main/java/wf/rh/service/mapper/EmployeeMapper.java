package wf.rh.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import wf.rh.domain.Employee;
import wf.rh.domain.HistoricData;
import wf.rh.domain.ToDo;
import wf.rh.domain.Training;
import wf.rh.service.dto.EmployeeDTO;
import wf.rh.service.dto.HistoricDataDTO;
import wf.rh.service.dto.ToDoDTO;
import wf.rh.service.dto.TrainingDTO;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
    @Mapping(target = "trainings", source = "trainings", qualifiedByName = "trainingCodeSet")
    @Mapping(target = "todos", source = "todos", qualifiedByName = "toDoDescriptionSet")
    @Mapping(target = "historicData", source = "historicData", qualifiedByName = "historicDataNameSet")
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    EmployeeDTO toDto(Employee s);

    @Mapping(target = "removeTraining", ignore = true)
    @Mapping(target = "removeTodo", ignore = true)
    @Mapping(target = "removeHistoricData", ignore = true)
    Employee toEntity(EmployeeDTO employeeDTO);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);

    @Named("trainingCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TrainingDTO toDtoTrainingCode(Training training);

    @Named("trainingCodeSet")
    default Set<TrainingDTO> toDtoTrainingCodeSet(Set<Training> training) {
        return training.stream().map(this::toDtoTrainingCode).collect(Collectors.toSet());
    }

    @Named("toDoDescription")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    ToDoDTO toDtoToDoDescription(ToDo toDo);

    @Named("toDoDescriptionSet")
    default Set<ToDoDTO> toDtoToDoDescriptionSet(Set<ToDo> toDo) {
        return toDo.stream().map(this::toDtoToDoDescription).collect(Collectors.toSet());
    }

    @Named("historicDataName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    HistoricDataDTO toDtoHistoricDataName(HistoricData historicData);

    @Named("historicDataNameSet")
    default Set<HistoricDataDTO> toDtoHistoricDataNameSet(Set<HistoricData> historicData) {
        return historicData.stream().map(this::toDtoHistoricDataName).collect(Collectors.toSet());
    }
}
