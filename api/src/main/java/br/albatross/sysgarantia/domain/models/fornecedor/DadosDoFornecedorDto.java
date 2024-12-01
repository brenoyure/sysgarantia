package br.albatross.sysgarantia.domain.models.fornecedor;

import java.util.HashSet;
import java.util.Set;

import br.albatross.sysgarantia.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.sysgarantia.persistence.entities.fornecedor.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosDoFornecedorDto implements DadosDoFornecedor {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String emails;
    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

    public DadosDoFornecedorDto(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.emails = fornecedor.getEmails();
        idsDosServicosDoFornecedorNoSistemaDeChamados.addAll(fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados());
    }

}
