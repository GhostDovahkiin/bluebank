package com.gama.projeto.bluebank.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public class MessageResponseDTO {

    public String message;

}
