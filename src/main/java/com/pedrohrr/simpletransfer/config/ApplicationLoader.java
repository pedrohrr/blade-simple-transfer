package com.pedrohrr.simpletransfer.config;

import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.loader.BladeLoader;
import io.github.biezhi.anima.Anima;
import org.h2.jdbcx.JdbcDataSource;

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