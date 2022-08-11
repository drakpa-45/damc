package com.ngn.spring.project.damc.common.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * ====================================================================
 * Created by Nima Yoezer on 8/4/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class ServiceFeeDTO {
    private String id;
    private Integer referenceNo;
    private String serviceName;
    private BigDecimal feeAmount;
    private BigInteger count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(Integer referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }
}
