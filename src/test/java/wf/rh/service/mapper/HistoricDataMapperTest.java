package wf.rh.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistoricDataMapperTest {

    private HistoricDataMapper historicDataMapper;

    @BeforeEach
    public void setUp() {
        historicDataMapper = new HistoricDataMapperImpl();
    }
}
