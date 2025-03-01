package com.greatlearning.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	 @Id
	   @Column(name = "user_id")
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   @Column(name="username")
	   private String username;
	   @Column(name="password")
	   private String password;
	   
		private LocalDate AccountExpiryDate;
	    private int accountLockedStatus;//1-Active  0-Locked
		private LocalDate CredentialsExpiryDate;
			private int enabledStatus;//1-Enabled  0-Disabled

		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
	   
	   @ManyToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
	   @JoinTable(
			   name ="user_roles",
			   joinColumns = @JoinColumn(name ="user_id"),
			   inverseJoinColumns = @JoinColumn(name ="role_id"))
	       private List<Role>roles;
	   

}
