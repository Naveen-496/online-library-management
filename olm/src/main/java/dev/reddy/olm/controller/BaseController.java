package dev.reddy.olm.controller;

import dev.reddy.olm.exception.ApiException;
import dev.reddy.olm.model.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class BaseController {

    private static final Map<String, Integer> roleToScoreMap = new HashMap<>();

    static {
        roleToScoreMap.put("User", 10);
        roleToScoreMap.put("Admin", 2);
        roleToScoreMap.put("SuperAdmin", 1);
        roleToScoreMap.put("Manager", 3);
    }

   protected SecurityUser getCurrentUser() {
       var authentication = SecurityContextHolder.getContext().getAuthentication();
       if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof SecurityUser)
           return (SecurityUser) authentication.getPrincipal();
       throw new ApiException("You aren't allowed to perform this operation");
   }

   protected boolean canPerformThisOperation() {
        var securityUser = getCurrentUser();
        return roleToScoreMap.get(securityUser.getRole()) >= 3;
   }
}
