<template>

<h2>Administração / Modelos de E-mails / {{ modeloDeEmailCadastroDto.id == null ? 'Cadastro' : 'Atualização' }} </h2>

<div>
    <label id="label-dica" style="font-weight: bold; text-align: justify;" >
💡 DICA 💡
<br/>
Ao digitar nos campos de Assunto e Corpo do E-mail é possível ir conferindo logo abaixo
<br/>
o resultado do e-mail template que você está criando, por exemplo ao digitar uma expressão de variável, 
<br/>
como $cliente.endereco.cidade, é possível conferir nos campos de cidade, seu valor de exemplo. 
</label>
<br><br>
</div>

<div>
    <LoadingFromApi v-if="isLoadingExistingEmailTemplateFromApi" />
</div>

<form style="display: grid;" @submit.prevent="salvar">

<label class="form-label" style="font-weight: bold" for="inputText-descricaoDoEmailTemplate">Descrição do Tipo do E-mail: </label>
<input class="form-control" v-model="modeloDeEmailCadastroDto.descricao" id="inputText-descricaoDoEmailTemplate" type="text" placeholder="Email Pronto para quando acontecer o problema X ..." maxlength="255" >

<label class="form-label" style="font-weight: bold" for="inputText-assuntoDoEmailTemplate">Assunto do E-mail: </label>
<input class="form-control" v-model="modeloDeEmailCadastroDto.assunto" id="inputText-assuntoDoEmailTemplate" type="text" @input="imprimeAssuntoTemplateNoInputTextResultado" placeholder="Solicitação de $problema.tipo no equipamento do fornecedor $fornecedor.nome " maxlength="255" >

<label class="form-label" value="Corpo do Email: " for="textArea-corpoDoEmailTemplate" style="font-weight: bold;">Corpo do E-mail: </label>
<textarea class="form-control" v-model="modeloDeEmailCadastroDto.corpoDoEmail" id="textArea-corpoDoEmailTemplate" rows="10" cols="50" @input="imprimeTemplateNoTextAreaResultado" placeholder="Prezado atendente da $fornecedor.nome, solicitamos garantia para o equipamento $numeroDeSerie..." ></textarea>

<br>
<button class="btn btn-outline-primary">Salvar</button>
<br>
<button v-if="modeloDeEmailCadastroDto.id" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#exclueEmailTemplateModal">Excluir</button>
        <div class="modal fade" id="exclueEmailTemplateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmação de Exclusão</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <label>Confirmar Exclusão do Modelo: <span style="text-transform: capitalize; font-weight: bold;">{{ modeloDeEmailCadastroDto.descricao }}</span> ?</label>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="excluirEmailTemplate">Confirmar Exclusão</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>

</form>

<Fieldset legend="Resultado">
    <label class="form-label" value="Assunto do Email (Resultado)" style="font-weight: bold;" >Assunto do Email (Resultado)</label>
    <input id="inputText-resultado-assuntoDoEmailTemplate" placeholder="Digite no campo de Assunto do Email acima para ir conferindo o resultado neste campo" readonly class="form-control" style="cursor: not-allowed;" />
    <label for="textArea-resultado-corpoDoEmailTemplate" style="font-weight: bold;" >Corpo do Email (Resultado): </label>
    <textarea id="textArea-resultado-corpoDoEmailTemplate" class="form-control" rows="10" cols="50" readonly="true" style="cursor: not-allowed;"></textarea>
</Fieldset>


