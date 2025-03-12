package br.albatross.sysgarantia.repositories;

import java.util.Set;

import br.albatross.sysgarantia.models.Anexo;
import br.albatross.sysgarantia.models.Email;

public interface EmailRepository extends Repository<Email, Long> {

    Set<Anexo> findAnexosById(long id);

}
