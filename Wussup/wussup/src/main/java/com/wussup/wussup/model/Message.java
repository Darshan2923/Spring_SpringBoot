package com.wussup.wussup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String senderNumber;
    private String recipientNumber;
    private String body;
}
