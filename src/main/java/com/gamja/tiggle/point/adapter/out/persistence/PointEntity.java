package com.gamja.tiggle.point.adapter.out.persistence;

import com.gamja.tiggle.point.domain.GetOrUse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "point")
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer value;
    private String description;
    private GetOrUse getOrUse;
    private Date modifiedAt;

    @PrePersist
    public void setModifiedAt() {
        this.modifiedAt = Timestamp.from(Instant.now());
    }
}
