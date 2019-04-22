package com.elm.demo.dataobject;

import com.elm.demo.util.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * Created by weiyao on 2019/4/17.
 */
public class InfoMixin {
    @Column(name = "created_time", precision = 3, nullable = false)
    @Getter
    @Setter
    private LocalDateTime createdTime = LocalDateTime.now(ZoneId.of("UTC"));

    @Column(name = "updated_time", precision = 3)
    @Getter
    @Setter
    private LocalDateTime updatedTime;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean deleted = Boolean.FALSE;

    /**
     * Pre update.
     */
    @PreUpdate
    public void preUpdate() {
        updatedTime = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public LocalDateTime getCreatedTime() {
        return Objects.nonNull(createdTime) ? DateTimeUtil.toLocalDateTime(createdTime) : null;
    }

    public LocalDateTime getUpdatedTime() {
        return Objects.nonNull(updatedTime) ? DateTimeUtil.toLocalDateTime(updatedTime) : null;
    }
}
