<template>

    <div>
        <h2>Serviço de Garantia</h2>
        <LoadingFromApi v-if="isFetchingFromApi"  />
    </div>

    <form style="display: grid;" @submit.prevent="solicitarGarantia">

        <label style="font-weight: bold;" class="form-label" for="selectOne-cliente">Selecione o Cliente: </label>
        <select class="form-select" id="selectOne-cliente" @change="setCliente()" v-model="cliente" required autofocus :disabled="isFetchingFromApi" >
            <option value="0">Selecione o Cliente</option>
            <option v-for="cliente in clientes" :value="cliente" :key="cliente.id">{{ cliente.nome }} - {{ cliente.descricao }}</option>
        </select>
<br>

        <label style="font-weight: bold;" class="form-label" for="selectOne-fornecedor">Selecione o Fornecedor: </label>
        <select class="form-select" id="selectOne-fornecedor" v-model="fornecedor" @change="setFornecedor()" required :disabled="isFetchingFromApi">
            <option value="0">Selecione o Fornecedor</option>
            <option v-for="fornecedor in fornecedores" :value="fornecedor" :key="fornecedor.id">{{ fornecedor.nome }}</option>
        </select>
<br>

        <div id="div-buscaNumeroDeSeriePeloIdentificadorEquipamento">
            <label style="font-weight: bold;" class="form-label"   for="inputText-identificadorEquipamento">(Opcional) Buscar Número de Série pelo Identificador: </label>
            <input class="form-control" id="inputText-identificadorEquipamento" placeholder="Por exemplo, número de patrimônio ..." v-model="identificadorDoEquipamento" @change="buscarNumeroDeSeriePeloIdentificadorDoEquipamentoNoSistemaDeChamados()">
            <br>
        </div>

        <label style="font-weight: bold;" class="form-label"   for="inputText-numeroDeSerieEquipamento">Número de Série: </label>
        <input class="form-control" id="inputText-numeroDeSerieEquipamento" v-model="solicitacao.numero_de_serie" placeholder="ex. AVCLX486" required>
<br>

        <label style="font-weight: bold;" class="form-label" for="selectOne-descricaoProblema">Selecione o Problema: </label>
        <select class="form-select" id="selectOne-descricaoProblema" @change="setDescricaoProblema()" v-model="descricaoProblema" required :disabled="isFetchingFromApi">
            <option value="0">Selecione o Problema</option>
            <option v-for="descricaoProblema in descricaoProblemas" :title="descricaoProblema.descricaoDetalhada" :value="descricaoProblema" :key="descricaoProblema.id">{{ descricaoProblema.problema.tipo }} | {{ descricaoProblema.descricaoResumida }}</option>
        </select>
<br>

        <label style="font-weight: bold;" class="form-label"   for="textArea-descricaoProblema">Descrição do Problema: </label>
        <textarea rows="5" class="form-control" id="textArea-descricaoProblema" v-model="descricaoProblema.descricaoDetalhada" placeholder="Selecione um Problema na combobox acima" disabled></textarea>
<br>
        <label style="font-weight: bold;" for="selectOne-ticketChamado">Selecione o Ticket: </label>
        <select class="form-select" id="selectOne-ticketChamado" @change="setChamado()" v-model="chamado" required>
            <option value="0">{{ solicitacao.fornecedor_id == 0 ? 'Para exibir os chamados disponíveis, primeiro selecione o Fornecedor' : 'Selecione o Ticket' }}</option>
            <option :value="chamado" v-for="chamado in chamados" :key="chamado.id">Nº do Ticket: {{ chamado.numero }} | Título: {{ chamado.titulo }} | Serviço: {{ chamado.servico }} | Usuário: {{ chamado.usuario }}</option>
        </select>
