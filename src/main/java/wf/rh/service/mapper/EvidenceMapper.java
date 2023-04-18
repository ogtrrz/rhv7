package wf.rh.service.mapper;

import org.mapstruct.*;
import wf.rh.domain.Evidence;
import wf.rh.service.dto.EvidenceDTO;

/**
 * Mapper for the entity {@link Evidence} and its DTO {@link EvidenceDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvidenceMapper extends EntityMapper<EvidenceDTO, Evidence> {}
