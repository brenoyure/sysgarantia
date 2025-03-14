<template>
 <h2>Administração / Fornecedores / {{ fornecedor.id ? 'Atualização' : 'Cadastro' }}</h2>

<div v-if="errors">
    <ul>
        <li style="color: red" v-for="error in errors" :key="error">{{ error }}</li>
    </ul>
</div>

<div v-if="isLoadingExistingFornecedorFromApi">
    <LoadingFromApi />
</div>

<form style="display: grid;" @submit.prevent="salvar()">

    <label class="form-label"   for="inputText-nome">
        <i class="bi bi-truck"></i>
        Nome: 
    </label>
    <input class="form-control" autofocus id="inputText-nome" v-model="fornecedor.nome" required >
<br>
    <label class="form-label"   for="inputText-emails">
        <i class="bi bi-envelope-at"></i>
        E-mails para Contato: 
    </label>
    <input class="form-control" id="inputText-emails" v-model="fornecedor.emails" required >
<br>

    <div>
        <div>
            <label style="font-weight: bold;" for="inputSearch-servicos">
                <i class="bi bi-search"></i>
                Pesquisar Serviços: 
            </label>
            <input style="display: inline-flex; max-width: max-content; margin-left: 5px;" class="form-control" id="inputSearch-servicos">
        </div>
        <div>
            <label style="font-weight: bold;" for="checkBox-showOnlySelectedServices">Mostrar apenas selecionados: </label>
            <input style="margin-left: 5px;" type="checkbox" id="checkBox-showOnlySelectedServices" @click="toggleShowOrHideSelectedFornecedorServices()">
        </div>
        <div>
            <label style="font-weight: bold;">Serviços do Fornecedor no Sistema de Chamados:  </label>
            <div id="divSelectManyServicos" style="margin-top: 2vh; max-height: 50vh; overflow-x: hidden; overflow-y: scroll;">
                <!-- <div v-for="servico in servicosDoFornecedor" :key="servico.id" class="form-check" >
                    <input type="checkbox" class="form-check-input" :value="servico.id" @click="setServico">
                    <label class="form-label">{{ servico.nome }}</label>
                </div> -->
            </div>
        </div>
    </div>

<br>

    <button class="btn btn-outline-primary">Salvar</button>
<br>

    <button v-if="fornecedor.id" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#exclueFornecedorModal">Excluir</button>
    <div class="modal fade" id="exclueFornecedorModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmação de Exclusão</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <label>Confirmar Exclusão do Fornecedor: <span style="text-transform: capitalize; font-weight: bold;">{{ fornecedor.nome }}</span> ?</label>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="excluirFornecedor()">Confirmar Exclusão</button>
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
import LoadingFromApi from '@/components/LoadingFromApi.vue';
import router from '@/router'
import { useRoute } from 'vue-router'

