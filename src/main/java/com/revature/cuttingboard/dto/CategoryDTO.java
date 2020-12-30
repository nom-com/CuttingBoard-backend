package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.Category;

public class CategoryDTO {

		private int id;
		private String category;
		
		public CategoryDTO() {
			super();
		}
		
		public CategoryDTO(int id, String category) {
			this.id = id;
			this.category = category;
		}
		
		public CategoryDTO(Category category) {
			this.id = category.getId();
			this.category = category.getCategory();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		@Override
		public String toString() {
			return "CategoryDTO [id=" + id + ", category=" + category + "]";
		}
}
