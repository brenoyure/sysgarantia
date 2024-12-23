<template>

    <h2>Administração / Clientes / Cadastro</h2>

    <div v-if="errors">
        <ul>
            <li style="color: red" v-for="error in errors" :key="error">{{ error }}</li>
        </ul>
    </div>

    <form style="display: grid;" @submit.prevent="salvar()">

        <label class="form-label"   for="inputText-nome">
            <i class="bi bi-buildings"></i>
            Nome: 
        </label>
        <input class="form-control" id="inputText-nome" v-model="cliente.nome" required autofocus>
<br>
        <label class="form-label"   for="inputText-descricao">
            <i class="bi bi-info-circle"></i>
            Descrição: 
        </label>
        <input class="form-control" id="inputText-descricao" v-model="cliente.descricao" required>
<br>

        <label class="form-label"   for="inputText-numerosParaContato">
            <i class="bi bi-telephone-inbound"></i>
            Números para Contato: 
        </label>
        <input class="form-control" id="inputText-numerosParaContato" v-model="cliente.numerosParaContato" required>
<br>

        <label class="form-label"   for="inputText-emailsParaContato">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope-at" viewBox="0 0 16 16">
                <path d="M2 2a2 2 0 0 0-2 2v8.01A2 2 0 0 0 2 14h5.5a.5.5 0 0 0 0-1H2a1 1 0 0 1-.966-.741l5.64-3.471L8 9.583l7-4.2V8.5a.5.5 0 0 0 1 0V4a2 2 0 0 0-2-2zm3.708 6.208L1 11.105V5.383zM1 4.217V4a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v.217l-7 4.2z"/>
                <path d="M14.247 14.269c1.01 0 1.587-.857 1.587-2.025v-.21C15.834 10.43 14.64 9 12.52 9h-.035C10.42 9 9 10.36 9 12.432v.214C9 14.82 10.438 16 12.358 16h.044c.594 0 1.018-.074 1.237-.175v-.73c-.245.11-.673.18-1.18.18h-.044c-1.334 0-2.571-.788-2.571-2.655v-.157c0-1.657 1.058-2.724 2.64-2.724h.04c1.535 0 2.484 1.05 2.484 2.326v.118c0 .975-.324 1.39-.639 1.39-.232 0-.41-.148-.41-.42v-2.19h-.906v.569h-.03c-.084-.298-.368-.63-.954-.63-.778 0-1.259.555-1.259 1.4v.528c0 .892.49 1.434 1.26 1.434.471 0 .896-.227 1.014-.643h.043c.118.42.617.648 1.12.648m-2.453-1.588v-.227c0-.546.227-.791.573-.791.297 0 .572.192.572.708v.367c0 .573-.253.744-.564.744-.354 0-.581-.215-.581-.8Z"/>
            </svg>
            E-mails para Contato: 
        </label>
        <input class="form-control" id="inputText-emailsParaContato" v-model="cliente.emailsParaContato" required>
<br>
<hr>
    <h3>
        <i class="bi bi-signpost-split"></i>
        Endereço
    </h3>

        <label class="form-label"   for="inputText-cep">CEP: </label>
        <input class="form-control" id="inputText-cep" @change="fetchEndereco" v-model="cliente.cep" minlength="8" maxlength="9" required>
<br>

        <label class="form-label"   for="inputText-logradouro">Rua ou Avenida: </label>
        <input class="form-control" id="inputText-logradouro" v-model="cliente.logradouro">
<br>

        <label class="form-label"   for="inputText-numero">Número: </label>
        <input class="form-control" id="inputText-numero" v-model="cliente.numero" required>
<br>

        <label class="form-label"   for="inputText-bairro">Bairro: </label>
        <input class="form-control" id="inputText-bairro" v-model="cliente.bairro">
<br>

        <label class="form-label"   for="inputText-bairro">Estado: </label>
        <input class="form-control" id="inputText-bairro" v-model="cliente.estado">
<br>

        <label class="form-label"   for="inputText-cidade">Cidade: </label>
        <input class="form-control" id="inputText-cidade" v-model="cliente.cidade">
<br>
<hr>
    <h3>
        <i class="bi bi-alarm"></i>
        Horários
    </h3>

        <label class="form-label"   for="selectTime-horarioInicioDoExpediente">Início do Expediente: </label>
        <input class="form-control" type="time" id="selectTime-horarioInicioDoExpediente" v-model="cliente.horarioInicioDoExpediente" required>
<br>

        <label class="form-label"   for="selectTime-horarioFimDoExpediente">Fim do Expediente: </label>
        <input class="form-control" type="time" id="selectTime-horarioFimdoExpediente" v-model="cliente.horarioFimDoExpediente" required>
<br>

        <label class="form-label"   for="checkbox-possuiHorarioDeAlmoco">Possui Horário de Almoço: </label>
        <input class="form-check"   type="checkbox" id="checkbox-possuiHorarioDeAlmoco" v-model="cliente.possuiHorarioDeAlmoco">
