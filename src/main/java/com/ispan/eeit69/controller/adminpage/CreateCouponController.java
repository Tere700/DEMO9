package com.ispan.eeit69.controller.adminpage;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.Coupon;
import com.ispan.eeit69.service.Impl.member.CouponMaService;
import com.ispan.eeit69.utils.SystemService;
import com.ispan.eeit69.validator.CouponValidator;

@Controller
@RequestMapping("/adminpages")
public class CreateCouponController extends AbstractController {
	
	Logger log = LoggerFactory.getLogger(CreateCouponController.class);
	
	CouponMaService couponMaService;

	CouponValidator couponValidator;
		
//	@Autowired
    public CreateCouponController(CouponMaService couponMaService, CouponValidator couponValidator) {
		this.couponMaService = couponMaService;
		this.couponValidator = couponValidator;
	}
	
	@GetMapping("/coupon/CreateCouponForm")
	public String sendEmptyForm(Model model) {
		Coupon coupon = new Coupon();
		// 表單之<form:form 標籤的 modelAttribute屬性需要下列敘述儲存的屬性物件；
		model.addAttribute(coupon);  // 注意：使用預設的識別字串
		log.info("送出可供前端使用者新增的空白表單");
		
		return "/adminpages/createcoupon";     
	}
	
	@PostMapping("/coupon/insertCoupon")
	public String saveItem(@ModelAttribute Coupon coupon, BindingResult result, Model model,
			RedirectAttributes ra) throws SerialException, SQLException {

		couponValidator.validate(coupon, result);
		if (result.hasErrors()) {
			log.warn("前端使用者送回的表單含有錯誤資料");
		    for (FieldError error : result.getFieldErrors()) {
		        log.warn("Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
		    }
			log.warn("-------------------------------------");
			// -------------------------------------
			// 下列四列敘述只有需要獲知由於格式錯而無法綁定時所需的錯誤訊息的Key才會用到它們
//			List<ObjectError> errors = result.getAllErrors();
//			for(ObjectError error : errors) {
//				log.warn(error.toString());
//			}
			// -------------------------------------
			// 如果使用者挑選圖片，if敘述的條件會是true
			if (!result.hasFieldErrors("filename")) {
				// 將圖片與圖片檔名的取出存入Model內以方便於驗證失敗時導回原輸入畫面時仍能顯示原來的圖片
				model.addAttribute("image", coupon.getImage());
				System.out.println(coupon.getImage());
				model.addAttribute("filename", coupon.getFilename());
			}
			return "/adminpages/createcoupon";
		}

		if (couponMaService.existsByCouponId(coupon)) {
			log.warn("提供的商品編號已存在: " + coupon.getCoupon_id());
			result.rejectValue("couponId", "coupon.coupon_id.exist.error", "商品編號已存在，請更換新的商品編號");
			model.addAttribute("image", coupon.getImage());
			model.addAttribute("filename", coupon.getFilename());
			return "/adminpages/createcoupon";
		}

		char[] c = coupon.getImage().toCharArray();
		Clob clob = new SerialClob(c);
		coupon.getImage_id().setImageData(clob);
		couponMaService.save(coupon);
		// 要將圖檔寫入Server端的資料夾
		File imageMainFolder = new File(SystemService.EMPLOYEE_IMAGE_FILE_FOLDER);
		String fileExt = ".txt";
		File outFile = new File(imageMainFolder, "Coupon_" + coupon.getCoupon_id() + fileExt);
		try (PrintWriter pw = new PrintWriter(outFile);) {
			pw.print(coupon.getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ra.addFlashAttribute("message", "資料新增成功");
		ra.addFlashAttribute("success", true);
//		ra.addFlashAttribute("message", "<font color='blue'>資料新增成功</font>");
		// 新增與修改成功一定要用 redirect: 傳回新增/修改成功的訊息
		log.info("新增商品資料已經完成");
		return "redirect:/adminpages/coupon/queryCoupon";
	}
	
//	@ModelAttribute
//	public Coupon beforeSave() {
//		Coupon coupon = new Coupon();
//		// 於新增員工資料前加入系統產生的資料
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		coupon.seitem.setCreateTime(ts);
//		return item;
//	}
	
	
	
	

}
