package wf.rh.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import wf.rh.web.rest.TestUtil;

class ToDoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToDo.class);
        ToDo toDo1 = new ToDo();
        toDo1.setId(1L);
        ToDo toDo2 = new ToDo();
        toDo2.setId(toDo1.getId());
        assertThat(toDo1).isEqualTo(toDo2);
        toDo2.setId(2L);
        assertThat(toDo1).isNotEqualTo(toDo2);
        toDo1.setId(null);
        assertThat(toDo1).isNotEqualTo(toDo2);
    }
}
