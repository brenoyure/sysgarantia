package br.albatross.sysgarantia.models;

import java.time.LocalTime;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile SingularAttribute<Cliente, String> descricao;
    public static volatile SingularAttribute<Cliente, String> numerosParaContato;
    public static volatile SingularAttribute<Cliente, String> emailsParaContato;
    public static volatile SingularAttribute<Cliente, String> logradouro;
    public static volatile SingularAttribute<Cliente, String> numero;
    public static volatile SingularAttribute<Cliente, String> bairro;
    public static volatile SingularAttribute<Cliente, String> estado;
    public static volatile SingularAttribute<Cliente, String> cidade;
    public static volatile SingularAttribute<Cliente, String> cep;
    public static volatile SingularAttribute<Cliente, LocalTime> horarioInicioDoExpediente;
    public static volatile SingularAttribute<Cliente, LocalTime> horarioFimDoExpediente;
    public static volatile SingularAttribute<Cliente, Boolean> possuiHorarioDeAlmoco;
    public static volatile SingularAttribute<Cliente, LocalTime> inicioDoHorarioDeAlmoco;
    public static volatile SingularAttribute<Cliente, LocalTime> fimDoHorarioDeAlmoco;

}
