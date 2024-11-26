package vn.iotstar.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iotstar.entity.Category;
import vn.iotstar.model.CategoryModel;
import vn.iotstar.service.ICategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@RequestMapping("")
	public String List(ModelMap model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("categories", list);
		return "admin/categories/list";
	}
	
	@GetMapping("add")
	public String Add(ModelMap model) {
		CategoryModel cate = new CategoryModel();
		cate.setIsEdit(false);
		model.addAttribute("category", cate);
		return "admin/categories/add-edit";
	}
	
	@PostMapping("save-update")
	public ModelAndView SaveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel cate, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/categories/add-edit");
		}
		Category entity = new Category();
		
		// Copy from Model to Entity
		BeanUtils.copyProperties(cate, entity);
		
		categoryService.save(entity);
		
		String message = "";
		if (cate.getIsEdit() == true) {
			message = "Category has been updated successfully";
		}
		else {
			message = "Category has been added successfully";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/categories", model);
	}
	
	@GetMapping("edit/{categoryid}")
	public ModelAndView Edit(ModelMap model, @PathVariable("categoryid") Long categoryid) {
		Optional<Category> opt = categoryService.findById(categoryid);
		
		CategoryModel cate = new CategoryModel();
		if (opt.isPresent()) {
			Category entity = opt.get();
			
			// Copy from Entity to Model
			BeanUtils.copyProperties(entity, cate);
			
			cate.setIsEdit(true);
			model.addAttribute("category", cate);
			return new ModelAndView("admin/categories/add-edit", model);
		}
		
		model.addAttribute("message", "Category doesn't exist");
		
		return new ModelAndView("admin/categories", model);
	}
	
	@GetMapping("delete/{categoryid}")
	public ModelAndView Delete(ModelMap model, @PathVariable("categoryid") Long categoryid) {
		categoryService.deleteById(categoryid);
		model.addAttribute("message", "Category has been deleted successfully");
		return new ModelAndView("forward:/admin/categories", model);
	}
	
}
