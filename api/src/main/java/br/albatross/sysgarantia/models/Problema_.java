package br.albatross.sysgarantia.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = Problema.class)
public abstract class Problema_ {

	public static volatile SingularAttribute<Problema, Short> id;
	public static volatile SingularAttribute<Problema, String> tipo;

}
