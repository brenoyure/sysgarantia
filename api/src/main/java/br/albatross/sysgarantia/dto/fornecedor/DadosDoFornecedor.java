package br.albatross.sysgarantia.dto.fornecedor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.albatross.sysgarantia.models.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@AllArgsConstructor
public class DadosDoFornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String emails;
    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

    public DadosDoFornecedor(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.emails = fornecedor.getEmails();
        idsDosServicosDoFornecedorNoSistemaDeChamados.addAll(fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados());
    }

}
