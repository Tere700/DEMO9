package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Clob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ispan.eeit69.utils.SystemService;

@Entity
@Table(name = "discountTypeImage")
public class DiscountTypeImage implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer image_id;
	@Column(name = "discount_type")
	private String discountType;

	@JsonIgnore
	@Lob
	@Column(name = "image_data")
	private Clob imageData;

	@OneToMany(mappedBy = "image_id")
	private List<Coupon> coupons;

	public DiscountTypeImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscountTypeImage(Integer image_id, String discountType, Clob imageData, List<Coupon> coupons) {
		super();
		this.image_id = image_id;
		this.discountType = discountType;
		this.imageData = imageData;
		this.coupons = coupons;
	}

	public Integer getImage_id() {
		return image_id;
	}

	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Clob getImageData() {
		return imageData;
	}

	public void setImageData(Clob imageData) {
		this.imageData = imageData;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDataUri() throws Exception {
		return SystemService.clobToString(imageData);
	}
	@Override
	public String toString() {
		return "DiscountTypeImage [image_id=" + image_id + ", discountType=" + discountType + "]";
	}

}
