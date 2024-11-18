package com.socialtech.eppeye.configuration.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ppe_requirements")
public class PPERequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ppe_configuration_id")
    private PPEConfiguration ppeConfiguration;
}

