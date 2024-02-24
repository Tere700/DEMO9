package com.ispan.eeit69.controller.adminpage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.eeit69.model.Coupon;
import com.ispan.eeit69.service.Impl.member.CouponMaService;

@Controller
@RequestMapping("/adminpages")
public class QueryCouponController extends AbstractController {
	
	int recordsPerPage = 5; //每頁筆數
	
	Logger log = LoggerFactory.getLogger(QueryCouponController.class);
	
	CouponMaService couponMaService;

	public QueryCouponController(CouponMaService couponMaService) {
		this.couponMaService = couponMaService;
	}
	
	
	
	
	@GetMapping("/coupon/queryCoupon")
    public String queryCoupon(@RequestParam(defaultValue = "1") int page, Model model) {
        int totalRecords = couponMaService.getTotalCouponCount();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        int startIndex = (page - 1) * recordsPerPage;
        System.out.println(recordsPerPage);
        List<Coupon> couponList = couponMaService.getCouponSubset(startIndex, recordsPerPage);

        model.addAttribute("couponList", couponList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startIndex", startIndex);
        System.out.println(startIndex);
        model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
        model.addAttribute("currentPage", page);

        return "/adminpages/coupons"; 
    }
	
	
	
	
	@GetMapping("/coupon/queryCouponByKeyword")
	public String queryCouponByKeyword(@RequestParam(defaultValue = "1") int page, 
	                                  @RequestParam(required = false) String keyword, 
	                                  Model model) {
	    int totalRecords;
	    List<Coupon> couponList;

	    if (keyword != null && !keyword.isEmpty()) {
	        // 如果提供了關鍵字，則根據關鍵字查詢物品
	        totalRecords = couponMaService.getTotalCouponCountByKeyword(keyword);
	        couponList = couponMaService.searchCouponsByKeyword(keyword, page, recordsPerPage);
	    } else {
	        // 如果未提供關鍵字，則獲取所有物品
	        totalRecords = couponMaService.getTotalCouponCount();
	        couponList =couponMaService.getCouponSubset((page - 1) * recordsPerPage, recordsPerPage);
	    }

	    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

	    if (page < 1) {
	        page = 1;
	    } else if (page > totalPages) {
	        page = totalPages;
	    }

	    int startIndex = (page - 1) * recordsPerPage;

	    model.addAttribute("couponList", couponList);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("startIndex", startIndex);
	    model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
	    model.addAttribute("currentPage", page);
	    model.addAttribute("keyword", keyword);

	    return "/adminpages/coupons";
	}
	

	
}
