package com.danvol.webchat.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class Message {
    @Id private String messageId;
    private String messageText;
    private Long sender;
    private @CreatedDate Date sendTime;
}
