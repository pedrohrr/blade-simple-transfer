package com.pedrohrr.simpletransfer.data.client;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientUpdate extends ValidatedData {

    @NotNull
    private Long id;
    private String firstname;
    private String passport;
    private String lastname;

}
