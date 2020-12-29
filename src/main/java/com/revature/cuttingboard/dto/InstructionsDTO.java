package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.Instructions;

public class InstructionsDTO {

		private String step;
		
		public InstructionsDTO() {
			super();
		}
		
		public InstructionsDTO(String step) {
			this.step = step;
		}
		
		public InstructionsDTO(Instructions instruction) {
			this.step = instruction.getStep();
		}

		public String getStep() {
			return step;
		}

		public void setStep(String step) {
			this.step = step;
		}

		@Override
		public String toString() {
			return "InstructionsDTO [step=" + step + "]";
		}
		
		
}
