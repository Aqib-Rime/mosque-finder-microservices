package com.chotonakib.mosqueservice.api.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirstRequestFromUIPost {
    private String latitude;
    private String longitude;
    private String userDeviceId;
    private String userTokenId;
}
