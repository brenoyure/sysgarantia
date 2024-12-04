<template>
 <h2>Administração / Fornecedores / Cadastro</h2>

<form style="display: grid;" @submit.prevent="cadastrarFornecedor()">

    <label class="form-label"   for="inputText-nome">Nome: </label>
        <input class="form-control" id="inputText-nome" v-model="fornecedor.nome" required>
<br>
    <label class="form-label"   for="inputText-emails">E-mails para Contato: </label>
    <input class="form-control" id="inputText-emails" v-model="fornecedor.emails" required>
<br>

    <div>
        <label >Serviços do Fornecedor no Sistema de Chamados:  </label>
        <div style="margin-top: 2vh; max-height: 50vh; overflow-x: hidden; overflow-y: scroll;">
            <div class="form-check" v-for="servico in servicosDoFornecedor" :key="servico.id" :value="servico.idDoServico">
                <input type="checkbox" class="form-check-input" v-model="fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados" >
                <label class="form-label">{{ servico.nomeDoServico }}</label>    
            </div>
        </div>
    </div>

<br>

    <button class="btn btn-outline-primary">Salvar</button>

<br>

</form>

</template>

<script>
import axios from '@/axios'
export default {
    name: 'CadastroFornecedorView',
    data() {
        return {
            servicosDoFornecedor: null,
            fornecedor: {
                nome: null,
                emails: null,
                idsDosServicosDoFornecedorNoSistemaDeChamados: []
            }
        }
    },

    methods: {
        async cadastrarFornecedor() {
            await axios
                    .post('/fornecedores', this.fornecedor)
                    .then(response => console.log(response))
                    .catch(error => console.log(error))
        },

        async fetchServicosDoFornecedorNoSistemaDeChamados() {
            await axios
                .get('/sistemaDeChamados/chamados/servicos')
                .then(response => {
                    this.servicosDoFornecedor = response.data
                })
                .catch(error => console.log(error))
        }
    },

    async mounted() {
        await this.fetchServicosDoFornecedorNoSistemaDeChamados()
    }

}
</script>