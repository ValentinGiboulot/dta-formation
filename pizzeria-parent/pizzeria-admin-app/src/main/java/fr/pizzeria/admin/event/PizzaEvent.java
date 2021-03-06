package fr.pizzeria.admin.event;

import java.time.LocalDateTime;

public class PizzaEvent {
	private String code;
	private LocalDateTime heureCreation;
	private TypeEvent type;

	public PizzaEvent(String code, LocalDateTime heureCreation, TypeEvent type) {
		super();
		this.code = code;
		this.heureCreation = heureCreation;
		this.type = type;
	}

	public TypeEvent getType() {
		return type;
	}

	public void setType(TypeEvent type) {
		this.type = type;
	}

	public LocalDateTime getHeureCreation() {
		return heureCreation;
	}

	public void setHeureCreation(LocalDateTime heureCreation) {
		this.heureCreation = heureCreation;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
