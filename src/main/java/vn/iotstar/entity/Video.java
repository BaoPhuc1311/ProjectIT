package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Videos")
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "VideoId")
	private String videoid;
	
	@Column(name = "Active")
	private boolean active;
	
	@Column(name = "Description", columnDefinition = "NVARCHAR(MAX) NOT NULL")
	private String description;
	
	@Column(name = "Poster")
	private String poster;
	
	@Column(name = "Title", columnDefinition = "NVARCHAR(255) NOT NULL")
	private String title;
	
	@Column(name = "Images")
	private String images;
	
	@Column(name = "Views")
	private int views;
	
	// Connect Many-to-One with Category
	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;
	
}
