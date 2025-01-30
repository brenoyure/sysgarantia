package br.albatross.sysgarantia.models;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Email.class)
public abstract class Email_ {
    public static volatile SingularAttribute<Email, Long> id;
    public static volatile SingularAttribute<Email, String> remetente;
    public static volatile SingularAttribute<Email, String> destinatario;
    public static volatile SingularAttribute<Email, String> assunto;
    public static volatile SingularAttribute<Email, String> corpo;
    public static volatile SingularAttribute<Email, String> copiaPara;
    public static volatile SingularAttribute<Email, String> copiaOculta;
    public static volatile SetAttribute<Email, Anexo> anexos;
    public static volatile SingularAttribute<Email, SolicitacaoGarantia> solicitacaoGarantia;
}
