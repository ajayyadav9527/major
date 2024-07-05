package com.sheryians.major.controller;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CartController {

	@Autowired
	ProductService productService;

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}


	@GetMapping("/addToCartFromCart/{id}")
	public String addToCartFromCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/cart";
	}

	@GetMapping("/cart")
	public String getCart(Model model) {
		Map<Product, Long> productCountMap = GlobalData.cart.stream()
				.collect(Collectors.groupingBy(product -> product, Collectors.counting()));

		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("productCountMap", productCountMap);
		return "cart";
	}

	@GetMapping("/cart/removeItem/{id}")
	public String removeCart(@PathVariable int id) {
		Product productToRemove = null;
		for (Product product : GlobalData.cart) {
			if (product.getId() == id) {
				productToRemove = product;
				break;
			}
		}
		if (productToRemove != null) {
			GlobalData.cart.remove(productToRemove);
		}
		return "redirect:/cart";
	}

	@GetMapping("/checkout")
	public String checkoutCart(Model model) {
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}
}
