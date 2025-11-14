package com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.messaging;

import com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.model.FeedbackEvent;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;



@Component
public class FeedbackEventListener {

    private static final Logger LOG =  LoggerFactory.getLogger(FeedbackEventListener.class);
    @KafkaListener(topics = "feedback-submitted")
    public void feedbackSubmitted(FeedbackEvent feedback) {

        LOG.info("""
                
                
                ********************** Kakfa LISTENER **********************\
                
                [CONSUMER] (FeedbackEvent) \
                
                [PROVIDER-NAME] ---> {}
                
                [MEMBER-ID]: ---> {}
                
                [COMMENT]: ---> {}
                
                [RATING]: ---> {}
                
                ****************************************************************""",
                feedback.getProviderName(),
                feedback.getMemberId(),
                feedback.getComment(),
                feedback.getRating()
        );
    }
}
