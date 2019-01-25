package com.pedrohrr.simpletransfer.data.client;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClientCreate extends ValidatedData {

    @NotBlank
    private String firstname;
    @NotBlank
    private String passport;
    @NotBlank
    private String lastname;

}
