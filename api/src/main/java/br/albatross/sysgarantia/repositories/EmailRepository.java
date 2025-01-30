package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.models.Email;

public interface EmailRepository extends Repository<Email, Long> {

    Email getById(Long id);

}
