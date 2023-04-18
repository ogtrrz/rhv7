package wf.rh.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import wf.rh.web.rest.TestUtil;

class HistoricDataDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricDataDTO.class);
        HistoricDataDTO historicDataDTO1 = new HistoricDataDTO();
        historicDataDTO1.setId(1L);
        HistoricDataDTO historicDataDTO2 = new HistoricDataDTO();
        assertThat(historicDataDTO1).isNotEqualTo(historicDataDTO2);
        historicDataDTO2.setId(historicDataDTO1.getId());
        assertThat(historicDataDTO1).isEqualTo(historicDataDTO2);
        historicDataDTO2.setId(2L);
        assertThat(historicDataDTO1).isNotEqualTo(historicDataDTO2);
        historicDataDTO1.setId(null);
        assertThat(historicDataDTO1).isNotEqualTo(historicDataDTO2);
    }
}
