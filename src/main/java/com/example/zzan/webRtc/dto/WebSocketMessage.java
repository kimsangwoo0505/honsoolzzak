package com.example.zzan.webRtc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMessage {
    private Long from;
    private String type;
    private Long data;
    private Object candidate;
    private Object sdp;
}
