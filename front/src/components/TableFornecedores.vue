<template>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">Fornecedor</th>
            <th scope="col">E-mails(s)</th>
            <th scope="col">Informações</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="fornecedor in fornecedores" :key="fornecedor.id">
            <td>{{ fornecedor.nome }}</td>
            <td>{{ fornecedor.emails }}</td>
            <td><RouterLink class="btn btn-outline-secondary" :to="`/administracao/fornecedores/cadastro?id=${fornecedor.id}`">Informações</RouterLink></td>
        </tr>
        
    </tbody>
</table>
</template>
<script>
import axios from '@/axios'
export default {
    name: 'TableFornecedoresView',
    data() {
        return {
            fornecedores: null
        }
    },

    async created() {
        await axios
                .get('/fornecedores')
                .then(response => this.fornecedores = response.data)
    }
}
</script>