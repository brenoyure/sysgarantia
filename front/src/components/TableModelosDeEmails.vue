<template>
    <div v-if="!modelosDeEmails.length && !isFetchingFromApi">
        <br>
            <p>Não existem Modelos de E-mail Cadastrados. Clique <RouterLink to="/administracao/emailstemplates/cadastro">aqui</RouterLink> para cadastrar um novo modelo</p>
        <br>
    </div>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                    <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
                </svg>
                Descrição
            </th>
            <th scope="col">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-left-text" viewBox="0 0 16 16">
                    <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
                    <path d="M3 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5M3 6a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 6m0 2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5"/>
                </svg>
                Assunto
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
    <tbody v-if="modelosDeEmails.length">
        <tr v-for="modelo in modelosDeEmails" :key="modelo.id">
            <td>{{ modelo.descricao }}</td>
            <td>{{ modelo.assunto }}</td>
            <td><RouterLink class="btn btn-outline-secondary" :to="`/administracao/emailstemplates/cadastro?id=${modelo.id}`">Informações</RouterLink></td>
        </tr>
    </tbody>
    <LoadingFromApi v-if="isFetchingFromApi" />
</table>

</template>

<script>
import axios from '@/axios'
import LoadingFromApi from './LoadingFromApi.vue';

export default {
    components: { LoadingFromApi },
    data() {
        return {
            isFetchingFromApi: false,
            modelosDeEmails: []
        }
    },
    async created() { 
        this.isFetchingFromApi = true
        await axios.get('/emailstemplates').then(response => this.modelosDeEmails = response.data).finally(() => this.isFetchingFromApi = false) 
    }
}

</script>