package wf.rh.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import wf.rh.web.rest.TestUtil;

class ToDoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToDoDTO.class);
        ToDoDTO toDoDTO1 = new ToDoDTO();
        toDoDTO1.setId(1L);
        ToDoDTO toDoDTO2 = new ToDoDTO();
        assertThat(toDoDTO1).isNotEqualTo(toDoDTO2);
        toDoDTO2.setId(toDoDTO1.getId());
        assertThat(toDoDTO1).isEqualTo(toDoDTO2);
        toDoDTO2.setId(2L);
        assertThat(toDoDTO1).isNotEqualTo(toDoDTO2);
        toDoDTO1.setId(null);
        assertThat(toDoDTO1).isNotEqualTo(toDoDTO2);
    }
}
