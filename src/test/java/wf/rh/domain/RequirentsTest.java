package wf.rh.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import wf.rh.web.rest.TestUtil;

class RequirentsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Requirents.class);
        Requirents requirents1 = new Requirents();
        requirents1.setId(1L);
        Requirents requirents2 = new Requirents();
        requirents2.setId(requirents1.getId());
        assertThat(requirents1).isEqualTo(requirents2);
        requirents2.setId(2L);
        assertThat(requirents1).isNotEqualTo(requirents2);
        requirents1.setId(null);
        assertThat(requirents1).isNotEqualTo(requirents2);
    }
}
