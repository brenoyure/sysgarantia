package br.albatross.sysgarantia.security.persistence.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.security.models.DadosParaListagemDoUsuarioDto;
import br.albatross.sysgarantia.security.persistence.entities.User;
import io.quarkus.hibernate.orm.PersistenceUnit;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

@RequestScoped
public class UsersDao {

    @Inject
    @PersistenceUnit("sysgarantia-pu")
    EntityManager entityManager;

	public DadosParaListagemDoUsuarioDto persist(User user) {
		entityManager.persist(user);
		return new DadosParaListagemDoUsuarioDto(user);
	}

	public boolean existsByUsername(String username) {
		try {
			return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.username = ?1)", Boolean.class)
					.setParameter(1, username)
					.getSingleResult();
		} catch (NoResultException e) {	return false; }
	}

	public boolean existsByUsernameForMerge(int id, String newUsername) {
		try {
			return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.id != ?1 AND u.username = ?2)", Boolean.class)
					.setParameter(1, id)
					.setParameter(2, newUsername)
					.getSingleResult();
		} catch (NoResultException e) {	return false; }
	}	

	public List<DadosParaListagemDoUsuarioDto> findAll() {
		var usersResultStream = entityManager
									.createQuery("SELECT u FROM User u INNER JOIN FETCH u.roles ORDER BY u.username", User.class)
									.getResultStream();

		List<DadosParaListagemDoUsuarioDto> dtoResultList = new ArrayList<>();

		usersResultStream
			.map(DadosParaListagemDoUsuarioDto::new)
			.forEach(dtoResultList::add);

		return dtoResultList;

	}

	public Optional<DadosParaListagemDoUsuarioDto> findById(int id) {

		try {

			User userEntityResult = entityManager
										.createQuery("SELECT u FROM User u INNER JOIN FETCH u.roles WHERE u.id = ?1", User.class)
										.setParameter(1, id)
										.getSingleResult();
	
			return Optional
					.of(new DadosParaListagemDoUsuarioDto(userEntityResult));


		} catch (NoResultException e) {

			return Optional.empty();
		
		}

	}

	public Optional<User> getReference(int id) {

		try {

			return Optional.ofNullable(entityManager.getReference(User.class, id));

		}

		catch(EntityNotFoundException e) {

			return Optional.empty();

		}

	}

	public DadosParaListagemDoUsuarioDto atualizar(User user) {

		user = entityManager.merge(user);
		return new DadosParaListagemDoUsuarioDto(user);

	}

	public void remover(User userReference) {

		entityManager.remove(userReference);

	}

}
