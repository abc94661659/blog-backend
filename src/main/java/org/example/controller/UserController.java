package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Result;
import org.example.entity.User;
import org.example.entity.UserInfo;
import org.example.service.EmailService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    @Transactional
    public Result register(@RequestBody Map<String, Object> request) {
        String email = (String) request.get("email");
        String code = (String) request.get("code");
        // 验证验证码
        if (!emailService.verifyCode(email, code)) {
            return Result.error("验证码验证失败");
        }
        User user = new User();
        user.setUsername((String) request.get("username"));
        user.setPassword((String) request.get("password"));
        user.setEmail(email);

        userService.save(user);
        return Result.success(user);
    }

    @PostMapping("/register/send-code")
    public String sendVerificationCodeForRegister(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        // 检查邮箱格式是否正确
        if (!isValidEmail(email)) {
            log.info("邮箱格式不正确");
            return "邮箱格式不正确";
        }
        // 检查邮箱是否已注册
        if (userService.isEmailExists(email)) {
            log.info("该邮箱已被注册");
            return "该邮箱已被注册";
        }
        try {
            emailService.sendVerificationCode(email);
            log.info("验证码已发送，请查收邮箱");
            return "验证码已发送，请查收邮箱";
        } catch (Exception e) {
            log.info("发送失败，请稍后再试：{}",e.getMessage());
            return "发送失败，请稍后再试";
        }
    }
    /**
     * 验证用户名和邮箱，并发送验证码
     * @param request 请求参数，包含用户名和邮箱
     * @return 发送结果
     */
    @PostMapping("/forgot-password/send-code")
    public String sendVerificationCodeForForgotPassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");

        // 若未传递用户名，只验证邮箱
        if (username == null || username.isEmpty()) {
            // 检查邮箱格式是否正确
            if (!isValidEmail(email)) {
                return "邮箱格式不正确";
            }
            try {
                emailService.sendVerificationCode(email);
                return "验证码已发送，请查收邮箱";
            } catch (Exception e) {
                return "发送失败，请稍后再试";
            }
        }

        // 验证用户名和邮箱是否匹配
        if (!userService.verifyUsernameAndEmail(username, email)) {
            return "用户名和邮箱不匹配";
        }
        // 检查邮箱格式是否正确
        if (!isValidEmail(email)) {
            return "邮箱格式不正确";
        }
        try {
            emailService.sendVerificationCode(email);
            return "验证码已发送，请查收邮箱";
        } catch (Exception e) {
            return "发送失败，请稍后再试";
        }
    }

    // 新增验证码验证接口
    @PostMapping("/verify-code")
    public boolean verifyCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        log.info("验证码验证请求参数: email={}, code={}", email, code);
        boolean result = emailService.verifyCode(email, code);
        log.info("验证码验证结果: {}", result);
        return result; // 直接返回之前验证的结果
    }

    private boolean isValidEmail(String email) {
        // 简单的邮箱格式验证
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        UserInfo info = userService.login(user);
        log.info("登录用户：{}",user);
        System.out.println(info);
        if(info != null){
            log.info("登录成功");
            return Result.success(info);
        }else {
            return Result.error("用户名或密码错误");
        }
    }
    @GetMapping("/{id}")
    public Result getUser(@PathVariable Integer id){
        log.info("获取用户信息：{}",id);
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PutMapping()
    public Result updateUser(@RequestBody User user){
        log.info("编辑用户信息：{}",user);
        userService.update(user);
        user = userService.getById(user.getId());
        return Result.success(user);
    }

    @DeleteMapping()
    public Result delete(Integer id){
        log.info("删除用户信息：{}",id);
        userService.deleteById(id);
        return Result.success();
    }


    @GetMapping("/me")
    public Result getCurrentUser(){
        return Result.success(userService.getCurrentUser());
    }


    @PostMapping("/check")
    @Transactional
    public Result checkUser(@RequestBody User user){
        log.info("检查用户：{}",user);
        User u  = userService.checkUser(user);
        if(u != null){
            log.info("检查成功");
            return Result.success(u);
        }else {
            return Result.error("用户名或邮箱不存在");
        }
    }

    @PostMapping("/forget-password")
    @Transactional
    public Result forgetPassword(@RequestBody User user){
        log.info("忘记密码：{}",user);
        boolean isSuccess = userService.updatePasswordByUsernameOrEmail(user);
        if(isSuccess){
            return Result.success("密码修改成功");
        }else {
            return Result.error("用户名或邮箱错误");
        }
    }

    @GetMapping("/admin")
    public Result getAdmin(){
        log.info("获取管理员信息");
        return Result.success(userService.getAdmin());
    }

    @GetMapping
    public Result findAll(){
        log.info("获取所有用户");
        List<User> list = userService.findAll();
        return Result.success(list);
    }

}
