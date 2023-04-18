package wf.rh.service.mapper;

import org.mapstruct.*;
import wf.rh.domain.HistoricData;
import wf.rh.service.dto.HistoricDataDTO;

/**
 * Mapper for the entity {@link HistoricData} and its DTO {@link HistoricDataDTO}.
 */
@Mapper(componentModel = "spring")
public interface HistoricDataMapper extends EntityMapper<HistoricDataDTO, HistoricData> {}
