package org.springframework.boot.java_sandbox.application.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EpargnePerpDTO {
    private Integer nbPart;
    private Double epargneAcquise;
}