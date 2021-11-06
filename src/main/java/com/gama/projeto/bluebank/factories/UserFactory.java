package com.gama.projeto.bluebank.factories;

import com.gama.projeto.bluebank.forms.UserForm;
import com.gama.projeto.bluebank.model.BankAccount;
import com.gama.projeto.bluebank.model.User;
import com.gama.projeto.bluebank.model.dto.UserDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

public class UserFactory {

    public static User Create(UserForm form){
        return new User(
            form.specificID,
            form.name,
            form.age,
            form.phone,
            form.email,
            form.Account);
    }

    public static UserDTO Create(User user){
        UserDTO dto = new UserDTO();
         dto.setSpecificID(user.getSpecificID());
         dto.setName(user.getName());
         dto.setAge(user.getAge());
         dto.setPhone(user.getPhone());
         dto.setEmail(user.getEmail());
         dto.setAccount(user.getAccount());

        return dto;
    }

}
