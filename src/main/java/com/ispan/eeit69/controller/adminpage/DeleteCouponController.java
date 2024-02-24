package com.ispan.eeit69.controller.adminpage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.service.Impl.member.CouponMaService;

@Controller
@RequestMapping("/adminpages")
public class DeleteCouponController extends AbstractController{
	
	Logger log = LoggerFactory.getLogger(DeleteCouponController.class);
	
	CouponMaService couponMaService;

	public DeleteCouponController(CouponMaService couponMaService) {
		this.couponMaService = couponMaService;
	}

	@DeleteMapping("/coupon/CouponDelete/{id}")
	public String deleteItem(RedirectAttributes ra, 
			@PathVariable Integer id,
			@RequestParam String empNo
			) {
		try {
			log.info("123456");
			couponMaService.deleteByCouponId(id);
			ra.addFlashAttribute("message", "已刪除商品編號:" + empNo + "之紀錄");
			ra.addFlashAttribute("success", true);
			log.info("已刪除商品編號: " +  empNo + " 之紀錄");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "已刪除商品編號:" + empNo + "之紀錄失敗" + e.getMessage());
			ra.addFlashAttribute("success", true);
			log.error("刪除商品編號: " +  empNo + " 之紀錄失敗");
			e.printStackTrace();
		}
		return "redirect:/adminpages/coupon/queryCoupon";
	}
	
	

}
