package com.ispan.eeit69.service.Impl.member;

import java.util.List;

import com.ispan.eeit69.model.Coupon;

public interface CouponMaService {

	void resetCouponTable();
	
	void save(Coupon coupon);
	
	void deleteByCouponId(Integer couponId);

	void update(Coupon coupon);
	
	Coupon findByDiscount_type(String discountType);
	
	boolean existsByCouponId(Coupon coupon);
	
	Coupon findByCouponId(Integer couponId);
	
	List<Coupon> findAll();
	
	boolean isPersist(Coupon coupon);

	void detach(Coupon coupon);
	
	int getTotalCouponCount();
	
	List<Coupon> getCouponSubset(int startIndex, int recordsPerPage);
	
    //int countCouponsByKeyword(String keyword);
    
    int getTotalCouponCountByKeyword(String keyword);
    
    List<Coupon> searchCouponsByKeyword(String keyword, int page, int recordsPerPage);
    
    //public List<Coupon> findCouponsByKeyword(String keyword, int startIndex, int recordsPerPage);
	
	
}
