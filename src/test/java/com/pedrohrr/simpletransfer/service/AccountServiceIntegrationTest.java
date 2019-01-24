package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Inject;
import com.blade.test.BladeApplication;
import com.blade.test.BladeTestRunner;
import com.pedrohrr.simpletransfer.TestApplication;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@RunWith(BladeTestRunner.class)
@BladeApplication(TestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceIntegrationTest {

    @Inject
    private AccountService service;

    @Test
    public void test01create() {
    }

    @Test
    public void test02update() {
    }

    @Test
    public void test03findById() {
    }

    @Test
    public void test04delete() {
    }

    @Test
    public void test05findByClientId() {
    }

}