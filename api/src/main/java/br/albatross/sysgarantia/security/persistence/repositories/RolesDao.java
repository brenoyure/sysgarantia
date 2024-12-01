package br.albatross.sysgarantia.security.persistence.repositories;

import java.util.List;

import br.albatross.sysgarantia.security.persistence.entities.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@RequestScoped
public class RolesDao {

    @Inject
    private EntityManager entityManager;

	public List<Role> getRoles() {
		return entityManager
				.createQuery("SELECT r FROM Role r", Role.class)
				.getResultList();
	}

	public List<Role> getRoles(Iterable<Integer> ids) {
		return entityManager
				.createQuery("SELECT r FROM Role r WHERE r.id IN ( ?1 )", Role.class)
				.setParameter(1, ids)
				.getResultList();
	}

}
