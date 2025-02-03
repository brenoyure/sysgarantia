package br.albatross.sysgarantia.services.emailtemplate;

import java.util.Optional;

import br.albatross.sysgarantia.dto.emailtemplate.DadosParaAtualizacaoDeEmailTemplate;
import br.albatross.sysgarantia.dto.emailtemplate.DadosParaCadastroDeEmailTemplate;
import br.albatross.sysgarantia.models.EmailTemplate;
import br.albatross.sysgarantia.repositories.EmailTemplateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

/**
<p>
    Você pode utilizar as variáveis abaixo para gerar <i>um corpo de e-mail template</i>, que pode ser reaproveitado para várias solicitações ou fornecedores.
</p>
<ul>
    <li>Número de Série do Equipamento: $numeroDeSerie</li>
    <li>Tipo do Problema: $problema.tipo</li>
    <li>Descrição Detalhada do Problema do Equipamento: $problema.descricao</li>
    <li>Número do Ticket no Sistema de Chamado: $chamado.numeroDoTicket</li>
    <li>Nome do Fornecedor: $fornecedor.nome</li>
    <li>Nome do Cliente: $cliente.nome</li>
    <li>Números para contato do Cliente: $cliente.numerosParaContato</li>
    <li>Emails para contato do Cliente: $cliente.emailsParaContato</li>
    <li>Endereço do Cliente: Rua ou Avenida: $cliente.endereco.logradouro</li>
    <li>Endereço do Cliente: Número: $cliente.endereco.numero</li>
    <li>Endereço do Cliente: Bairro: $cliente.endereco.bairro</li>
    <li>Endereço do Cliente: Estado: $cliente.endereco.estado</li>
    <li>Endereço do Cliente: Cidade: $cliente.endereco.cidade</li>
    <li>Endereço do Cliente: CEP: $cliente.endereco.cep</li>
    <li>Horários do Cliente: Início do Expediente: $cliente.horarios.inicioDoExpediente</li>
    <li>Horários do Cliente: Início do Almoço: $cliente.horarios.inicioDoAlmoco</li>
    <li>Horários do Cliente: Início do Fim: $cliente.horarios.fimDoAlmoco</li>
    <li>Horários do Cliente: Fim do Expediente: $cliente.horarios.fimDoExpediente</li>
</ul>
 */
@ApplicationScoped
public class EmailTemplateService {

    @Inject
    private EmailTemplateRepository repository;

    @Transactional
    public EmailTemplate cadastrar(@Valid DadosParaCadastroDeEmailTemplate dados) {

        if (repository.existsByDescricao(dados.getDescricao())) {
            throw new ValidationException("Já existe outro Email Modelo cadastrado com a descrição informada");
        }

        return repository.persist(new EmailTemplate(dados.getDescricao(), dados.getAssunto(), dados.getCorpoDoEmail()));
    }

    @Transactional
    public void atualizar(@Valid DadosParaAtualizacaoDeEmailTemplate dadosAtualizados) {

        if (repository.existsByDescricaoAndNotById(dadosAtualizados.getDescricao(), dadosAtualizados.getId())) {
            throw new ValidationException("Já existe outro Email Modelo cadastrado com a descrição informada");
        }

        repository.merge(new EmailTemplate(dadosAtualizados.getId(), 
                                           dadosAtualizados.getDescricao(), 
                                           dadosAtualizados.getAssunto(), 
                                           dadosAtualizados.getCorpoDoEmail()));
    }

    public Optional<EmailTemplate> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public boolean excluirPorId(Integer id) {

        return repository.deleteById(id);

    }

}
