package dev.reddy.olm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.reddy.olm.exception.ApiException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Auditable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column(name = "created_by", updatable = false)
    private Long createdBy;
    @Column(name = "updated_by")
    private Long updatedBy;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    public void beforePersist() {
        var userId = 0L ; // RequestContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new ApiException("cannot persist without userId in Request context for this thread");
        }
        setCreatedAt(LocalDateTime.now());
        setCreatedBy(userId);
        setUpdatedAt(LocalDateTime.now());
        setUpdatedBy(userId);
    }
}
