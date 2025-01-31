<template>
    <div>
        <h2>Relatórios dos Serviços</h2>
        <LoadingFromApi v-if="isFetchingFromApi"  />
    </div>
    <div style="display: grid;">
        <label style="font-weight: bold;" class="form-label" for="selectOne-fornecedor">Selecione o Fornecedor: </label>
        <select class="form-select" id="selectOne-fornecedor" v-model="fornecedor" @change="setFornecedor" required :disabled="isFetchingFromApi">
            <option value="0">Selecione o Fornecedor</option>
            <option v-for="fornecedor in fornecedores" :value="fornecedor" :key="fornecedor.id">{{ fornecedor.nome }}</option>
        </select>
    </div>
<br>

<div v-if="fornecedor != 0">
    <h3>Relatório por Período</h3>
    <DatePicker @value-change="exibirRelatorioPeriodo" v-model="datasRelatorioPeriodo" placeholder="Selecione os períodos, inicial e final" selectionMode="range" :manualInput="false" iconDisplay="input" />
    <h6>Total: 3</h6>
    <ul class="list-group">
        <li class="list-group-item list-group-item-action">Serviço: Gabinete DATEN | Total: 25 | Última Solicitação: 30/01/2025</li>
        <li class="list-group-item list-group-item-action">Serviço: Mouse DATEN | Total: 25 | Última Solicitação: 30/01/2025</li>
	</ul>
</div>

<div v-if="fornecedor != 0">
    <h3>Relatório Geral</h3>
    <h6>Total: {{ relatorioAutomatico.totalGeral }}</h6>
    <ul class="list-group">
        <li v-for="relatorio in relatorioAutomatico.relatorioGeral" :key="relatorio.idDoServico" class="list-group-item list-group-item-action">Serviço: {{ relatorio.nomeDoServico }} | Total: {{ relatorio.total }} | Última Solicitação: {{ relatorio.dataEHoraDaUltimaSolicitacao }}</li>
        <!-- <li class="list-group-item list-group-item-action">Serviço: Gabinete DATEN | Total: 25 | Última Solicitação: 30/01/2025</li>
        <li class="list-group-item list-group-item-action">Serviço: Mouse DATEN | Total: 25 | Última Solicitação: 30/01/2025</li> -->
	</ul>
<br>

<h3>Relatório Anual</h3>
    <h6>Total: {{ relatorioAutomatico.totalAnual }}</h6>
    <ul class="list-group">
        <li v-for="relatorio in relatorioAutomatico.relatorioAnual" :key="relatorio.idDoServico" class="list-group-item list-group-item-action">Serviço: {{ relatorio.nomeDoServico }} | Total: {{ relatorio.total }} | Última Solicitação: {{ relatorio.dataEHoraDaUltimaSolicitacao }}</li>
	</ul>
<br>

    <h3>Relatório Mensal</h3>
    <h6>Total: {{ relatorioAutomatico.totalMensal }}</h6>
    <ul class="list-group">
        <li v-for="relatorio in relatorioAutomatico.relatorioMensal" :key="relatorio.idDoServico" class="list-group-item list-group-item-action">Serviço: {{ relatorio.nomeDoServico }} | Total: {{ relatorio.total }} | Última Solicitação: {{ relatorio.dataEHoraDaUltimaSolicitacao }}</li>
	</ul>

</div>
<br>
        
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { DatePicker } from 'primevue'
import LoadingFromApi from '@/components/LoadingFromApi.vue'
import axios from '@/axios'

const isFetchingFromApi = ref(false)
const fornecedores = ref([])
const fornecedor = ref(0)
const relatorioAutomatico = ref()
const datasRelatorioPeriodo = ref()

const exibirRelatorioPeriodo = () => {
    const dataInicial = datasRelatorioPeriodo.value[0]
    const dataFinal = datasRelatorioPeriodo.value[1]
    if (dataFinal == null) {
        return
    }
    const dtoRelatorioFornecedorPorPeriodo = {
        fornecedorId: fornecedor.value.id,
        dataInicial: dataInicial,
        dataFinal: dataFinal
    }
    console.log(dtoRelatorioFornecedorPorPeriodo)
}

const setFornecedor = () => {
    
    const relatorioAnualGabinete = {
        total: 25,
        idDoServico: 1,
        nomeDoServico: 'Gabinete DATEN',
        nomeDoFornecedor: 'DATEN',
        dataEHoraDaUltimaSolicitacao: '30/01/2025'
    }

    const relatorioMensalGabinete = {
        total: 5,
        idDoServico: 1,
        nomeDoServico: 'Gabinete DATEN',
        nomeDoFornecedor: 'DATEN',
        dataEHoraDaUltimaSolicitacao: '10/01/2025'
    }

    const relatorioGeralGabinete = {
        total: 100,
        idDoServico: 1,
        nomeDoServico: 'Gabinete DATEN',
        nomeDoFornecedor: 'DATEN',
        dataEHoraDaUltimaSolicitacao: '30/01/2025'
    }

    const relatorioAnualMouse = {
        total: 25,
        idDoServico: 1,
        nomeDoServico: 'Mouse DATEN',
        nomeDoFornecedor: 'DATEN',
        dataEHoraDaUltimaSolicitacao: '30/01/2025'
    }

    const relatorioMensalMouse = {
        total: 5,
        idDoServico: 1,
        nomeDoServico: 'Mouse DATEN',
        nomeDoFornecedor: 'DATEN',
        dataEHoraDaUltimaSolicitacao: '15/01/2025'
    }

    const relatorioGeralMouse = {
        total: 100,
        idDoServico: 1,
        nomeDoServico: 'Mouse DATEN',
        nomeDoFornecedor: 'DATEN',
        dataEHoraDaUltimaSolicitacao: '30/01/2025'
    }

    const relatorioGeral = []
    relatorioGeral.push(relatorioGeralGabinete, relatorioGeralMouse)

    const relatorioAnual = []
    relatorioAnual.push(relatorioAnualGabinete, relatorioAnualMouse)

    const relatorioMensal = []
    relatorioMensal.push(relatorioMensalGabinete, relatorioMensalMouse)

    let totalGeral = 0
    relatorioGeral.forEach(relatorio => totalGeral += relatorio.total)

    let totalAnual = 0
    relatorioAnual.forEach(relatorio => totalAnual += relatorio.total)

    let totalMensal = 0
    relatorioMensal.forEach(relatorio => totalMensal += relatorio.total)

    const relatorio = {
        totalGeral: totalGeral,
        totalAnual: totalAnual,
        totalMensal: totalMensal,
        relatorioGeral: relatorioGeral,
        relatorioAnual: relatorioAnual,
        relatorioMensal: relatorioMensal
    }

    relatorioAutomatico.value = relatorio
    console.log(relatorioAutomatico.value);
    
}

const fetchFornecedores = async () => {

    axios.get('/fornecedores').then(response => {
        fornecedores.value = response.data
        console.log(fornecedores.value)
    })
}

onMounted(async () => {
    fetchFornecedores()
})

// const mockFornecedores = () => {
//     const fornecedor1 = {
//         id: 1,
//         nome: 'DATEN',
//         emails: 'atendimento.governo@daten.com.br, suporte5@daten.com.br'
//     }
//     const fornecedor2 = {
//         id: 2,
//         nome: 'Positivo',
//         emails: 'atendimento.governo@positivo.com.br, suporte5@positivo.com.br'
//     }
//     const fornecedoresMockedList = []
//     fornecedoresMockedList.push(fornecedor1)
//     fornecedoresMockedList.push(fornecedor2)
//     fornecedores.value = fornecedoresMockedList
// }

</script>
