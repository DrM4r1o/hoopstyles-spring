package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
public class ProductsController {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

    @Autowired
    CategoryService categoryService;
	
	private UserHoop user;
	
	@GetMapping("/product/{id}")
	public String productPage(@PathVariable Long id, Model model) {
		Product p = productService.findById(id);
        model.addAttribute("product", p);
		return "product";
	}

    @PostMapping("/product/remove/{id}")
    public String removeProduct(@PathVariable Long id, Model model) {
        Product p = productService.findById(id);
        categoryService.removeProduct(p);
        productService.delete(p);
        return "redirect:/profile/admin";
    }
	
	@PostMapping("/product/nuevo/submit")
	public String newProductSubmit(@ModelAttribute Product product, @RequestParam("file") MultipartFile file) {	
		/*
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			producto.setImagen(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", imagen).build().toUriString());
		}
		*/
		product.setOwner(user);
		productService.insert(product);
		return "redirect:/app/myproducts";
	}

    @ModelAttribute("user")
	public String usuario(Model model) {
		return userService.getUsername();
	}

    @ModelAttribute("cartCount")
    public int cartCount(Model model) {
        UserHoop user = userService.findByEmail(userService.getUsername());
        if(user == null) {
            return 0;
        }
        BasketballOrder order = orderService.getActiveOrder(user);
        return order.getOrderLines().size();
    }
	
}
