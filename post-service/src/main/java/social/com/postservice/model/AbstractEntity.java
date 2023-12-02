package social.com.postservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created_by", nullable = false)
    protected String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

}

