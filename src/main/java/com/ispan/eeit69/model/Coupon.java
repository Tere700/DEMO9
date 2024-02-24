package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "coupon")
public class Coupon implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coupon_id; // 優惠券的唯一識別符。
	private String discount_type; // 折扣類型（例如滿千送百或8折卷）。
	private Float discount_amount; // 折扣金額（例如100元或20%）。
	private String valid_from; // 優惠券的有效期開始日期。
	private String valid_to; // 優惠券的有效期結束日期。
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式
	private Boolean is_active; // 標識優惠券是否處於活動狀態。
	private Timestamp created_at; // 創建日期時間戳。

	@ManyToOne
	@JoinColumn(name = "image_id")
	private DiscountTypeImage image_id;

	@OneToMany(mappedBy = "coupon_id", fetch = FetchType.LAZY)
	private Set<UserCoupon> userCoupons = new HashSet<>();

	@Transient
	private String image;
	
	@Transient
	private String filename;

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupon(Integer coupon_id, String discount_type, Float discount_amount, String valid_from, String valid_to,
			SimpleDateFormat dateFormat, Boolean is_active, Timestamp created_at, DiscountTypeImage image_id) {
		super();
		this.coupon_id = coupon_id;
		this.discount_type = discount_type;
		this.discount_amount = discount_amount;
		this.valid_from = valid_from;
		this.valid_to = valid_to;
		this.dateFormat = dateFormat;
		this.is_active = is_active;
		this.created_at = created_at;
		this.image_id = image_id;
	}

	public Integer getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

	public Float getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(Float discount_amount) {
		this.discount_amount = discount_amount;
	}

	public String getValid_from() {
		return valid_from;
	}

	public void setValid_from(String valid_from) {
		this.valid_from = valid_from;
	}

	public String getValid_to() {
		return valid_to;
	}

	public void setValid_to(String valid_to) {
		this.valid_to = valid_to;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public DiscountTypeImage getImage_id() {
		return image_id;
	}

	public void setImage_id(DiscountTypeImage image_id) {
		this.image_id = image_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Coupon [coupon_id=" + coupon_id + ", discount_type=" + discount_type + ", discount_amount="
				+ discount_amount + ", valid_from=" + valid_from + ", valid_to=" + valid_to + ", dateFormat="
				+ dateFormat + ", is_active=" + is_active + ", created_at=" + created_at + ", image_id=" + image_id
				+ "]";
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
