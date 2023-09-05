package com.example.BE_LinkKien.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.sql.Timestamp;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @Column(name="created_by", updatable = false, nullable = false)
    @CreatedBy
    protected String createdBy;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    protected Timestamp createdAt;

    @Column(name="updated_by")
    @LastModifiedBy
    protected String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected Timestamp updatedAt;
}
