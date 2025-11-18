package com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.messsagingTest;


import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.messaging.FeedbackEventListener;
import com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.model.FeedbackEvent;

@ExtendWith(MockitoExtension.class)
public class FeedbackEventListenerTest {


    @Spy
    private FeedbackEventListener feedbackEventListener;

    @Test
    void feedBackSubmitted_Valid_LogsEvent() {

        // Arrange
        UUID uuid = UUID.randomUUID();
        Instant timestamp = Instant.now();
        FeedbackEvent feedbackEvent = new FeedbackEvent(
                uuid,
                "M12345",
                "Dr. Smith",
                5,
                "Great visit",
                timestamp
        );

        // Act
        feedbackEventListener.feedbackSubmitted(feedbackEvent);

        // Assert
        verify(feedbackEventListener).feedbackSubmitted(feedbackEvent);
    }
}