<br>

        <label style="font-weight: bold;" class="form-label" for="selectOne-emailTemplate">(Opcional) Utilizar modelo de e-mail pronto: </label>
        <select class="form-select" id="selectOne-emailTemplate" :disabled="dadosObrigatoriosFaltantesParaGerarOTemplate()" v-model="emailTemplateSelecionado" required @change="setAssuntoECorpoDoEmail" >
            <option value="0">(Opcional) Selecione um Modelo</option>
            <option v-for="emailTemplate in emailTemplates" :value="emailTemplate" :key="emailTemplate.id">{{ emailTemplate.descricao }}</option>
        </select>
<br>
        <label style="font-weight: bold;" class="form-label"   for="inputText-assunto">Assunto: </label>
        <input class="form-control" id="inputText-assunto" v-model="solicitacao.assunto" placeholder="" required>
<br>

        <label style="font-weight: bold;" class="form-label"   for="textArea-corpoDoEmail">Corpo do Email: </label>
        <textarea rows="10" class="form-control" id="textArea-corpoDoEmail" v-model="solicitacao.corpo_do_email" placeholder="Prezados, falamos da empresa XPTO, equipamento de número de série AVCLX486 está ..." required></textarea>
<br>

        <label style="font-weight: bold;" class="form-label" for="inputFile-videoOuFotoProblema">(Opcional) Vídeo ou Foto do Problema: </label>
        <input type="file" class="form-control form-control-sm" id="inputFile-videoOuFotoProblema" @change="setAnexo">
<br>

        <label style="font-weight: bold;" class="form-label"   for="inputText-copiaPara">Copia Para: </label>
        <input class="form-control" id="inputText-copiaPara" v-model="solicitacao.copia_para" placeholder="copia1@example.com, copia2@example.com" >
<br>

        <label style="font-weight: bold;" class="form-label"   for="inputText-copiaOculta">Copia Oculta: </label>
        <input class="form-control" id="inputText-copiaOculta" v-model="solicitacao.copia_oculta" placeholder="copia_oculta@example.com" >
