package com.domain.model.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
/**
 * Auditing
 * ● Saat kita membuat table, sering sekali kita menambahkan informasi audit seperti createdAt dan updatedAt
 * ● Spring Data JPA mendukung mengubahan data audit secara otomatis ketika proses save
 * ● Kita cukup gunakan annotation @CreatedDate dan @LastModifiedDate, dan menggunakan
 *      EntityListener AuditingEntityListener
 * ● Kita bisa menggunakan tipe data Date, Timestamp, Instance atau Long (milis) untuk field audit nya
 * ● Secara default, fitur ini tidak aktif, untuk mengaktifkannya, kita harus menambahkan annotation
 *      @EnableJpaAuditing
 */
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T> {

    // Untuk bisa mendapatkan current-nya, maka kita harus buat kelas turunan AuditorAware
    @CreatedBy
    protected T createdBy;
 
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @LastModifiedBy
    protected T updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;
    
}
