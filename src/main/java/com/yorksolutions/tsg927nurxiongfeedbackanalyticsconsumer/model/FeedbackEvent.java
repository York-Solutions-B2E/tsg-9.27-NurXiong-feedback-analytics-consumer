package com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackEvent {
    private UUID id;
    private String memberId;
    private String providerName;
    private int rating;
    private String comment;
    private Instant submittedAt;

}
