package com.elective.web.api;

import com.elective.Entity.Base.ResponseResult;
import com.elective.Entity.User;
import com.elective.Entity.dto.UserDTOFactory;
import com.elective.Entity.rpo.LoginRPO;
import com.elective.constant.SystemConstant;
import com.elective.service.UserService;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static com.elective.constant.SystemConstant.USER_KEY;


@RestController(value = "user.api")
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private HttpSession httpSession;
    @Resource
    private UserDTOFactory userDTOFactory;

    @PostMapping(value = "/login")
    public ResponseResult<UserDTOFactory.UserDTO> login(@RequestBody @Valid LoginRPO loginRPO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),bindingResult.getFieldError().getDefaultMessage());
        }
        User user = userService.login(loginRPO.getNumber(), loginRPO.getPassword());
        if(user != null){
            httpSession.setAttribute(USER_KEY, user.getId());
            return new ResponseResult<>(HttpStatus.OK.value(), "Login successfully成功Controller", userDTOFactory.pojoToDTO(user));
        }else {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error失败Controller");
        }
    }



    @GetMapping("/all")
    public ResponseResult<List<User>> getAll(){

        return new ResponseResult<>(HttpStatus.OK.value(), "success", userService.getAll());
    }


    @PostMapping("/modify/password")
    public ResponseResult<Void> modifyPassword(@RequestBody String password){
        Boolean result = userService.modifyPassword(userService.getCurrentUser(httpSession), password);
        if(result){
            httpSession.removeAttribute(USER_KEY);
        }
        return new ResponseResult<>(HttpStatus.OK.value(),"modified successful修改成功Service");
    }

}
