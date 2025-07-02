package org.example.lmsbackend.dto;

import java.time.Instant;

public class DiscussionDTO {
    private Integer discussionId;
    private Integer courseId;
    private Integer userId;
    private String title;
    private String content;
    private Instant createdAt;
}
