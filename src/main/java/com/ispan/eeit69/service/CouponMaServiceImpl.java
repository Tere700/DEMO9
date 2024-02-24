package com.ispan.eeit69.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.dao.CouponMaDao;
import com.ispan.eeit69.model.Coupon;
import com.ispan.eeit69.service.Impl.member.CouponMaService;


@Service
@Transactional
public class CouponMaServiceImpl implements CouponMaService{

	CouponMaDao couponMaDao;
	
//	@Autowired
	public CouponMaServiceImpl (CouponMaDao couponMaDao) {
		this.couponMaDao = couponMaDao;
	}
	
	
	@Override
	public void resetCouponTable() {
		couponMaDao.resetCouponTable();
		
	}

	@Override
	public void save(Coupon coupon) {
		couponMaDao.save(coupon);
		
	}

	@Override
	public void deleteByCouponId(Integer couponId) {
		couponMaDao.deleteByCouponId(couponId);
		
	}

	@Override
	public void update(Coupon coupon) {
		couponMaDao.update(coupon);
		
	}

	@Override
	public Coupon findByDiscount_type(String discountType) {
		Coupon coupon = couponMaDao.findByDiscount_type(discountType);
		return coupon;
	}

	@Override
	public boolean existsByCouponId(Coupon coupon) {
		if(couponMaDao.isPersist(coupon)) {
			couponMaDao.detach(coupon);
		}
		Coupon coupon1 = findByCouponId(coupon.getCoupon_id());
		return coupon1 != null;
	}

	@Override
	public Coupon findByCouponId(Integer couponId) {
		return couponMaDao.findByCouponId(couponId);
	}

	@Override
	public List<Coupon> findAll() {
		return couponMaDao.findAll();
	}

	@Override
	public boolean isPersist(Coupon coupon) {
		boolean ans = couponMaDao.isPersist(coupon);
		return ans;
	}

	@Override
	public void detach(Coupon coupon) {
		couponMaDao.detach(coupon);
		
	}

	@Override
	public int getTotalCouponCount() {
		return couponMaDao.getTotalCouponCount();
	}

	@Override
	public List<Coupon> getCouponSubset(int startIndex, int recordsPerPage) {
		return couponMaDao.getCouponSubset(startIndex, recordsPerPage);
	}

	@Override
	public int getTotalCouponCountByKeyword(String keyword) {
		return couponMaDao.countCouponsByKeyword(keyword);
	}

	@Override
	public List<Coupon> searchCouponsByKeyword(String keyword, int page, int recordsPerPage) {
		int startIndex = (page - 1) * recordsPerPage;
		return couponMaDao.findCouponsByKeyword(keyword, startIndex, recordsPerPage);
	}

}
