package harena.dev.banking;

import harena.dev.banking.config.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementApplication.class, args);
/*		JwtUtil jwtUtil = new JwtUtil();
		String userId = "1";
		String role = "ROLE_ADMIN";
		String token = jwtUtil.generateToken(userId, role);
		System.out.println(token);*/
	}



}
