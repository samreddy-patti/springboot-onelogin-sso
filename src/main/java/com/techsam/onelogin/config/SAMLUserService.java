package com.techsam.onelogin.config;

import com.techsam.onelogin.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SAMLUserService implements SAMLUserDetailsService {

  @Override
  public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
    return new User(credential.getNameID().getValue());
  }
}
