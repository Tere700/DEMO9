package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userCoupon")
public class UserCoupon implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_coupon_id; // 用戶優惠券的唯一識別符。
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user_id; // 考慮改名為 user// 用戶的唯一識別符，用於關聯用戶。
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id", referencedColumnName = "coupon_id")
	private Coupon coupon_id; // 考慮改名為 coupon 關聯的優惠券的ID。
	private Boolean used; // 標識優惠券是否已經使用。已使用用為true=1，未使用為false=0。
	private Timestamp used_at; // 如果優惠券已使用，則記錄使用日期時間戳。 
	private Timestamp created_at; // 創建日期時間戳。

	public UserCoupon() {
		super();
	}	

	public UserCoupon(Integer user_coupon_id, User user_id, Coupon coupon_id, Boolean used, Timestamp used_at,
			Timestamp created_at) {
		super();
		this.user_coupon_id = user_coupon_id;
		this.user_id = user_id;
		this.coupon_id = coupon_id;
		this.used = used;
		this.used_at = used_at;
		this.created_at = created_at;
	}

	public Integer getUser_coupon_id() {
		return user_coupon_id;
	}

	public void setUser_coupon_id(Integer user_coupon_id) {
		this.user_coupon_id = user_coupon_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Coupon getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Coupon coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public Timestamp getUsed_at() {
		return used_at;
	}

	public void setUsed_at(Timestamp used_at) {
		this.used_at = used_at;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserCoupon [user_coupon_id=" + user_coupon_id + ", user_id=" + user_id + ", coupon_id=" + coupon_id
				+ ", used=" + used + ", used_at=" + used_at + ", created_at=" + created_at + "]";
	}


}
