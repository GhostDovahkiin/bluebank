package br.com.gamastore.bluebank.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {

	@NotNull
	public int age;
	@NotNull
	@Email
	public String email;
	@NotNull
	public String name;
	@NotNull
	@Size(min = 8, max=15)
	public String phone;
	
}
