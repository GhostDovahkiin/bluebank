package com.gama.projeto.bluebank.forms;

import com.gama.projeto.bluebank.model.BankAccount;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {

            @NotNull
            @NotEmpty
            @NotBlank
            public String specificID;
            public String name;
            public int age;
            public String phone;
            public String email;
            public BankAccount Account;

}
