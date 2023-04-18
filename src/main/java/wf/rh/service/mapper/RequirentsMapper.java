package wf.rh.service.mapper;

import org.mapstruct.*;
import wf.rh.domain.Requirents;
import wf.rh.service.dto.RequirentsDTO;

/**
 * Mapper for the entity {@link Requirents} and its DTO {@link RequirentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequirentsMapper extends EntityMapper<RequirentsDTO, Requirents> {}
