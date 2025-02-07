<template>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">
                <i class="bi bi-truck"></i>
                Fornecedor
            </th>
            <th scope="col">
                <i class="bi bi-envelope-at"></i>
                E-mails(s)
            </th>
            <th scope="col">
                <i class="bi bi-info-circle"></i>
                Informações
            </th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="fornecedor in fornecedores" :key="fornecedor.id">
            <td>{{ fornecedor.nome }}</td>
            <td>{{ fornecedor.emails }}</td>
            <td><RouterLink class="btn btn-outline-secondary" :to="`/administracao/fornecedores/cadastro?id=${fornecedor.id}`">Informações</RouterLink></td>
        </tr>
        <LoadingFromApi v-if="isFetchingFromApi" />
    </tbody>
</table>
</template>
<script>
import axios from '@/axios'
import LoadingFromApi from './LoadingFromApi.vue';
export default {
    name: 'TableFornecedoresView',
    components: { LoadingFromApi },
    data() {
        return {
            isFetchingFromApi: false,
            fornecedores: null
        }
    },

    async created() {
        this.isFetchingFromApi = true
        await axios
                .get('/fornecedores')
                .then(response => this.fornecedores = response.data)
                .finally(() => this.isFetchingFromApi = false)
    }
}
</script>