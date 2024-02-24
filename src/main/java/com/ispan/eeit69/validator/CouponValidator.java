package com.ispan.eeit69.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ispan.eeit69.model.Coupon;
import com.ispan.eeit69.service.Impl.member.CouponMaService;

@Component
public class CouponValidator implements Validator {
	
	CouponMaService couponMaService;

//	@Autowired
	CouponValidator(CouponMaService couponMaService) {
		this.couponMaService = couponMaService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Coupon.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "coupon_id", "coupon.coupon_id.empty.error", "必須輸入商品編號"); 

		Coupon coupon = (Coupon)target;
		
		if (coupon.getImage() == null || coupon.getImage().length() == 0) {
			if (coupon.getFilename().length() == 0) {
				errors.rejectValue("filename", "item.image.empty.error", "必須挑選照片");				
			}
		}
		
	}
	
	
}
