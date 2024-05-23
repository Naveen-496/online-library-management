package dev.reddy.olm.controller;

import dev.reddy.olm.dto.AdminUserRequest;
import dev.reddy.olm.dto.ApiResponse;
import dev.reddy.olm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse saveUser(@RequestBody AdminUserRequest userRequest) {
         canPerformThisOperation();
         var user = userService.saveAdmin(userRequest);
         return ApiResponse.getSuccessResponse(user);
     }
}
