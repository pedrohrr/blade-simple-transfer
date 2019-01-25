package com.pedrohrr.simpletransfer.model;

import io.github.biezhi.anima.Model;

public abstract class AbstractModel extends Model {
    private Long id;

    public abstract Long getId();

    public abstract void setId(final Long id);
}
