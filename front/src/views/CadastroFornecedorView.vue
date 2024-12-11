<template>
 <h2>Administração / Fornecedores / Cadastro</h2>

<form style="display: grid;" @submit.prevent="cadastrarFornecedor()">

    <label class="form-label"   for="inputText-nome">Nome: </label>
    <input class="form-control" autofocus id="inputText-nome" v-model="fornecedor.nome" required>
<br>
    <label class="form-label"   for="inputText-emails">E-mails para Contato: </label>
    <input class="form-control" id="inputText-emails" v-model="fornecedor.emails" required>
<br>

    <div>
        <label >Serviços do Fornecedor no Sistema de Chamados:  </label>
        <div id="divSelectManyServicos" style="margin-top: 2vh; max-height: 50vh; overflow-x: hidden; overflow-y: scroll;">
            <!-- <div class="form-check" > -->
                <!-- <input type="checkbox" class="form-check-input" :value="servico.id" > -->
                <!-- <label class="form-label">{{ servico.nome }}</label> -->
            <!-- </div> -->
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
            // servicosDoFornecedor: null,
            fornecedor: {
                nome: null,
                emails: null,
                idsDosServicosDoFornecedorNoSistemaDeChamados: new Set()
            }
        }
    },

    methods: {
        async cadastrarFornecedor() {
            console.log(this.fornecedor)
            // await axios
            //         .post('/fornecedores', this.fornecedor)
            //         .then(response => console.log(response))
            //         .catch(error => console.log(error))
        },

        async fetchServicosDoFornecedorNoSistemaDeChamados() {
            await axios
                .get('/sistemaDeChamados/chamados/servicos')
                .then(response => {
                    // this.servicosDoFornecedor = response.data
                    this.fetchDivServicosDoFornecedor(response.data)
                })
                .catch(error => console.log(error))
        },

        fetchDivServicosDoFornecedor(servicos) {
            const divSelectManyServicos = document.querySelector('#divSelectManyServicos')
            servicos.forEach(servico => {

                const divFormCheck = document.createElement('div')
                divFormCheck.classList.add('form-check')

                const labelServico = document.createElement('label')
                labelServico.classList.add('form-label')
                labelServico.innerText = servico.nome

                const checkboxServico = document.createElement('input')
                checkboxServico.classList.add('form-check-input')
                checkboxServico.setAttribute('type', 'checkbox')
                checkboxServico.setAttribute('value', servico.id)

                divFormCheck.appendChild(checkboxServico)
                divFormCheck.appendChild(labelServico)

                divSelectManyServicos.appendChild(divFormCheck)

                checkboxServico.addEventListener('click', () => {
                    if (checkboxServico.checked) {
                        this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados.add(checkboxServico.value)
                    } else {
                        this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados.delete(checkboxServico.value)
                    }
                })

            })
            
        }

    },

    async created() {
        await this.fetchServicosDoFornecedorNoSistemaDeChamados()
    }

}
</script>