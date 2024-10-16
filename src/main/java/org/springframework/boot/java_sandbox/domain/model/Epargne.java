package org.springframework.boot.java_sandbox.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Epargne {
    private Long numContrat;
    private Integer nbPart;
    private Double epargneAcquise;
}
