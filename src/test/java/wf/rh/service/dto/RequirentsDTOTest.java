package wf.rh.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import wf.rh.web.rest.TestUtil;

class RequirentsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequirentsDTO.class);
        RequirentsDTO requirentsDTO1 = new RequirentsDTO();
        requirentsDTO1.setId(1L);
        RequirentsDTO requirentsDTO2 = new RequirentsDTO();
        assertThat(requirentsDTO1).isNotEqualTo(requirentsDTO2);
        requirentsDTO2.setId(requirentsDTO1.getId());
        assertThat(requirentsDTO1).isEqualTo(requirentsDTO2);
        requirentsDTO2.setId(2L);
        assertThat(requirentsDTO1).isNotEqualTo(requirentsDTO2);
        requirentsDTO1.setId(null);
        assertThat(requirentsDTO1).isNotEqualTo(requirentsDTO2);
    }
}
