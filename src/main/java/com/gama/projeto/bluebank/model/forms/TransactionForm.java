package br.com.gamastore.bluebank.forms;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class TransactionForm {
	
	@NotNull
	public LocalDateTime date;
}
