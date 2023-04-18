package wf.rh.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import wf.rh.domain.Evidence;
import wf.rh.domain.Training;
import wf.rh.service.dto.EvidenceDTO;
import wf.rh.service.dto.TrainingDTO;

/**
 * Mapper for the entity {@link Training} and its DTO {@link TrainingDTO}.
 */
@Mapper(componentModel = "spring")
public interface TrainingMapper extends EntityMapper<TrainingDTO, Training> {
    @Mapping(target = "evidences", source = "evidences", qualifiedByName = "evidenceDescriptionSet")
    TrainingDTO toDto(Training s);

    @Mapping(target = "removeEvidence", ignore = true)
    Training toEntity(TrainingDTO trainingDTO);

    @Named("evidenceDescription")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    EvidenceDTO toDtoEvidenceDescription(Evidence evidence);

    @Named("evidenceDescriptionSet")
    default Set<EvidenceDTO> toDtoEvidenceDescriptionSet(Set<Evidence> evidence) {
        return evidence.stream().map(this::toDtoEvidenceDescription).collect(Collectors.toSet());
    }
}
