package br.albatross.sysgarantia.models;

import java.sql.Blob;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Anexo.class)
public abstract class Anexo_ {

    public static volatile SingularAttribute<Anexo, Long> id;
    public static volatile SingularAttribute<Anexo, String> nome;
    public static volatile SingularAttribute<Anexo, Blob> arquivo;
    public static volatile SingularAttribute<Anexo, Email> email;

}
