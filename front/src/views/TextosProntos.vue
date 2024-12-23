<template>
<h2>
    <i class="bi bi-text-left"></i>
    Cadastro de Textos Prontos
</h2>
<hr>
<h3>Tipos de Problemas</h3>
<div>
    <DicaTiposDeProblemas />
    <div>
        <label class="form-label" for="inputText-problema-tipo">
            Você pode criar uma categoria através do campo de texto abaixo: 
            <input required v-model="novoProblema.tipo" class="form-control" style="display: inline-flex;" placeholder="ex. Mouse, Teclado ou Fonte" id="inputText-problema-tipo">
        </label>
    </div>
    <div>
        <button class="btn btn-outline-primary" type="button" @click="salvarNovaCategoria">Salvar</button>
    </div>
</div>
<hr>
<h2>Categorias Cadastradas</h2>
<div>
    <p>
        <b>Dica: </b>Clique em uma das categorias abaixo para edita-las.
    </p>
</div>
<div>
    <table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">
                <i class="bi bi-info-circle"></i>
                Categoria
            </th>
        </tr>
    </thead>
    <tbody id="tableCategorias-body">
        <!-- <tr v-for="problema in problemas" :key="problema.id"> -->
            <!-- <td @click="editCategoriaClick">{{ problema.tipo }}</td> -->
        <!-- </tr> -->
    </tbody>
</table>
</div>
<br>




</template>

<style scoped>
div {
    display: grid;
}
td {
    cursor: pointer;
}
</style>

<script>
import axios from '@/axios'
import DicaTiposDeProblemas from '@/components/DicaTiposDeProblemas.vue';
export default {
    components: { DicaTiposDeProblemas },
    data() {
        return {
            problemas: null,
            problemaSelecionado: null,
            novoProblema: { tipo: null }
        }
    },
    methods: {

        salvarNovaCategoria(event) {
            event.target.setAttribute('disabled', true)
            event.target.innerText = 'Salvando...'
            const inputProblemaTipo = document.getElementById('inputText-problema-tipo')
            inputProblemaTipo.style.borderColor = null
            axios
                .post('/problemas', this.novoProblema)
                .then(response => {
                    const tableBody = document.getElementById('tableCategorias-body')
                    const categoriaCriada = response.data
                    alert(`Nova Categoria ${categoriaCriada.tipo} criada com sucesso`)
                    const row = document.createElement('tr')
                    const data = document.createElement('td')
                    data.innerText = categoriaCriada.tipo
                    data.style.cursor = 'pointer'
                    row.appendChild(data)
                    tableBody.appendChild(row)
                    this.transformCategoriaDataRowToInputText(data, categoriaCriada)
                })
                .catch(error => {
                    console.log(error);
                    if (error.response.status == 400) {
                        inputProblemaTipo.style.borderColor = 'red'
                        if (Array.isArray(error.response.data)) {
                            error.response.data.forEach(error => {
                                alert(error)
                            })
                        } else {
                            alert(error.response.data.error)
                        }

                    } else {
                        throw error
                    }
                }).finally(() => {
                    event.target.disabled = false
                    event.target.innerText = 'Salvar'
                })
        },

        editCategoriaClick(event) {
            console.log(event.target);
        },

        fetchTableCategoriasBody(problemas) {
            const tableBody = document.getElementById('tableCategorias-body')
            problemas.forEach(problema => {
                const row = document.createElement('tr')
                const data = document.createElement('td')
                data.innerText = problema.tipo
                data.style.cursor = 'pointer'
                row.appendChild(data)
                tableBody.appendChild(row)
                this.transformCategoriaDataRowToInputText(data, problema)
            })
        },

        transformCategoriaDataRowToInputText(dataRowElement, problema) {
            const inputCategoriaEdit = document.createElement('input')
            const inputCategoriaOriginalText = dataRowElement.innerText

            dataRowElement.addEventListener('click', () => {
                dataRowElement.replaceWith(inputCategoriaEdit)
                inputCategoriaEdit.value = dataRowElement.innerText
                inputCategoriaEdit.className = 'form-control'
                inputCategoriaEdit.style.marginBottom = '10px'
                inputCategoriaEdit.oninput = (event) => this.problemaSelecionado.tipo = event.target.value
                inputCategoriaEdit.focus()
                this.problemaSelecionado = problema
            })
            inputCategoriaEdit.addEventListener('focusout', () => {
                inputCategoriaEdit.replaceWith(dataRowElement)
                dataRowElement.innerText = inputCategoriaEdit.value
                this.atualizarCategoriaProblema()
                .catch(error => {
                    console.log(error);
                    if (error.response.status == 400) {
                        if (Array.isArray(error.response.data)) {
                            error.response.data.forEach(error => {
                                alert(error)
                            })
                        } else {
                            alert(error.response.data.error)
                        }

                        this.problemaSelecionado.tipo = inputCategoriaOriginalText
                        dataRowElement.innerText = inputCategoriaOriginalText

                    } else {
                        throw error
                    }
                }).finally(() => {
                    event.target.disabled = false
                    event.target.innerText = 'Salvar'
                })
            })
        },

        async atualizarCategoriaProblema() { await axios .put('/problemas', this.problemaSelecionado) }

    },
    async created() {
        await axios
                .get('/problemas')
                .then(response => {
                    this.problemas = response.data
                    this.fetchTableCategoriasBody(this.problemas)
                })
    },

    mounted() {
        document.getElementById('inputText-problema-tipo').focus()
    }

}
</script>