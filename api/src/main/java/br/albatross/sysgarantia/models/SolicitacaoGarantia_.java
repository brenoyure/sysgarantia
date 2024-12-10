package br.albatross.sysgarantia.models;

import br.albatross.sysgarantia.models.SolicitacaoGarantia.Status;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SolicitacaoGarantia.class)
public abstract class SolicitacaoGarantia_ {
    public static volatile SingularAttribute<SolicitacaoGarantia, Long> id;
    public static volatile SingularAttribute<SolicitacaoGarantia, String> numeroDeSerie;
    public static volatile SingularAttribute<SolicitacaoGarantia, Cliente> cliente;
    public static volatile SingularAttribute<SolicitacaoGarantia, Fornecedor> fornecedor;
    public static volatile SingularAttribute<SolicitacaoGarantia, DescricaoProblema> descricaoProblema;
    public static volatile SingularAttribute<SolicitacaoGarantia, Status> status;
}
