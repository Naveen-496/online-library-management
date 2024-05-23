package dev.reddy.olm.controller;

import dev.reddy.olm.exception.ApiException;
import dev.reddy.olm.model.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


public class BaseController {

   protected SecurityUser getCurrentUser() {
       var authentication = SecurityContextHolder.getContext().getAuthentication();
       if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof SecurityUser)
           return (SecurityUser) authentication.getPrincipal();
       throw new ApiException("You aren't allowed to perform this operation");
   }
}
