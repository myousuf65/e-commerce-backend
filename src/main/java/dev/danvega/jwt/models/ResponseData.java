package dev.danvega.jwt.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseData {

    private String productName;
    private String fileType;
    private long fileSize;

}