<br>

        <!-- Button trigger modal -->
        <button :disabled="dadosObrigatoriosFaltantes()" id="botaoSolicitarGarantia" type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Solicitar Garantia
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Nova Solicitação de Garantia</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <h4>Cliente</h4>
                        <p><b>Solicitante:</b> {{ cliente.nome }} - {{ cliente.descricao }}</p>
                        <p><b>Número de Série:</b> {{ solicitacao.numero_de_serie }}</p>
                        <p><b>Problema:</b> {{ descricaoProblema.descricaoDetalhada }}</p>
                    </div>
                    <div>
                        <h4>Fornecedor</h4>
                        <p><b>Nome: </b>{{ fornecedor.nome }}</p>
                        <p><b>E-mail(s): </b>{{ fornecedor.emails }}</p>
                    </div>
                    <div v-if="chamado">
                        <h4>Sistema de Chamados</h4>
                        <p><b>Chamado: </b>{{ chamado.numero }}</p>
                        <p><b>Título: </b>{{ chamado.titulo }}</p>
                        <p><b>Serviço: </b>{{ chamado.servico }}</p>
                        <p><b>Usuário: </b>{{ chamado.usuario }}</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-outline-primary" data-bs-dismiss="modal">Confirmar Solicitação</button>
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancelar</button>
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
export default {
    components: { LoadingFromApi },
    name: 'IndexView',
    data() {
        return {
            isFetchingFromApi: false,
            solicitacaoJaRealizada: false,
            errors: new Set(),
            cliente: 0,
            fornecedor: 0,
            descricaoProblema: 0,
            chamado: 0,
            emailTemplateSelecionado: 0,

            chamados: null,
            identificadorDoEquipamento: null,
            clientes: null,
            fornecedores: null,
            descricaoProblemas: null,
            emailTemplates: null,

            solicitacao: {
                numero_de_serie: null,
                cliente_id: 0,
                fornecedor_id: 0,
                chamado_id: 0,
                descricao_problema_id: 0,
                assunto: null,
                corpo_do_email: null,
                copia_para: null,
                copia_oculta: null,
                anexo: null
            }
        }
    },

    methods: {
        dadosObrigatoriosFaltantes() {
            return this.cliente == 0 ||
                   this.fornecedor == 0 ||
                   this.descricaoProblema == 0 || 
                   this.chamado == 0 ||
                   this.solicitacao.chamado_id == 0 ||
                   this.solicitacao.numero_de_serie == null || this.solicitacao.numero_de_serie.trim() == '' ||
                   this.solicitacao.assunto == null || this.solicitacao.assunto.trim() == '' ||
                   this.solicitacao.corpo_do_email == null || this.solicitacao.corpo_do_email.trim() == ''
        },

        dadosObrigatoriosFaltantesParaGerarOTemplate() {
            return this.cliente == 0 ||
                   this.fornecedor == 0 ||
                   this.descricaoProblema == 0 || 
                   this.chamado == 0 ||
                   this.solicitacao.chamado_id == 0 ||
                   this.solicitacao.numero_de_serie == null || this.solicitacao.numero_de_serie.trim() == ''
        },

        async buscarNumeroDeSeriePeloIdentificadorDoEquipamentoNoSistemaDeChamados() {
            const inputNumeroDeSerie = document.querySelector('#inputText-numeroDeSerieEquipamento')
            if (this.identificadorDoEquipamento.trim() != '') {
                await axios
                    .get('/sistemaDeChamados/inventario/' + this.identificadorDoEquipamento)
                    .then(response => {
                        this.solicitacao.numero_de_serie = response.data
                        inputNumeroDeSerie.style.borderColor = 'green'
                    })
                    .catch(error => {
                        console.log(error)
                        if (error.status == 404) {
                            this.showToast('warning', 'Número de Série para o identificador informado não encontrado')
                            this.solicitacao.numero_de_serie = null
                            inputNumeroDeSerie.style.borderColor = 'red'
                        }
                    })
            }
        },

        async solicitarGarantia() {
            const botaoSolicitarGarantia = document.getElementById('botaoSolicitarGarantia')
            const botaoSolicitarGarantiaTextoOriginal = botaoSolicitarGarantia.innerText
            botaoSolicitarGarantia.innerText = 'Solicitação em Andamento...'
            botaoSolicitarGarantia.disabled = true
            if (this.solicitacaoJaRealizada) {
                this.showToast('warning', 'Solicitação de Garantia Já Realizada')
                return
            }
            this.errors.clear()
            await axios.postForm('/solicitacaoGarantia', this.solicitacao, {
                headers: { 'Content-Type': 'multipart/form-data' }
            })
                .then(() => {
                    this.solicitacaoJaRealizada = true
                    this.showToast('success', 'Solicitação de Garantia enviada com Sucesso')
            }).catch(error => {
                    console.log(error)
                    if (error.status == 400) { 
                        this.exibirConstraintViolations(error) 
                    } else {
                        throw error
                    }
            }).finally(() => {
                botaoSolicitarGarantia.innerText = botaoSolicitarGarantiaTextoOriginal
                botaoSolicitarGarantia.disabled = false
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

        fromTemplateToRealString(templateString) {
            let replaced =  templateString

            .replaceAll("$numeroDeSerie", 
                this.solicitacao.numero_de_serie)
            .replaceAll("$problema.tipo", 
                this.descricaoProblema.problema.tipo)
            .replaceAll("$problema.descricao", 
                this.descricaoProblema.descricaoDetalhada)
            .replaceAll("$chamado.numeroDoTicket", 
                this.chamado.numero)
            .replaceAll("$fornecedor.nome", 
                this.fornecedor.nome)
            .replaceAll("$cliente.nome", 
                this.cliente.nome)
            .replaceAll("$cliente.descricao", 
                this.cliente.descricao)
            .replaceAll("$cliente.numerosParaContato", 
                this.cliente.numerosParaContato)
            .replaceAll("$cliente.emailsParaContato", 
                this.cliente.emailsParaContato)
            .replaceAll("$cliente.endereco.logradouro", 
                this.cliente.logradouro)
            .replaceAll("$cliente.endereco.numero", 
                this.cliente.numero)
            .replaceAll("$cliente.endereco.bairro", 
                this.cliente.bairro)
            .replaceAll("$cliente.endereco.estado", 
                this.cliente.estado)
            .replaceAll("$cliente.endereco.cidade", 
                this.cliente.cidade)
            .replaceAll("$cliente.endereco.cep", 
                this.cliente.cep)
            .replaceAll("$cliente.horarios.inicioDoExpediente", 
                this.cliente.horarioInicioDoExpediente)
            .replaceAll("$cliente.horarios.fimDoExpediente", 
                this.cliente.horarioFimDoExpediente)
            .replaceAll("$cliente.horarios.inicioDoAlmoco", 
                this.cliente.inicioDoHorarioDeAlmoco)
            .replaceAll("$cliente.horarios.fimDoAlmoco", 
                this.cliente.fimDoHorarioDeAlmoco);

            return replaced;
        },

        setCliente() {
            this.solicitacao.cliente_id = this.cliente.id
            this.solicitacao.copia_oculta = this.cliente.emailsParaContato
        },

        setDescricaoProblema() {
            this.solicitacao.descricao_problema_id = this.descricaoProblema.id
        },

        setAnexo(event) {
            const anexo = event.target.files[0]
            this.solicitacao.anexo = anexo
        },

        setChamado() {
            this.solicitacao.chamado_id = this.chamado.id
        },

        async setFornecedor() {
            this.solicitacao.fornecedor_id = this.fornecedor.id
            await this.listarChamadosDoFornecedor()
        },

        async listarChamadosDoFornecedor() {
            this.solicitacao.chamado_id = 0
            if (this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados.length > 0) {
                await axios
                        .post('/sistemaDeChamados/chamados', this.fornecedor.idsDosServicosDoFornecedorNoSistemaDeChamados)
                        .then(response => {
                            this.chamados = response.data
                            if (this.chamados.length == 0) {
                                this.showToast(
                                    'warning', 
                                    'Nâo há chamados disponíveis para o fornecedor selecionado',
                                    'Caso tenha certeza que o(s) chamado(s) existe(m) e que está(ão) aberto(s), verifique se você definiu o serviço do chamado corretamente no Sistema de Chamados, ou se, o Serviço está corretamente associado ao fornecedor, consultando através da aba Fornecedores > Listagem, e clicando em Informações no Fornecedor desejado.')
                            }
                        })
            }
        },

        setAssuntoECorpoDoEmail(event) {
            if (event.target.value != 0) {
                this.solicitacao.assunto = this.fromTemplateToRealString(this.emailTemplateSelecionado.assunto)
                this.solicitacao.corpo_do_email = this.fromTemplateToRealString(this.emailTemplateSelecionado.corpoDoEmail)
            }
        },

        async fetchFornecedores() {
            await axios
                .get('/fornecedores')
                .then(response => this.fornecedores = response.data)
                .catch(error => console.log(error))
        },

        async fetchClientes() {
            await axios
                .get('/clientes')
                .then(response => this.clientes = response.data)
                .catch(error => console.log(error))

        },

        async fetchDescricaoProblemas() {
            await axios
                    .get('/descricaoProblemas')
                    .then(response => this.descricaoProblemas = response.data)
                    .catch(error => console.log(error))
        },

        async fetchEmailsTemplates() {
            await axios
                    .get('/emailstemplates')
                    .then(response => this.emailTemplates = response.data)
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

        async apiFetch() {
            this.isFetchingFromApi = true
            await this.fetchClientes()
            await this.fetchFornecedores()
            await this.fetchDescricaoProblemas()
            await this.fetchEmailsTemplates()

            document.getElementById('selectOne-cliente').focus()
        }

    },

    async created() {
        await this.apiFetch().finally(() =>  this.isFetchingFromApi = false )
    }

}

</script>