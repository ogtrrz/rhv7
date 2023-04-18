package wf.rh.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import wf.rh.web.rest.TestUtil;

class HistoricDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricData.class);
        HistoricData historicData1 = new HistoricData();
        historicData1.setId(1L);
        HistoricData historicData2 = new HistoricData();
        historicData2.setId(historicData1.getId());
        assertThat(historicData1).isEqualTo(historicData2);
        historicData2.setId(2L);
        assertThat(historicData1).isNotEqualTo(historicData2);
        historicData1.setId(null);
        assertThat(historicData1).isNotEqualTo(historicData2);
    }
}
