package dal.twentythree.gft.controller;

import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dal.twentythree.gft.dao.UserAccountStatus;
import dal.twentythree.gft.dao.UserContactInfo;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;
import dal.twentythree.gft.repository.UsersDatabaseRepository;
import dal.twentythree.gft.repository.impl.UsersDatabaseRepositoryImpl;
import dal.twentythree.gft.service.impl.UserValidator;
import dal.twentythree.gft.util.LoggerUtil;

@Controller
public class UserController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";

	UsersDatabaseRepository userDBImpl = new UsersDatabaseRepositoryImpl();
	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());

	@GetMapping("/ping")
	public String ping() {
		return "I'm alive!";
	}

	@GetMapping({ "/", "/index" })
	public String homePage() {
		return "index";
	}

	@GetMapping("/login")
	public String displayLogIn() {
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String displaySignup() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createUser(@RequestParam(name = USERNAME) String bannerID,
			@RequestParam(name = PASSWORD) String password,
			@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
			@RequestParam(name = FIRST_NAME) String firstName, @RequestParam(name = LAST_NAME) String lastName,
			@RequestParam(name = EMAIL) String email) throws CopyCatMeDBConfigException, CourseGroupFormationException {

		UserContactInfo user = new UserContactInfo();
		user.setBannerId(bannerID);
		user.setEmailId(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		ModelAndView m;
		if (UserValidator.validateUser(user) && password.equals(passwordConfirm)) {
			Long userId = userDBImpl.createUser(user);

			// -- Send Confirmation Email
//			UserAccountStatus uas = new UserAccountStatus(email, UUID.randomUUID().toString());
//
//			String appUrl = request.getScheme() + "://" + request.getServerName();
//			if (userService.sendConfirmMail(uas, appUrl)) {
//				m = new ModelAndView("signup");
//				m.addObject("message",
//						"Please confirm the account by clicking on the link in your email at " + email);
//				return m;
//			}

			m = new ModelAndView("login");
			m.addObject("error", "Please confirm your email by clicking on the link in email");
		} else {
			m = new ModelAndView("signup");
			m.addObject("errorMessage", "Invalid data, please check your input values.");
		}
		return m;
	}
}