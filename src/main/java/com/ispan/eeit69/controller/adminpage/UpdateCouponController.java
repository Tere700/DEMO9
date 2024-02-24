package com.ispan.eeit69.controller.adminpage;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.Coupon;
import com.ispan.eeit69.service.Impl.member.CouponMaService;
import com.ispan.eeit69.validator.CouponValidator;

@Controller
@RequestMapping("/adminpages")
@SessionAttributes("previous_couponId")
public class UpdateCouponController extends AbstractController{
	
	Logger log = LoggerFactory.getLogger(UpdateCouponController.class);
	
	CouponMaService couponMaService;
	
	CouponValidator couponValidator;
	
//	@Autowired
	public UpdateCouponController(CouponMaService couponMaService, CouponValidator couponValidator) {
		super();
		this.couponMaService = couponMaService;
		this.couponValidator = couponValidator;
	}
	
	
	 @GetMapping("/coupon/findById/{id}")
	 public String sendEmptyForm(Model model) {  
	  log.info("送出可供前端使用者修改的表單");
	  return "/adminpages/updatecoupon";
	 }

	
	
	@PutMapping("/coupon/editCoupon/{id}")
	public String updateItem(@ModelAttribute Coupon coupon, BindingResult result,
			@RequestParam String previous_couponId, Model model, RedirectAttributes ra)
			throws SerialException, SQLException {
		// 對使用者輸入之資料進行驗證
		couponValidator.validate(coupon, result);
		// 如果修改後的資料有錯誤
		if (result.hasErrors()) {
			log.warn("前端使用者送回的表單含有錯誤資料");
			log.warn("-------------------------------------");
			// -------------------------------------
			// 下列迴圈僅為了找出顯示我們需要之錯誤訊息所需的key:
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				log.warn(error.toString());
			}
			log.warn("-------------------------------------");
			// -------------------------------------
			if (!result.hasFieldErrors("filename")) {
				// 為了能正確顯示圖片
				model.addAttribute("image", coupon.getImage());
				model.addAttribute("filename", coupon.getFilename());
			}
			// 導向原來修改資料的頁面updateEmployee.jsp
			// 重要，重要，重要: updateEmployee.jsp 的<%@ page ... %>一定加上 isErrorPage='true'
			return "/adminpages/updatecoupon";
		}
		// 如果修改員工編號，需要檢查修改後的員工編號是否已經存在
		if (!previous_couponId.equals(coupon.getCoupon_id())) {
			log.warn("使用者修改了商品編號，原來商品編號: " + previous_couponId + ", 新的商品編號: " + coupon.getCoupon_id());
			// employeeService.existsByEmployeeId(employee)需要先檢查 employee 物件是否為
			// persistent entity。如果是，要先移除(detach)它，因為JPA在執行 JPQL (對應 HQL)
			// 前會先進行 entityManager.flush(), 此舉會將含有重複的employeeId寫入表格中而造成錯誤
			if (couponMaService.existsByCouponId(coupon)) {
				log.warn("新的商品編號已經存在，系統不接受");
				result.rejectValue("couponId", "coupon.coupon_id.exist.error", "商品編號已存在，請更換新的商品編號");
				model.addAttribute("image", coupon.getImage());
				model.addAttribute("filename", coupon.getFilename());
				//
				return "/adminpages/updatecoupon";
			}
		}
		// 將圖片資料(data url)轉換為 Clob
		char[] c = coupon.getImage().toCharArray();
		Clob clob = new SerialClob(c);
		coupon.getImage_id().setImageData(clob);
		
		log.info(coupon.toString()); //debug employee 值沒有變
		
		couponMaService.update(coupon);
		ra.addFlashAttribute("message", "資料修改成功");
		ra.addFlashAttribute("success", true);
		// 新增與修改成功一定要用 redirect: 傳回新增/修改成功的訊息
		log.info("修改商品資料已經完成");
		return "redirect:/adminpages/coupon/queryCoupon";
	}
	
	@ModelAttribute
	public Coupon getMember(@PathVariable Integer id, Model model) {
		Coupon coupon = couponMaService.findByCouponId(id);
		model.addAttribute("previous_couponId", coupon.getCoupon_id());
		log.info("依照傳入的主鍵值:" + id + "讀取對應的紀錄");
		return coupon;
	}

	

}
