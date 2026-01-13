package htw.gruppe.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.repository.KandidatenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class KandidatControllerTest {

    @SuppressWarnings("unused")
    private final MockMvc mockMvc;

    @SuppressWarnings("unused")
    private final ObjectMapper objectMapper;
    private final KandidatenRepository userRepository;

    @Autowired
    public KandidatControllerTest(MockMvc mockMvc,
            ObjectMapper objectMapper,
            KandidatenRepository kandidatenRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userRepository = kandidatenRepository;
    }

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void createKandidaten() throws Exception {
        Kandidat kandidat = new Kandidat("123456", "Informatik", "geheim", "Max", "Muster",
                "Ich setze mich fuer mehr lernraeume ein", null);

    }

}
