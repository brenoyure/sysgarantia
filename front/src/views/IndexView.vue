<template>

    <h2>Serviço de Garantia</h2>
    
    <form style="display: grid;" @submit.prevent="solicitarGarantia">

        <label class="form-label" for="selectOne-cliente">Selecione o Cliente: </label>
        <select class="form-select" id="selectOne-cliente" required>
            <option>Selecione o Cliente</option>
            <option v-for="cliente in clientes" :value="solicitacao.cliente_id" :key="cliente.id">{{ cliente.nome }} - {{ cliente.descricao }}</option>
        </select>
<br>

        <label class="form-label" for="selectOne-fornecedor">Selecione o Fornecedor: </label>
        <select class="form-select" id="selectOne-fornecedor" required @click="fetchFornecedores" >
            <option>Selecione o Fornecedor</option>
            <option v-for="fornecedor in fornecedores" :value="solicitacao.fornecedor_id" :key="fornecedor.id">{{ fornecedor.nome }}</option>
        </select>
<br>

        <label class="form-label"   for="inputText-identificadorEquipamento">(Opcional) Buscar Número de Série pelo Identificador: </label>
        <input class="form-control" id="inputText-identificadorEquipamento" placeholder="Por exemplo, número de patrimônio ...">
<br>
        <label class="form-label"   for="inputText-numeroDeSerieEquipamento">Número de Série: </label>
        <input class="form-control" id="inputText-numeroDeSerieEquipamento" required>
<br>

        <label for="selectOne-ticketChamado">(Opcional) Selecione o Ticket: </label>
        <select id="selectOne-ticketChamado">
            <option>Selecione o Ticket</option>
            <option value="1">xxx</option>
            <option value="2">yyy</option>
        </select>
<br>

        <button>Solicitar Garantia</button>

    </form>

        
</template>

<script>
import axios from '@/axios'
export default {
    name: 'IndexView',
    data() {
        return {
            clientes: null,
            fornecedores: null,
            solicitacao: {
                numero_de_serie: null,
                cliente_id: null,
                fornecedor_id: null,
                chamado_id: null,
                descricao_problema_id: null,
            }
        }
    },
    methods: {
        async solicitarGarantia() {
            alert(this.solicitacao.cliente_id)
            await axios.post('/solicitacaoGarantia', {
                data: this.solicitacao
            }).then((response) => {
                console.log(response)
                alert(response)
            }).catch((error) => console.log(error))
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

        }

    },
  
}

</script>