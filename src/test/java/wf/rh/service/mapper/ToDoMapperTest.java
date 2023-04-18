package wf.rh.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoMapperTest {

    private ToDoMapper toDoMapper;

    @BeforeEach
    public void setUp() {
        toDoMapper = new ToDoMapperImpl();
    }
}
