package com.pedrohrr.simpletransfer.data.account;

import com.pedrohrr.simpletransfer.data.ValidatedData;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class AccountCreate extends ValidatedData {

    @NotNull
    @Positive
    private Long client;
    @NotBlank
    @Length(min = 3, max = 3, message = "currency must be 3 characters")
    private String currency;
    @NotBlank
    private String iban;

}
