package com.pedrohrr.simpletransfer.config;

import com.blade.Blade;
import com.blade.Environment;
import com.blade.ioc.annotation.Bean;
import com.blade.loader.BladeLoader;
import com.blade.mvc.RouteContext;
import com.blade.mvc.handler.RouteHandler;
import io.github.biezhi.anima.Anima;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.h2.jdbcx.JdbcDataSource;

import java.io.IOException;
import java.util.Objects;

@Bean
public class ApplicationLoader implements BladeLoader {

    public void load(final Blade blade) {
        final JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(blade.environment().getOrNull("jdbc.url"));
        ds.setUser(blade.environment().getOrNull("jdbc.username"));
        ds.setPassword(blade.environment().getOrNull("jdbc.password"));
        Anima.open(ds);
    }

}