package learn.solarfarm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SolarControllerTest {

    @MockBean
    SolarPanelRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void getPanelsBySectionName() throws Exception {
        List<SolarPanel> solarPanels = new ArrayList<>();
        solarPanels.add(new SolarPanel(1, "HILL", 1, 1, 2020, Material.POLY_SI, true));
        solarPanels.add(new SolarPanel(2, "HILL", 1, 2, 2020, Material.POLY_SI, true));

        when(repository.findBySection("Section One"))
                .thenReturn(solarPanels);

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(solarPanels);

        mvc.perform(get("/api/Section One"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

    }

    @Test
    void shouldUpdatePanelAndReturn204() throws Exception {
        SolarPanel solarPanel = new SolarPanel(1, "HILL", 1, 1, 2020, Material.POLY_SI, true);

        ObjectMapper objectMapper = new ObjectMapper();
        String solarPanelJson = objectMapper.writeValueAsString(solarPanel);

        when(repository.update(any())).thenReturn(true);

        var request = put("/api/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(solarPanelJson);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }
}