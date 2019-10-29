package co.cara.springwebflux.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@Profile("test")
@EnableR2dbcRepositories
public class H2Config extends AbstractR2dbcConfiguration {

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Override
  public ConnectionFactory connectionFactory() {
    H2ConnectionConfiguration config = H2ConnectionConfiguration
      .builder()
      .url(url)
      .username(username)
      .password(password)
      .build();
    return new H2ConnectionFactory(config);
  }

}