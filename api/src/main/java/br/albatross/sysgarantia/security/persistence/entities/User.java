package br.albatross.sysgarantia.security.persistence.entities;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

	@Id	@GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(length = 55, unique = true, nullable = false)
	private String username;

	@Lob
	@Column(length = 100 ,unique = false, nullable = false)
	private String password;

	@ManyToMany(fetch = LAZY)
	@JoinTable(
			name = "users_roles", 
			joinColumns =         @JoinColumn(name = "fk_user_id", referencedColumnName = "id"), 
			inverseJoinColumns =  @JoinColumn(name = "fk_role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
		this.roles.add(role);
	}

}
