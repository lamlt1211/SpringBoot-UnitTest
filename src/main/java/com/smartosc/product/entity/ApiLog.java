package com.smartosc.product.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 15:44
 * @created_by Tung lam
 * @since 12/06/2020
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApiLog {
    @Id
    @GeneratedValue
    private Long id;
    private Date calledTime;
    private String data;
    private String errorMessage;
    private int retryNum;
}
