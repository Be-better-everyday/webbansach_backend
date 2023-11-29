package com.example.webbansach_backend.service;

import com.example.webbansach_backend.dao.UserRepository;
import com.example.webbansach_backend.entity.Notice;
import com.example.webbansach_backend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    EmailService emailService;

    public ResponseEntity<?> registerUser (User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new Notice("Username has been existed"));
        }
        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new Notice("Email has been existed"));
        }
        String bcryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(bcryptPassword);
        user.setVerificationCode(createVerificationCode());
//        user.setEnabled(false);

        System.out.println(user);
        userRepository.save(user);

        sendVerificationEmail(user.getEmail(), user.getVerificationCode());
        return ResponseEntity.ok("Register successful!");
    }

    private String createVerificationCode()
    {
        return UUID.randomUUID().toString();
    }

    private void sendVerificationEmail (String email, String verificationCode)
    {
        String subject = "Active your account at WebBanSach";

        String verifyLink = "http://localhost:3000/verify/" + email + "/" + verificationCode;
        String text = "Vui lòng sử dụng mã sau để kich hoạt cho tài khoản <" + email + ">:<br/><h1>"
                + verificationCode + "</h1>";
        text += "<a href=\"[[verifyLink]]\">Verify your account</a>";
        text = text.replace("[[verifyLink]]", verifyLink);

        System.out.println("***");
        System.out.println(text);
        String fromAddress = System.getenv("EMAIL");
        emailService.sendMessage(fromAddress, email, subject, text);
    }

    public ResponseEntity<?> verify(String email, String verificationCode){
        User user = userRepository.findByEmail(email);

        if(user == null){
            return ResponseEntity.badRequest().body(new Notice("User is not exist"));
        } else if (user.isEnabled()){
            return ResponseEntity.badRequest().body(new Notice("User already is active!"));
        }else {
            boolean checkVerifucationCode = user.getVerificationCode().equals(verificationCode);

            if (checkVerifucationCode){
                user.setEnabled(true);
                user.setVerificationCode(null);
                userRepository.save(user);
                return ResponseEntity.ok("Verify successfully!");
            }else{
                return ResponseEntity.badRequest().body(new Notice("Wrong verificationCode"));
            }
        }
    }
}
