package com.techsam.onelogin.config;

import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.saml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${onelogin.metadata-path}")
  private String metadataPath;

  @Value("${onelogin.sp.protocol}")
  private String spProtocol;

  @Value("${onelogin.sp.host}")
  private String spHost;

  @Value("${onelogin.sp.path}")
  private String spBashPath;

  @Value("${onelogin.sp.key-store.file}")
  private String keyStoreFile;

  @Value("${onelogin.sp.key-store.password}")
  private String keyStorePassword;

  @Value("${onelogin.sp.key-store.alias}")
  private String keyStoreAlias;


  @Value("${onelogin.sp.protocol}")
  private String protocol;

  @Autowired
  private SAMLUserService samlUserService;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    //@formatter:off
    http
      .csrf().and()
      .authorizeRequests()
        .antMatchers("/saml/**").permitAll()
        .anyRequest().authenticated()
        .and()
      .apply(saml())
        .userDetailsService(samlUserService)
        .serviceProvider()
          .protocol(spProtocol)
          .hostname(spHost)
          .basePath(spBashPath)
          .keyStore()
            .storeFilePath(keyStoreFile)
            .keyPassword(keyStorePassword)
            .keyname(keyStoreAlias)
          .and()
        .and()
        .identityProvider()
          .metadataFilePath(metadataPath)
        .and()
    .and();
    //@formatter:on
  }
}
