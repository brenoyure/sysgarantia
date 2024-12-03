<template>
 <h2>Administração / Fornecedores / Cadastro</h2>

<form style="display: grid;" @submit.prevent="cadastrarFornecedor()">

    <label class="form-label"   for="inputText-nome">Nome: </label>
        <input class="form-control" id="inputText-nome" v-model="fornecedor.nome" required>
<br>
    <label class="form-label"   for="inputText-emails">E-mails para Contato: </label>
    <input class="form-control" id="inputText-emails" v-model="fornecedor.emails" required>
<br>

    <label class="form-label"   for="checkMany-idsDosServicosDoFornecedorNoSistemaDeChamados">Serviços do Fornecedor no Sistema de Chamados:  </label>
    <select multiple class="form-control" id="checkMany-idsDosServicosDoFornecedorNoSistemaDeChamados" v-model="fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados" >
        <option v-for="servico in servicosDoFornecedor" :key="servico.id" :value="servico.idDoServico">{{ servico.nomeDoServico }}</option>
    </select>
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
            console.log(this.fornecedor)
            await axios
                    .post('/fornecedores', this.fornecedor)
                    .then(response => console.log(response))
                    .catch(error => console.log(error))
        },

        fetchServicosDoFornecedorNoSistemaDeChamados() {
            axios
                .get('/sistemaDeChamados/chamados/servicos')
                .then(response => {
                    this.servicosDoFornecedor = response.data
                })
                .catch(error => console.log(error))
        }
    },

    mounted() {
        this.fetchServicosDoFornecedorNoSistemaDeChamados()
    }

}
</script>