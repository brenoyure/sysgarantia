package br.albatross.sysgarantia.dto.cliente;

import jakarta.validation.constraints.Positive;

public class DadosAtualizacaoCliente extends DadosParaCadastroDeCliente {

    @Positive
    private int id;

    public DadosAtualizacaoCliente() {

    }

    public DadosAtualizacaoCliente(DadosDoCliente cliente) {
        this.id = cliente.getId();
        setNome(cliente.getNome());
        setDescricao(cliente.getDescricao());
        setNumerosParaContato(cliente.getNumerosParaContato());
        setEmailsParaContato(cliente.getEmails());
        setLogradouro(cliente.getDadosDeEndereco().getLogradouro());
        setNumero(cliente.getDadosDeEndereco().getNumero());
        setBairro(cliente.getDadosDeEndereco().getBairro());
        setEstado(cliente.getDadosDeEndereco().getEstado());
        setCidade(cliente.getDadosDeEndereco().getCidade());
        setCep(cliente.getDadosDeEndereco().getCep());

        setHorarioInicioDoExpediente(cliente.getHorarios().getHorarioInicioDoExpediente());
        setHorarioFimDoExpediente(cliente.getHorarios().getHorarioFimDoExpediente());

        setPossuiHorarioDeAlmoco(cliente.getHorarios().possuiHorarioDeAlmoco());

        setInicioDoHorarioDeAlmoco(cliente.getHorarios().getInicioDoHorarioDeAlmoco());
        setFimDoHorarioDeAlmoco(cliente.getHorarios().getFimDoHorarioDeAlmoco());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
