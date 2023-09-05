package com.example.BE_LinkKien.payload.resquest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IpDistributionRequest {
    private Integer ip;
    private Integer user;


}
