<template>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">
                <i class="bi bi-buildings"></i>
                Cliente
            </th>
            <th scope="col">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                    <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
                </svg>
                Descrição
            </th>
            <th scope="col">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                    <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0"/>
                </svg>
                Informações
            </th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="cliente in clientes" :key="cliente.id">
            <td>{{ cliente.nome }}</td>
            <td>{{ cliente.descricao }}</td>
            <td><RouterLink class="btn btn-outline-secondary" :to="`/administracao/clientes/cadastro?id=${cliente.id}`">Informações</RouterLink></td>
        </tr>
        <LoadingFromApi v-if="isFetchingFromApi" />
    </tbody>
</table>
</template>
<script>
import axios from '@/axios'
import LoadingFromApi from './LoadingFromApi.vue';
export default {
    name: 'TableClientesView',
    components: { LoadingFromApi } ,
    data() {
        return {
            isFetchingFromApi: false,
            clientes: null
        }
    },
    async created() {
        this.isFetchingFromApi = true
        await axios
                .get('/clientes')
                .then(response => {
                    this.clientes = response.data
                })
                .finally(() => this.isFetchingFromApi = false)
    }
}
</script>