package com.pedrohrr.simpletransfer.data.client;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ClientUpdate extends ValidatedData {

    @NotNull
    @Positive
    private Long id;
    private String firstname;
    private String passport;
    private String lastname;

}
