package vn.iotstar.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryModel {
	
	private Long categoryid;
	
	private String categorycode;
	
	@NotEmpty
	@Length(min = 5)
	private String categoryname;
	
	private String images;
	
	// Save image
	private MultipartFile imageFile;
	
	private boolean status;
	
	private Boolean isEdit = false;
	
}
