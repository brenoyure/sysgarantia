<template>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">Cliente</th>
            <th scope="col">Descrição</th>
            <th scope="col">Informações</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="cliente in clientes" :key="cliente.id">
            <td>{{ cliente.nome }}</td>
            <td>{{ cliente.descricao }}</td>
            <td><RouterLink class="btn btn-outline-secondary" :to="`/administracao/clientes/cadastro?id=${cliente.id}`">Informações</RouterLink></td>
        </tr>
        
    </tbody>
</table>
</template>
<script>
import axios from '@/axios'
export default {
    name: 'TableClientesView',
    data() {
        return {
            clientes: null
        }
    },
    async created() {
        await axios
                .get('/clientes')
                .then(response => {
                    this.clientes = response.data
                })
    }
}
</script>