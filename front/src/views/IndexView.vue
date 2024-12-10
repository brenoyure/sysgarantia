<template>

    <h2>Serviço de Garantia</h2>

    <form style="display: grid;" @submit.prevent="solicitarGarantia">

        <label class="form-label" for="selectOne-cliente">Selecione o Cliente: </label>
        <select class="form-select" id="selectOne-cliente" v-model="solicitacao.cliente_id" required>
            <option value="0">Selecione o Cliente</option>
            <option v-for="cliente in clientes" :value="cliente.id" :key="cliente.id">{{ cliente.nome }} - {{ cliente.descricao }}</option>
        </select>
<br>

        <label class="form-label" for="selectOne-fornecedor">Selecione o Fornecedor: </label>
        <select class="form-select" id="selectOne-fornecedor" v-model="solicitacao.fornecedor_id" @change="listarChamadosDoFornecedor()" required>
            <option value="0">Selecione o Fornecedor</option>
            <option v-for="fornecedor in fornecedores" :value="fornecedor.id" :key="fornecedor.id">{{ fornecedor.nome }}</option>
        </select>
<br>

        <div id="div-buscaNumeroDeSeriePeloIdentificadorEquipamento">
            <label class="form-label"   for="inputText-identificadorEquipamento">(Opcional) Buscar Número de Série pelo Identificador: </label>
            <input class="form-control" id="inputText-identificadorEquipamento" placeholder="Por exemplo, número de patrimônio ..." v-model="identificadorDoEquipamento" @change="buscarNumeroDeSeriePeloIdentificadorDoEquipamentoNoSistemaDeChamados()">
            <br>
        </div>

        <label class="form-label"   for="inputText-numeroDeSerieEquipamento">Número de Série: </label>
        <input class="form-control" id="inputText-numeroDeSerieEquipamento" v-model="solicitacao.numero_de_serie" placeholder="ex. AVCLX486" required>
<br>

        <label class="form-label" for="selectOne-descricaoProblema">Selecione o Problema: </label>
        <select class="form-select" id="selectOne-descricaoProblema" v-model="solicitacao.descricao_problema_id" required>
            <option value="0">Selecione o Problema</option>
            <option v-for="descricaoProblema in descricaoProblemas" :value="descricaoProblema.id" :key="descricaoProblema.id">{{ descricaoProblema.problema.tipo }} | {{ descricaoProblema.descricaoResumida }}</option>
        </select>
<br>

        <label for="selectOne-ticketChamado">Selecione o Ticket: </label>
        <select class="form-select" id="selectOne-ticketChamado" v-model="solicitacao.chamado_id" required>
            <option value="0">Selecione o Ticket</option>
            <option :value="chamado.id" v-for="chamado in chamados" :key="chamado.id">Nº do Ticket: {{ chamado.numeroDoChamado }} | Título: {{ chamado.titulo }} | Serviço: {{ chamado.nomeDoServico }} | Usuário: {{ chamado.nomeDoUsuario }}</option>
        </select>
<br>

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Solicitar Garantia
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Nova Solicitação de Garantia</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <h4>Cliente</h4>
                        <p><b>Solicitante:</b> JFCE - Justiça Federal no Ceará</p>
                        <p><b>Número de Série:</b> AVCLX486</p>
                    </div>
                    <div>
                        <h4>Fornecedor</h4>
                        <p><b>Nome: </b>DATEN</p>
                        <p><b>E-mail(s): </b>atendimento.governo@daten.com.br, suporte5@daten.com.br</p>
                    </div>
                    <div>
                        <h4>Sistema de Chamados</h4>
                        <p><b>Chamado: </b>2024120382000486</p>
                        <p><b>Título: </b>Monitor Não Liga</p>
                        <p><b>Serviço: </b>Monitor DATEN</p>
                        <p><b>Usuário: </b>andre.santos </p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-outline-primary">Confirmar Solicitação</button>
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
                </div>
            </div>
        </div>
<br>

    </form>

        
</template>

<script>
import axios from '@/axios'
export default {
    name: 'IndexView',
    data() {
        return {
            chamados: null,
            identificadorDoEquipamento: null,
            clientes: null,
            fornecedores: null,
            descricaoProblemas: null,
            solicitacao: {
                numero_de_serie: null,
                cliente_id: 0,
                fornecedor_id: 0,
                chamado_id: 0,
                descricao_problema_id: 0,
            }
        }
    },
    methods: {
        async buscarNumeroDeSeriePeloIdentificadorDoEquipamentoNoSistemaDeChamados() {
            await axios
                    .get('/sistemaDeChamados/inventario/' + this.identificadorDoEquipamento)
                    .then(response => this.solicitacao.numero_de_serie = response.data)
                    .catch(error => console.log(error))
        },

        async listarChamadosDoFornecedor() {
            this.solicitacao.chamado_id = 0
            await axios
                .get('/fornecedores/' + this.solicitacao.fornecedor_id)
                .then(response => {
                    const fornecedor = response.data
                    if (fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados.length > 0) {
                        axios
                            .post('/sistemaDeChamados/chamados', fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados)
                            .then(response => this.chamados = response.data)
                    }
                })
        },

        solicitarGarantia() {
            this.solicitacao.chamado_id = 3
            axios.post('/solicitacaoGarantia', this.solicitacao, {
                headers: { 'Content-Type': 'multipart/form-data' }
            })
                .then((response) => {
                console.log(response)
                alert(response)
            }).catch((error) => console.log(error))
        },

        async fetchFornecedores() {
            await axios
                .get('/fornecedores')
                .then(response => this.fornecedores = response.data)
                .catch(error => console.log(error))
        },

        async fetchClientes() {
            await axios
                .get('/clientes')
                .then(response => this.clientes = response.data)
                .catch(error => console.log(error))

        },

        async fetchDescricaoProblemas() {
            await axios
                    .get('/descricaoProblemas')
                    .then(response => this.descricaoProblemas = response.data)
                    .catch(error => console.log(error))
        },

    },
    async mounted() {
        await this.fetchClientes()
        await this.fetchFornecedores()
        await this.fetchDescricaoProblemas()
    }
  
}

</script>