<div>

    <Fieldset toggleable legend="Variáveis Disponíveis" >
                <p style="font-weight: bold;" >
                    Você pode utilizar as variáveis abaixo para gerar <i>um corpo de e-mail template</i>, que pode ser reaproveitado para várias solicitações ou fornecedores.
                </p>
                <ul style="list-style: none;">
                    <li>Número de Série do Equipamento: $numeroDeSerie</li>
                    <li>Tipo do Problema: $problema.tipo</li>
                    <li>Descrição Detalhada do Problema do Equipamento: $problema.descricao</li>
                    <li>Número do Ticket no Sistema de Chamado: $chamado.numeroDoTicket</li>
                    <li>Nome do Fornecedor: $fornecedor.nome</li>
                    <li>Nome do Cliente: $cliente.nome</li>
                    <li>Números para contato do Cliente: $cliente.numerosParaContato</li>
                    <li>Emails para contato do Cliente: $cliente.emailsParaContato</li>
                    <li>Endereço do Cliente: Rua ou Avenida: $cliente.endereco.logradouro</li>
                    <li>Endereço do Cliente: Número: $cliente.endereco.numero</li>
                    <li>Endereço do Cliente: Bairro: $cliente.endereco.bairro</li>
                    <li>Endereço do Cliente: Estado: $cliente.endereco.estado</li>
                    <li>Endereço do Cliente: Cidade: $cliente.endereco.cidade</li>
                    <li>Endereço do Cliente: CEP: $cliente.endereco.cep</li>
                    <li>Horários do Cliente: Início do Expediente: $cliente.horarios.inicioDoExpediente</li>
                    <li>Horários do Cliente: Início do Almoço: $cliente.horarios.inicioDoAlmoco</li>
                    <li>Horários do Cliente: Início do Fim: $cliente.horarios.fimDoAlmoco</li>
                    <li>Horários do Cliente: Fim do Expediente: $cliente.horarios.fimDoExpediente</li>
                </ul>

                <h2>Exemplo</h2>
                <p>
                    Abaixo é possível conferir como ficaria um cadastro de um email modelo
                </p>
                <h5>Assunto</h5>
                <p>
                    [Ticket#$chamado.numeroDoTicket] Problema $problema.tipo $fornecedor.nome - $cliente.nome
                </p>
                <h5>Corpo do E-mail</h5>
                <p>
                    Prezados, <br/>
                    Falamos da $cliente.nome, <br/>
                    Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: $numeroDeSerie. <br/>
                    O Expediente vai das $cliente.horarios.inicioDoExpediente às $cliente.horarios.fimDoExpediente. <br/>
                    Atenciosamente, <br/>
                    $cliente.nome - $cliente.descricao <br/>
                    $cliente.endereco.logradouro <br/>
                    $cliente.endereco.estado / $cliente.endereco.cidade <br/>
                </p>

                <h2>Resultado</h2>

                <h5>Assunto</h5>
                <p>
                    [Ticket#20240101486] Problema Mouse Fornecedor LTDA - Empresa Cliente Inc
                </p>
                <h5>Corpo do E-mail</h5>
                <p>
                    Prezados, <br/>
                    Falamos da Empresa Cliente LTDA, <br/>
                    Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: AVCL486XPTO. <br/>
                    O Expediente vai das 08:00 às 17:00. <br/>
                    Atenciosamente, <br/>
                    Empresa Cliente LTDA - Matriz da Empresa Cliente LTDA <br/>
                    Avenida dos Expedicionários <br/>
                    Ceará / Fortaleza <br/>
                </p>
        </Fieldset>


</div>

</template>

<script>
import axios from '@/axios'
import LoadingFromApi from '@/components/LoadingFromApi.vue';
import { Fieldset } from 'primevue'
import { useRoute, useRouter } from 'vue-router';

export default {
    components: { Fieldset, LoadingFromApi },
    data() {
        return {
            isLoadingExistingEmailTemplateFromApi: false,
            modeloDeEmailCadastroDto: {
                id: null,
                descricao: '',
                assunto: '',
                corpoDoEmail: ''
            },
            
            solicitacaoDeGarantia: {
                numeroDeSerie: 'AVCLX486',
                problema: {
                    tipo: 'Gabinete',
                    descricao: 'Computador Liga porém não apresenta imagem, solicitamos verificação'
                },
                chamado: {
                    numeroDoTicket: '20240101000486'
                },
                cliente: {
                    id: 1,
                    nome: 'Empresa Cliente LTDA',
                    descricao: 'Matriz da Empresa Cliente LTDA',
                    numerosParaContato: '(85)3386-4214, (85)3386-4416',
                    emailsParaContato: 'atendimento.cliente@mail.br, cliente.ltda@mail.br',
                    endereco: {
                        logradouro: 'Rua dos Escritores',
                        numero: '150',
                        bairro: 'Bairro dos Escrivões',
                        estado: 'Ceará',
                        cidade: 'FortalCity',
                        cep: '60000-486'
                    },
                    horarios: {
                        inicioDoExpediente: '08:00',
                        fimDoExpediente: '17:00',
                        inicioDoAlmoco: '12:00',
                        fimDoAlmoco: '13:00'
                    }
                },
                fornecedor: {
                    id: 1,
                    nome: 'Fornecedor LTDA',
                    emailsParaContato: 'suporte.garantia@mail.fornecedor.br, atendimento.empresas@fornecedor.br'
                }
            }
        }
    },

    methods: {
        async salvar() {
            if (this.modeloDeEmailCadastroDto.id == undefined || this.modeloDeEmailCadastroDto.id == null ) {
                await this.cadastrarNovoModeloDeEmail(this.modeloDeEmailCadastroDto)
            } else {
                await this.atualizarModeloDeEmail(this.modeloDeEmailCadastroDto)
            }
        },

        async cadastrarNovoModeloDeEmail(novoModeloDeEmailDto) {
            await axios.post('/emailstemplates', novoModeloDeEmailDto)
                    .then(response => {
                        novoModeloDeEmailDto.id = response.data.id
                        this.showToast('success', 'Novo Modelo de E-mail Pronto cadastrado com sucesso')
                    }).catch(error => {
                        if (error.status == 400) {
                            this.tratarErro400(error)
                        } else throw error
                    })
        },

        async atualizarModeloDeEmail(atualizacaoModeloDeEmailDto) {
            await axios.patch('/emailstemplates', atualizacaoModeloDeEmailDto)
                    .then(() => {
                        this.showToast('success', 'Modelo de E-mail atualizado com sucesso')
                    }).catch(error => {
                        if (error.status == 400) {
                            this.tratarErro400(error)
                        } else throw error
                    })
        },

        async excluirEmailTemplate() {
            await axios
                    .delete(`/emailstemplates/${this.modeloDeEmailCadastroDto.id}`)
                    .then(async () => {
                        useRouter().push('/administracao/emailsTemplates/listagem').then(() => {
                            this.showToast('info', 
                                           'Modelo Excluído com sucesso', 
                                           `O Modelo ${this.modeloDeEmailCadastroDto.descricao} foi excluído com sucesso`)
                        })
                    }).catch(error => {
                        if (error.status == 400) {
                            this.tratarErro400(error)
                        }
                    })
        },

        tratarErro400(error) {
            const messages = error.response.data
            if (Array.isArray(messages)) {
                messages.forEach(message => {
                    this.showToast('error', message)
                })
            } else {
                this.showToast('error', messages.error)
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
        },

        fromTemplateToRealString(templateString) {
            let replaced = templateString
            .replaceAll("$numeroDeSerie", 
                this.solicitacaoDeGarantia.numeroDeSerie)
            .replaceAll("$problema.tipo", 
                this.solicitacaoDeGarantia.problema.tipo)
            .replaceAll("$problema.descricao", 
                this.solicitacaoDeGarantia.problema.descricao)
            .replaceAll("$chamado.numeroDoTicket", 
                this.solicitacaoDeGarantia.chamado.numeroDoTicket)
            .replaceAll("$fornecedor.nome", 
                this.solicitacaoDeGarantia.fornecedor.nome)
            .replaceAll("$cliente.nome", 
                this.solicitacaoDeGarantia.cliente.nome)
            .replaceAll("$cliente.descricao", 
                this.solicitacaoDeGarantia.cliente.descricao)
            .replaceAll("$cliente.numerosParaContato", 
                this.solicitacaoDeGarantia.cliente.numerosParaContato)
            .replaceAll("$cliente.emailsParaContato", 
                this.solicitacaoDeGarantia.cliente.emailsParaContato)
            .replaceAll("$cliente.endereco.logradouro", 
                this.solicitacaoDeGarantia.cliente.endereco.logradouro)
            .replaceAll("$cliente.endereco.numero", 
                this.solicitacaoDeGarantia.cliente.endereco.numero)
            .replaceAll("$cliente.endereco.bairro", 
                this.solicitacaoDeGarantia.cliente.endereco.bairro)
            .replaceAll("$cliente.endereco.estado", 
                this.solicitacaoDeGarantia.cliente.endereco.estado)
            .replaceAll("$cliente.endereco.cidade", 
                this.solicitacaoDeGarantia.cliente.endereco.cidade)
            .replaceAll("$cliente.endereco.cep", 
                this.solicitacaoDeGarantia.cliente.endereco.cep)
            .replaceAll("$cliente.horarios.inicioDoExpediente", 
                this.solicitacaoDeGarantia.cliente.horarios.inicioDoExpediente)
            .replaceAll("$cliente.horarios.fimDoExpediente", 
                this.solicitacaoDeGarantia.cliente.horarios.fimDoExpediente)
            .replaceAll("$cliente.horarios.inicioDoAlmoco", 
                this.solicitacaoDeGarantia.cliente.horarios.inicioDoAlmoco)
            .replaceAll("$cliente.horarios.fimDoAlmoco", 
                this.solicitacaoDeGarantia.cliente.horarios.fimDoAlmoco);

            return replaced;
        },

        imprimeAssuntoTemplateNoInputTextResultado() {
            let templateAssuntoInputText = document.getElementById('inputText-assuntoDoEmailTemplate')
            let resultadoAssuntoInputText = document.getElementById('inputText-resultado-assuntoDoEmailTemplate')
            resultadoAssuntoInputText.value = this.fromTemplateToRealString(templateAssuntoInputText.value)
        },

        imprimeTemplateNoTextAreaResultado() {
            let templateTextArea = document.getElementById('textArea-corpoDoEmailTemplate')
            let resultadoTextArea = document.getElementById('textArea-resultado-corpoDoEmailTemplate')
            resultadoTextArea.value = this.fromTemplateToRealString(templateTextArea.value)
        }
    },

    async created() {
        const modeloDeEmailId = parseInt(useRoute().query.id)
        if (Number.isInteger(modeloDeEmailId) && modeloDeEmailId > 0) {
            this.isLoadingExistingEmailTemplateFromApi = true
            await axios
                    .get(`/emailstemplates/${modeloDeEmailId}`)
                    .then(response => this.modeloDeEmailCadastroDto = response.data)
                    .catch(error => {
                        if (error.status == 404) {
                            this.showToast(
                                'warning', 
                                'Modelo de E-mail com o id informado não encontrado', 
                                'Redirecionando para a página de criação de novo Modelo')
                        }
                    }).finally(() => this.isLoadingExistingEmailTemplateFromApi = false)
        }
    },

    mounted() {
        const descricaoDoEmailTemplate = document.getElementById('inputText-descricaoDoEmailTemplate')
        descricaoDoEmailTemplate.focus()
    }

}

</script>