package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.models.Email;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailRepositoryImpl extends RepositoryImpl<Email, Long> implements EmailRepository {

    public EmailRepositoryImpl() {
        super(Email.class);
    }

}
