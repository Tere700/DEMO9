package com.ispan.eeit69.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ispan.eeit69.dao.CouponMaDao;
import com.ispan.eeit69.model.Coupon;

@Repository
public class CouponMaDaoImpl implements CouponMaDao{


	@PersistenceContext
	EntityManager  entityManager;

	@Override
	public void resetCouponTable() {
		throw new RuntimeException("本系統未提供此功能");
	}

	@Override
	public void save(Coupon coupon) {
		entityManager.persist(coupon);
		
	}

	@Override
	public void deleteByCouponId(Integer couponId) {
		String hql = "DELETE FROM Coupon e WHERE e.coupon_id = :id";
		entityManager.createQuery(hql)
					 .setParameter("id", couponId)
					 .executeUpdate();
		
	}

	@Override
	public void update(Coupon coupon) {
		entityManager.merge(coupon);
		
	}

	@Override
	public Coupon findByDiscount_type(String discountType) {
		Coupon result = null;
		String hql = "FROM Coupon WHERE discount_type = :eid";
		List<Coupon> coupons = entityManager
											.createQuery(hql, Coupon.class)
											.setParameter("eid", discountType)
											.getResultList();
		if(coupons.size()>0) {
			result = coupons.get(0);
		}
		return result;
	}

	@Override
	public Coupon findByCouponId(Integer couponId) {
		Coupon result = entityManager.find(Coupon.class, couponId);
		return result;
	}

	@Override
	public List<Coupon> findAll() {
		String hql = "FROM Coupon";
		List<Coupon> Coupons = entityManager.createQuery(hql,Coupon.class)
											.getResultList();
		return Coupons;
	}

	@Override
	public boolean isPersist(Coupon coupon) {
		boolean ans = entityManager.contains(coupon);
		if(ans)
			return true; 
		else
		return false;
	}

	@Override
	public void detach(Coupon coupon) {
		entityManager.detach(coupon);
		
	}

	@Override
	public int getTotalCouponCount() {
		Query query = entityManager.createQuery("SELECT COUNT(e) FROM Coupon e");
		return ((Number)query.getSingleResult()).intValue();
	}

	@Override
	public List<Coupon> getCouponSubset(int startIndex, int recordsPerPage) {
		Query query = entityManager.createQuery("SELECT e FROM Coupon e");
		query.setFirstResult(startIndex);
		query.setMaxResults(recordsPerPage);	
		List<Coupon>resultList = query.getResultList();
		return resultList;
	}

	@Override
	public int countCouponsByKeyword(String keyword) {
		Query query = entityManager.createQuery("SELECT COUNT(e) FROM Coupon e WHERE e.discount_type LIKE:keyword")
				.setParameter("keyword","%" + keyword + "%");
		return ((Number) query.getSingleResult()).intValue();
	}

	@Override
	public List<Coupon> findCouponsByKeyword(String keyword, int startIndex, int recordsPerPage) {
		Query query = entityManager.createQuery("SELECT e FROM Coupon e WHERE e.discount_type LIKE:keyword")
				.setParameter("keyword","%"+ keyword+"%")
				.setFirstResult(startIndex)
				.setMaxResults(recordsPerPage);
		return query.getResultList();
	}
	
	

}
