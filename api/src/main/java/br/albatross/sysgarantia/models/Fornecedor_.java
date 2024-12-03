package br.albatross.sysgarantia.models;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Fornecedor.class)
public abstract class Fornecedor_ {

    public static volatile SingularAttribute<Fornecedor, Integer> id;
    public static volatile SingularAttribute<Fornecedor, String> nome;
    public static volatile SingularAttribute<Fornecedor, String> emails;
    public static volatile SetAttribute<Fornecedor, Integer> idsDosServicosDoFornecedorNoSistemaDeChamados;

}
