package com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.controllerTest;



import org.mockito.Mock;
import static org.mockito.Mockito.verifyNoInteractions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.messaging.FeedbackEventListener;

@WebMvcTest(FeedbackEventListener.class)
public class FeedbackControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    FeedbackEventListener feedbackEventListener;


    void getHealth_HappyPath() throws Exception {

        mockMvc.perform(get("/health")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Consumer is healthy"));
        verifyNoInteractions(feedbackEventListener);
    }

}
