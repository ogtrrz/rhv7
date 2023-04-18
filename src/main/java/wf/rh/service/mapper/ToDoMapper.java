package wf.rh.service.mapper;

import org.mapstruct.*;
import wf.rh.domain.ToDo;
import wf.rh.service.dto.ToDoDTO;

/**
 * Mapper for the entity {@link ToDo} and its DTO {@link ToDoDTO}.
 */
@Mapper(componentModel = "spring")
public interface ToDoMapper extends EntityMapper<ToDoDTO, ToDo> {}