export default {
    name: 'CadastroFornecedorView',
    components: { LoadingFromApi },
    data() {
        return {
            isLoadingExistingFornecedorFromApi: false,
            errors: new Set(),
            servicosSelecionados: new Set(),
            fornecedor: {
                nome: null,
                emails: null,
                idsDosServicosDoFornecedorNoSistemaDeChamados: null
            }
        }
    },

    methods: {
        async salvar() {
            this.errors.clear()
            this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados = Array.from(this.servicosSelecionados)
            if (this.fornecedor.id) {
                await this.atualizarFornecedor()
            } else {
                await this.cadastrarFornecedor()
            }
        },

        async cadastrarFornecedor() {
            await axios
                    .post('/fornecedores', this.fornecedor)
                    .then(() => {
                        this.showToast('success', 'Fornecedor cadastrado com sucesso')
                        router.push('/administracao/fornecedores/listagem')
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

        async atualizarFornecedor() {
            await axios
                    .put('/fornecedores', this.fornecedor)
                    .then(() => {
                        this.showToast('success', `Fornecedor ${this.fornecedor.nome} atualizado com sucesso`)
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

        async excluirFornecedor() {
            await axios
                    .delete(`/fornecedores/${this.fornecedor.id}`)
                    .then(() => {
                        this.showToast('success', `Fornecedor ${this.fornecedor.nome} excluído com sucesso`)
                        this.$router.push('/administracao/fornecedores/listagem')
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

        exibirConstraintViolations(error) {
            const messages = error.response.data
            if (Array.isArray(messages)) {
                messages.forEach(message => {
                    this.showToast('error', message)
                })
            } else {
                this.showToast('error', messages.error)
            }
        },

        async fetchServicosDoFornecedorNoSistemaDeChamados() {
            await axios
                .get('/sistemaDeChamados/chamados/servicos')
                .then(response => {
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

                if (this.servicosSelecionados.has(servico.id)) {
                    checkboxServico.checked = true
                }

                divFormCheck.appendChild(checkboxServico)
                divFormCheck.appendChild(labelServico)

                divSelectManyServicos.appendChild(divFormCheck)

                checkboxServico.addEventListener('click', () => {
                    if (checkboxServico.checked) {
                        this.servicosSelecionados.add(parseInt(checkboxServico.value))
                    } else {
                        this.servicosSelecionados.delete(parseInt(checkboxServico.value))
                    }
                })

            })

            /**
             * Mostrar apenas os serviços que o fornecedor possui (selecionados), caso o fornecedor 
             * possua Id, ou seja, caso esteja atualizando um fornecedor existente.
             */
            if (this.fornecedor.id) {
                document.getElementById('checkBox-showOnlySelectedServices').checked = true
                this.toggleShowOrHideSelectedFornecedorServices()
            }

        },

        toggleShowOrHideSelectedFornecedorServices() {
            const checkBoxShowOnlySelectedServices = document.getElementById('checkBox-showOnlySelectedServices')
            const checkBoxFornecedorServices = document.getElementById('divSelectManyServicos')
            for (let i = 0; i < checkBoxFornecedorServices.children.length; i++) {
                const divServicoOption = checkBoxFornecedorServices.children.item(i)
                for (let j = 0; j < divServicoOption.children.length; j++) {
                    const element = divServicoOption.children.item(j);
                    if (element.type == 'checkbox' && !element.checked) {
                        checkBoxShowOnlySelectedServices.checked ? 
                            divServicoOption.hidden = true : 
                            divServicoOption.hidden = false
                    }
                }
            }
        },

        showToast(icon, title, message) {
            const Toast = this.$swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 5000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.onmouseenter = this.$swal.stopTimer;
                toast.onmouseleave = this.$swal.resumeTimer;
            }
            });
            Toast.fire({
            icon: icon,
            title: title,
            text: message
            });
        }

    },

    async created() {
        const fornecedorId = parseInt(useRoute().query.id)
        if (Number.isInteger(fornecedorId) && fornecedorId > 0) {
            this.isLoadingExistingFornecedorFromApi = true
            await axios
                    .get(`/fornecedores/${fornecedorId}`)
                    .then(response => {
                        const fornecedor = response.data
                        this.fornecedor = fornecedor
                        fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados.forEach(servico => this.servicosSelecionados.add(parseInt(servico)))})
                    .finally(() => this.isLoadingExistingFornecedorFromApi = false)
        }

        await this.fetchServicosDoFornecedorNoSistemaDeChamados()
        document.getElementById('inputText-nome').focus()

        const inputSearchServicos = document.getElementById('inputSearch-servicos')
        inputSearchServicos.addEventListener('input', (event) => {
            document.getElementById('checkBox-showOnlySelectedServices').checked = false
            const digitado = event.target.value
            const checkBoxFornecedorServices = document.getElementById('divSelectManyServicos')
            for (let i = 0; i < checkBoxFornecedorServices.children.length; i++) {
                const divServicoOption = checkBoxFornecedorServices.children.item(i)
                for (let j = 0; j < divServicoOption.children.length; j++) {
                    const element = divServicoOption.children.item(j);
                        if (element.tagName == 'LABEL') {
                            !element.innerText.toLowerCase().includes(digitado.trim().toLowerCase()) ?
                            divServicoOption.hidden = true :
                            divServicoOption.hidden = false
                        }
                        
                }
            }
        })
    }

}

        // setServico(event) {
        //     const checkboxServico = event.target
        //     if (checkboxServico.checked) {
        //         this.servicosSelecionados.add(checkboxServico.value)
        //     } else {
        //         this.servicosSelecionados.delete(checkboxServico.value)
        //     }

        // },

</script>

<style scoped>
.form-label {
    font-weight: bold;
}
</style>