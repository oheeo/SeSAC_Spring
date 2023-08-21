package com.example.apitest.model.request;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PushEventApiRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String tenantId;

    private String apiKey;

    private String token;

    private String message;

    private Integer receiverId;
}
