package com.cg.oam.entity;


import java.time.LocalDate;
import java.util.List;

import com.cg.oam.bean.PrescriptionBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prescription {
	public Prescription(PrescriptionBean prescription) {
		prescriptionId=prescription.getPrescriptionId();
		uploadDate=prescription.getUploadDate();
		validationDate=prescription.getValidationDate();
		prescriptionImage=prescription.getPrescriptionImage();
				
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "prescription_id", nullable = false)
	private Integer prescriptionId;
	
	@Column(name = "upload_date")
	private LocalDate uploadDate;

	@Column(name = "validation_date")
	private LocalDate validationDate;
	
	//storing large size images
	@Lob
	private byte[] prescriptionImage;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	//bidirectional relation with order
	@OneToMany(mappedBy = "prescription" ,cascade=CascadeType.ALL)
	private List<OrderDetails> orders;
}