package io.swagger;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	public static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("Start");
		LOGGER.info(" " + userRepository.findByEmpId(username));
		User user = userRepository.findByEmpId(username).block();
		// LOGGER.debug("User:{}", user);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		LOGGER.info("End");
		AppUser ap = new AppUser(user);
		LOGGER.info("user is " + ap.getUsername());
		return ap;
	}
}
