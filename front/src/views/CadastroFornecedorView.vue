<template>
 <h2>Administração / Fornecedores / Cadastro</h2>

<div v-if="errors">
    <ul>
        <li style="color: red" v-for="error in errors" :key="error">{{ error }}</li>
    </ul>
</div>

<form style="display: grid;" @submit.prevent="cadastrarFornecedor()">

    <label style="font-weight: bold;" class="form-label"   for="inputText-nome">Nome: </label>
    <input style="font-weight: bold;" class="form-control" autofocus id="inputText-nome" v-model="fornecedor.nome" required >
<br>
    <label class="form-label"   for="inputText-emails">E-mails para Contato: </label>
    <input class="form-control" id="inputText-emails" v-model="fornecedor.emails" required >
<br>

    <div>
        <label style="font-weight: bold;" >Serviços do Fornecedor no Sistema de Chamados:  </label>
        <div id="divSelectManyServicos" style="margin-top: 2vh; max-height: 50vh; overflow-x: hidden; overflow-y: scroll;">
            <div v-for="servico in servicosDoFornecedor" :key="servico.id" class="form-check" >
                <input type="checkbox" class="form-check-input" :value="servico.id" @click="setServico">
                <label class="form-label">{{ servico.nome }}</label>
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
            errors: new Set(),
            servicosSelecionados: new Set(),
            servicosDoFornecedor: null,
            fornecedor: {
                nome: null,
                emails: null,
                idsDosServicosDoFornecedorNoSistemaDeChamados: null
            }
        }
    },

    methods: {
        async cadastrarFornecedor() {
            this.errors.clear()
            this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados = Array.from(this.servicosSelecionados)
            await axios
                    .post('/fornecedores', this.fornecedor)
                    .then(response => console.log(response))
                    .catch(error => {
                        console.log(error)
                        if (error.status == 400) { 
                            this.exibirConstraintViolations(error) 
                        } else {
                            throw error
                        }
                    })
        },

        exibirConstraintViolations(error) {
            const messages = error.response.data
            messages.forEach(message => {
                this.errors.add(message)
            })
        },

        async fetchServicosDoFornecedorNoSistemaDeChamados() {
            await axios
                .get('/sistemaDeChamados/chamados/servicos')
                .then(response => {
                    this.servicosDoFornecedor = response.data
                })
                .catch(error => console.log(error))
        },

        setServico(event) {
            const checkboxServico = event.target
            if (checkboxServico.checked) {
                this.servicosSelecionados.add(checkboxServico.value)
            } else {
                this.servicosSelecionados.delete(checkboxServico.value)
            }

        }

    },

    async created() {
        await this.fetchServicosDoFornecedorNoSistemaDeChamados()
    }

}

        // fetchDivServicosDoFornecedor(servicos) {
        //     const divSelectManyServicos = document.querySelector('#divSelectManyServicos')
        //     servicos.forEach(servico => {

        //         const divFormCheck = document.createElement('div')
        //         divFormCheck.classList.add('form-check')

        //         const labelServico = document.createElement('label')
        //         labelServico.classList.add('form-label')
        //         labelServico.innerText = servico.nome

        //         const checkboxServico = document.createElement('input')
        //         checkboxServico.classList.add('form-check-input')
        //         checkboxServico.setAttribute('type', 'checkbox')
        //         checkboxServico.setAttribute('value', servico.id)

        //         divFormCheck.appendChild(checkboxServico)
        //         divFormCheck.appendChild(labelServico)

        //         divSelectManyServicos.appendChild(divFormCheck)

        //         checkboxServico.addEventListener('click', () => {
        //             if (checkboxServico.checked) {
        //                 this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados.add(checkboxServico.value)
        //             } else {
        //                 this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados.delete(checkboxServico.value)
        //             }
        //         })

        //     })
            
        // }
</script>
