package io.swagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.model.User;

public class AppUser implements UserDetails {

	private User user; // entity reference
	// private Collection<? extends GrantedAuthority> authorities; // to store
	// role details

	public AppUser(User user) {
		// super();
		this.user = user;
		// List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		// authorityList.add(new SimpleGrantedAuthority(user.getRole()));
		// this.authorities = authorityList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// return authorities;
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

		list.add(new SimpleGrantedAuthority(user.getRole()));

		return list;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUs_password();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUs_fs_name();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stu
		return true;
	}

}
