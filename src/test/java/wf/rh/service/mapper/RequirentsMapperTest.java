package wf.rh.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequirentsMapperTest {

    private RequirentsMapper requirentsMapper;

    @BeforeEach
    public void setUp() {
        requirentsMapper = new RequirentsMapperImpl();
    }
}
