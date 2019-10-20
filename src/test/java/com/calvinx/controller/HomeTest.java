package com.calvinx.controller;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

import com.calvinx.springwebflux.SpringwebfluxApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@ContextConfiguration(classes = SpringwebfluxApplication.class)
public class HomeTest {

  @Autowired
  ApplicationContext context;

  WebTestClient client;

  @Before
  public void setup() {
    this.client = WebTestClient
    .bindToApplicationContext(context)
    .apply(springSecurity())
    .configureClient()
    .build();
  }

  @Test
  public void home() {
    System.out.println("Hello");
    this.client.get().uri("/").exchange().expectStatus().isOk();
  }

}