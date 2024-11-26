package vn.iotstar.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VideoModel {
	
	private String videoid;
	
	private boolean active;
	
	private String description;
	
	// Save image
	private MultipartFile imageFile;
	
	private String poster;
	
	private String title;
	
	private String images;
	
	private int views;
	
	private Long categoryid;
	
	private Boolean isEdit = false;
}
