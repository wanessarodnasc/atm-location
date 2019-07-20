package br.atm.location.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.atm.location.form.UserForm;

/**
* 
* @author Wanessa Nascimento
*
*/
@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profile = new ArrayList<>();

	public User() {
	}

	public User(String name, String email, String password, List<Profile> profile) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}

	public User(UserForm loginForm, String password) {
		this.email = loginForm.getEmail();
		this.password = password;
		this.profile = Arrays.asList(new Profile(loginForm.getProfile()));
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswordGenerated() {
		return password;
	}

	public void setPasswordGenerated(String password) {
		this.password = password;
	}

	public List<Profile> getProfile() {
		return profile;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profile;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
