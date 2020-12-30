package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.Instructions;

public class InstructionsDTO {
		
		private int id;
		private String step;
		
		public InstructionsDTO() {
			super();
		}
		
		public InstructionsDTO(String step, int id) {
			this.id = id;
			this.step = step;
		}
		
		public InstructionsDTO(Instructions instruction) {
			this.id = instruction.getId();
			this.step = instruction.getStep();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getStep() {
			return step;
		}

		public void setStep(String step) {
			this.step = step;
		}

		@Override
		public String toString() {
			return "InstructionsDTO [id=" + id + ", step=" + step + "]";
		}
}
