<template>

    <h2>Administração / Clientes / Cadastro</h2>

    <div v-if="errors">
        <ul>
            <li style="color: red" v-for="error in errors" :key="error">{{ error }}</li>
        </ul>
    </div>

    <form style="display: grid;" @submit.prevent="salvar()">

        <label class="form-label"   for="inputText-nome">Nome: </label>
        <input class="form-control" id="inputText-nome" v-model="cliente.nome" required autofocus>
<br>
        <label class="form-label"   for="inputText-descricao">Descrição: </label>
        <input class="form-control" id="inputText-descricao" v-model="cliente.descricao" required>
<br>

        <label class="form-label"   for="inputText-numerosParaContato">Números para Contato: </label>
        <input class="form-control" id="inputText-numerosParaContato" v-model="cliente.numerosParaContato" required>
<br>

        <label class="form-label"   for="inputText-emailsParaContato">E-mails para Contato: </label>
        <input class="form-control" id="inputText-emailsParaContato" v-model="cliente.emailsParaContato" required>
<br>

    <h3>Dados de Endereços</h3>

        <label class="form-label"   for="inputText-cep">CEP: </label>
        <input class="form-control" id="inputText-cep" @change="fetchEndereco" v-model="cliente.cep" minlength="8" maxlength="9" required>
<br>

        <label class="form-label"   for="inputText-logradouro">Rua ou Avenida: </label>
        <input class="form-control" id="inputText-logradouro" v-model="cliente.logradouro" disabled>
<br>

        <label class="form-label"   for="inputText-numero">Número: </label>
        <input class="form-control" id="inputText-numero" v-model="cliente.numero" required>
<br>

        <label class="form-label"   for="inputText-bairro">Bairro: </label>
        <input class="form-control" id="inputText-bairro" v-model="cliente.bairro" disabled>
<br>

        <label class="form-label"   for="inputText-bairro">Estado: </label>
        <input class="form-control" id="inputText-bairro" v-model="cliente.estado" disabled>
<br>

        <label class="form-label"   for="inputText-cidade">Cidade: </label>
        <input class="form-control" id="inputText-cidade" v-model="cliente.cidade" disabled>
<br>

    <h3>Horários</h3>

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
                            console.log(this.cliente)
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

    }
  
}

</script>

<style scoped>
label {
    font-weight: bold;
}
</style>