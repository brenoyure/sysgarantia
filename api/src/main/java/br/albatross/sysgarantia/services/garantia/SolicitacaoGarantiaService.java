package br.albatross.sysgarantia.services.garantia;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;
import br.albatross.sysgarantia.repositories.ClienteRepository;
import br.albatross.sysgarantia.repositories.DescricaoProblemaRepository;
import br.albatross.sysgarantia.repositories.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 *
 * CDI Bean responsável por colocar os <code>Email</code>s de Garantia na fila
 * de mensagens
 *
 * @author breno.brito
 */
@ApplicationScoped
public class SolicitacaoGarantiaService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    DescricaoProblemaRepository descricaoProblemaRepository;

    @Transactional
    public void solicitarGarantia(@Valid DadosParaNovaSolicitacaoDeGarantia solicitacao) {
        //TODO
    }

}