<br>

        <div v-if="cliente.possuiHorarioDeAlmoco">
            <label class="form-label"   for="selectTime-inicioDoHorarioDeAlmoco">Início do Almoço: </label>
            <input class="form-control" type="time" id="selectTime-inicioDoHorarioDeAlmoco" v-model="cliente.inicioDoHorarioDeAlmoco" >
<br>
            <label class="form-label"   for="selectTime-fimDoHorarioDeAlmoco">Fim do Almoço: </label>
            <input class="form-control" type="time" id="selectTime-fimDoHorarioDeAlmoco" v-model="cliente.fimDoHorarioDeAlmoco" >
        </div>
<br>

        <button class="btn btn-outline-primary">Salvar</button>
        <button v-if="cliente.id" type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exclueClienteModal">Excluir</button>
        <div class="modal fade" id="exclueClienteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmação de Exclusão</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <label>Confirmar Exclusão do Cliente: <span style="text-transform: capitalize; font-weight: bold;">{{ cliente.nome }}</span> ?</label>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="excluirCliente()">Confirmar Exclusão</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
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
    name: 'CadastroClienteView',
    data() {
        return {
            errors: new Set(),
            cliente: {
                nome: null,
                descricao: null,
                numerosParaContato: null,
                emailsParaContato: null,
                logradouro: null,
                numero: null,
                bairro: null,
                estado: null,
                cidade: null,
                cep: null,
                horarioInicioDoExpediente: null,
                horarioFimDoExpediente: null,
                possuiHorarioDeAlmoco: false,
                inicioDoHorarioDeAlmoco: null,
                fimDoHorarioDeAlmoco: null
            }
        }
    },
    methods: {
        async salvar() {
            this.errors.clear()
            if (this.cliente.id) {
                await this.atualizarCliente()
            } else {
                await this.cadastrarCliente()
            }
        },
        async cadastrarCliente() {
            await axios
                    .post('/clientes', this.cliente)
                    .then(response => {
                        alert(`Cliente ${this.cliente.nome} cadastrado com sucesso`)
                        window.location.href = window.location.href.concat(`?id=${response.data.id}`)
                    })
                    .catch(error => {
                        console.log(error)
                        if (error.status == 400) { 
                            this.exibirConstraintViolations(error) 
                        } else {
                            throw error
                        }
                    })
        },

        async atualizarCliente() {
            await axios
                    .put('/clientes', this.cliente)
                    .then(() => {
                        alert(`Cadastro do Cliente ${this.cliente.nome} atualizado com sucesso`)
                    })
                    .catch(error => {
                        console.log(error)
                        if (error.status == 400) { 
                            this.exibirConstraintViolations(error) 
                        } else {
                            throw error
                        }
                    })
        },

        async excluirCliente() {
            await axios
                    .delete(`/clientes/${this.cliente.id}`)
                    .then(() => {
                        alert(`Cliente ${this.cliente.nome} excluído com sucesso`)
                        this.$router.push('/administracao/clientes/listagem')
                    })
                    .catch(error => {
                        console.log(error)
                        if (error.status == 400) { 
                            this.exibirConstraintViolations(error) 
                        } else {
                            throw error
                        }
                    })
        },

        async fetchEndereco() {
            if (this.cliente.cep != null && this.cliente.cep.trim() != '') {
                await axios
                        .get('https://viacep.com.br/ws/' + this.cliente.cep + '/json/')
                        .then((response) => {
                            const endereco = response.data
                            this.cliente.cep = endereco.cep
                            this.cliente.logradouro = endereco.logradouro
                            this.cliente.bairro = endereco.bairro
                            this.cliente.estado = endereco.estado
                            this.cliente.cidade = endereco.localidade
                        }).catch(error => {
                            this.resetEnderecoCliente()
                            console.log(error)
                        })
            } else { this.resetEnderecoCliente() }

        },
        resetEnderecoCliente() {
            this.cliente.cep = null
            this.cliente.logradouro = null
            this.cliente.bairro = null
            this.cliente.estado = null
            this.cliente.cidade = null            
        },

        exibirConstraintViolations(error) {
            const messages = error.response.data
            if (Array.isArray(messages)) {
                messages.forEach(message => {
                    this.errors.add(message)
                })
            } else {
                this.errors.add(messages.error)
            }
        }

    },

    async created() {
        const clienteId = parseInt(new URLSearchParams(window.location.search).get('id'))
        if (Number.isInteger(clienteId) && clienteId > 0) {
            await axios
                    .get(`/clientes/${clienteId}`)
                    .then(response => this.cliente = response.data)
        }

    },

    mounted() {
        document.getElementById('inputText-nome').focus()
    }
  
}

</script>

<style scoped>
label {
    font-weight: bold;
}
</style>