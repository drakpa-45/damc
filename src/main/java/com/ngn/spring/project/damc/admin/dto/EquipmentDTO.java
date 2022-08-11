package com.ngn.spring.project.damc.admin.dto;

import com.ngn.spring.project.damc.common.dto.CdbDTO;
import com.ngn.spring.project.damc.common.dto.VehicleDetails;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/20/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class EquipmentDTO {

    private String id;
    private String contractorId;
    private String consultantId;
    private String specializedFirmId;
    private String equipmentId;
    private String registrationNo;
    private String serialNo;
    private String modelNo;
    private Integer quantity;

    private Integer verified;
    private Integer approved;
    private Integer deleteRequest;
    private Integer editCheck;
    private String equipmentName;
    private String equipmentType;
    private String ownerCid;
    private String ownerName;


    List<AttachmentDTO> eqAttachments;

    List<VehicleDetails> vehicleDetailses;

    private List<CdbDTO> cdbDTOs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public List<AttachmentDTO> getEqAttachments() {
        return eqAttachments;
    }

    public Integer getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(Integer deleteRequest) {
        this.deleteRequest = deleteRequest;
    }

    public Integer getEditCheck() {
        return editCheck;
    }

    public void setEditCheck(Integer editCheck) {
        this.editCheck = editCheck;
    }

    public void setEqAttachments(List<AttachmentDTO> eqAttachments) {
        this.eqAttachments = eqAttachments;
    }

    public String getSpecializedFirmId() {
        return specializedFirmId;
    }

    public void setSpecializedFirmId(String specializedFirmId) {
        this.specializedFirmId = specializedFirmId;
    }

    public List<VehicleDetails> getVehicleDetailses() {
        return vehicleDetailses;
    }

    public void setVehicleDetailses(List<VehicleDetails> vehicleDetailses) {
        this.vehicleDetailses = vehicleDetailses;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public List<CdbDTO> getCdbDTOs() {
        return cdbDTOs;
    }

    public void setCdbDTOs(List<CdbDTO> cdbDTOs) {
        this.cdbDTOs = cdbDTOs;
    }

    public String getOwnerCid() {
        return ownerCid;
    }

    public void setOwnerCid(String ownerCid) {
        this.ownerCid = ownerCid;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentDTO that = (EquipmentDTO) o;

         if (!equipmentId.equals(that.equipmentId)) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return registrationNo.hashCode();
    }
}
