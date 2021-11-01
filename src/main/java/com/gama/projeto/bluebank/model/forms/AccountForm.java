package br.com.gamastore.bluebank.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountForm {

	@NotNull
	@Size(min=4, max =4)
	public Integer agency;
	@NotNull
	@Size(min=4, max=10)
	public Integer number;
	@NotNull
	public double amount;
	
}
