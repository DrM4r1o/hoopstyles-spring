package com.hoopstyles.hoopstyles.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.model.OrderLine;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.FileService;
import com.hoopstyles.hoopstyles.services.OrderLineService;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;


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

    @Autowired
    OrderLineService orderLineService;

    @Autowired
    FileService fileService;
	
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

        for (OrderLine orderLine : orderLineService.findByProduct(p)) {
            orderLineService.remove(orderLine);
        }

        categoryService.removeProduct(p);
        productService.delete(p);
        return "redirect:/profile/admin";
    }
       
    @PostMapping("/product/edit/{id}")
    public String editProduct (@PathVariable Long id, 
                               @RequestParam("name") String name,
                               @RequestParam("price") Float price,
                               @RequestParam("category") String category,
                               @RequestParam("file") MultipartFile file)
    {
        Product p = productService.findById(id);

        p.setCategories(getCategories(category));

        if(!p.getName().equals(name) && !name.isEmpty()) {
            p.setName(name);
        }

        if(price != null && price > 0) {
            p.setPrice(price);
        }

        if (!file.isEmpty()) {
            fileService.saveFile(file);
            p.setImage("/images/" + file.getOriginalFilename());
        }

        productService.insert(p);
        return "redirect:/profile/admin";
    }

    @PostMapping("/product/new")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("price") Float price,
                             @RequestParam("category") String category,
                             @RequestParam("file") MultipartFile file)
    {
        System.out.println("Entra");

        Product p = new Product();

        p.setCategories(getCategories(category));
        p.setName(name);

        if(price > 0) {
            p.setPrice(price);
        }

        if (!file.isEmpty()) {
            fileService.saveFile(file);
            p.setImage("/images/" + file.getOriginalFilename());
        }

        productService.insert(p);
        return "redirect:/profile/admin";
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

    private List<Category> getCategories(String category) {

        String[] categories = category.split(",");
        List<Category> cat = new ArrayList<Category>();

        for(int i = 0; i < categories.length; i++) {
            Category c = categoryService.findByName(categories[i]);
            if(c == null) {
                c = new Category(categories[i], "Category description");
                categoryService.insert(c);
            }
            cat.add(c);
        }

        return cat;
    }
	
}
