
package com.pedrohrr.simpletransfer.model;

import io.github.biezhi.anima.annotation.Table;
import lombok.Data;

@Data
@Table(name = "clients")
public class Client extends AbstractModel {

    private Long id;
    private String firstname;
    private String passport;
    private String lastname;

}
