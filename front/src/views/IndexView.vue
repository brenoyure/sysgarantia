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

        <div id="div-buscaNumeroDeSeriePeloIdentificadorEquipamento" v-if="sistemaDeChamadosDisponivel">
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
            <option v-for="descricaoProblema in descricaoProblemas" :value="descricaoProblema.id" :key="descricaoProblema.id">{{ descricaoProblema }}</option>
        </select>
<br>

        <div id="div-selectOne-chamado" v-if="sistemaDeChamadosDisponivel">
            <label for="selectOne-ticketChamado">(Opcional) Selecione o Ticket: </label>
            <select id="selectOne-ticketChamado" v-model="solicitacao.chamado_id">
                <option value="0">Selecione o Ticket</option>
                <option :value="chamado.id" v-for="chamado in chamados" :key="chamado.id">[Ticket#{{ chamado.numeroDoChamado }}] {{ chamado.titulo }} | Serviço: {{ chamado.nomeDoServico }} | Usuário: {{ chamado.nomeDoUsuario }}</option>
            </select>
            <br>
        </div>

        <button>Solicitar Garantia</button>

    </form>

        
</template>

<script>
import axios from '@/axios'
export default {
    name: 'IndexView',
    data() {
        return {
            sistemaDeChamadosDisponivel: false,
            chamados: null,
            identificadorDoEquipamento: null,
            clientes: null,
            fornecedores: null,
            descricaoProblemas: null,
            solicitacao: {
                numero_de_serie: null,
                cliente_id: 0,
                fornecedor_id: 0,
                chamado_id: null,
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
            this.solicitacao.chamado_id = null
            await axios
                    .get('/fornecedores/' + this.solicitacao.fornecedor_id)
                    .then(response => {
                        if (this.sistemaDeChamadosDisponivel) {
                            axios
                              .post('/sistemaDeChamados/chamados', response.data.idsDosServicosDoFornecedorNoSistemaDeChamados)
                              .then(response => this.chamados = response.data)
                        }
                    })
        },

        async solicitarGarantia() {
            alert(JSON.stringify(this.solicitacao))
            console.log(this.solicitacao)
            // await axios.post('/solicitacaoGarantia', {
            //     data: this.solicitacao
            // }).then((response) => {
            //     console.log(response)
            //     alert(response)
            // }).catch((error) => console.log(error))
        },

        async fetchFornecedores() {
            if (this.fornecedores == null) {
                await axios
                    .get('/fornecedores')
                    .then(response => this.fornecedores = response.data)
                    .catch(error   => console.log(error))
            }
        },

        async fetchClientes() {
            if (this.clientes == null) {
                await axios
                        .get('/clientes')
                        .then(response => this.clientes = response.data)
                        .catch(error => console.log(error))
            }

        },

        async fetchDescricaoProblemas() {
            await axios
                    .get('/descricaoProblemas')
                    .then(response => this.descricaoProblemas = response.data)
                    .catch(error => console.log(error))
        },

        async checkExternalHelpDeskSystemHealth() {
            await axios
                    .get('/sistemaDeChamados/up')
                    .then(() => this.sistemaDeChamadosDisponivel = true)
                    .catch(error => {
                        console.log(error)
                        this.solicitacao.chamado_id = null
                    })
        }

    },
    async mounted() {
        await this.checkExternalHelpDeskSystemHealth()
        await this.fetchClientes()
        await this.fetchFornecedores()
        await this.fetchDescricaoProblemas()
    }
  
}

</script>