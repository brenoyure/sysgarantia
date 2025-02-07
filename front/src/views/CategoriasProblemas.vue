<template>
<h2>
    <i class="bi bi-text-left"></i>
    Cadastro de Categorias
</h2>

<hr>
<h3>Tipos de Problemas</h3>
<div>
    <DicaTiposDeProblemas />
    <div>
        <label class="form-label" for="inputText-problema-tipo">
            Você pode <b>criar uma nova categoria</b> através do campo de texto abaixo: 
            <input required v-model="novaCategoria.tipo" class="form-control" style="display: inline-flex;" placeholder="ex. Mouse, Teclado ou Fonte" id="inputText-problema-tipo">
        </label>
    </div>
    <div style="display: grid;">
        <button class="btn btn-outline-primary" type="button" @click="salvarNovaCategoria">Salvar</button>
    </div>
</div>
<hr>
<h2>Categorias Cadastradas</h2>
<LoadingFromApi v-if="isFetchingFromApi" />
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
        <tr v-for="categoria in categorias" :key="categoria.id">
            <td hidden>{{ categoria.id }}</td>
            <td style="cursor: pointer;" @click="editCategoria">{{ categoria.tipo }}</td>
            <td>
                <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" :data-bs-target="`#exclueCategoria${categoria.id}`">Excluir</button>
                <div class="modal fade" :id="`exclueCategoria${categoria.id}`" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmação de Exclusão</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <label>Confirmar Exclusão da Categoria: <span style="text-transform: capitalize; font-weight: bold;">{{ categoria.tipo }}</span> ?</label>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-outline-danger" data-bs-dismiss="modal" @click="excluirCategoria">Excluir</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </tbody>
</table>
</div>
<br>

</template>

<script>
import axios from '@/axios'
import DicaTiposDeProblemas from '@/components/DicaTiposDeProblemas.vue';
import LoadingFromApi from '@/components/LoadingFromApi.vue';
export default {
    components: { DicaTiposDeProblemas, LoadingFromApi },
    data() {
        return {
            isFetchingFromApi: false,
            categorias: null,
            problemaSelecionado: null,
            novaCategoria: { tipo: null }
        }
    },
    methods: {
        async salvarNovaCategoria(event) {
            event.target.setAttribute('disabled', true)
            event.target.innerText = 'Salvando...'
            const inputProblemaTipo = document.getElementById('inputText-problema-tipo')
            inputProblemaTipo.style.borderColor = null
            await axios
                .post('/problemas', this.novaCategoria)
                .then(response => {
                    const categoriaCriada = response.data
                    this.categorias.push(categoriaCriada)
                    inputProblemaTipo.style.borderColor = 'green'
                    this.showToast('success', `Nova Categoria ${categoriaCriada.tipo} criada com sucesso`)
                    this.novaCategoria.tipo = null

                })
                .catch(error => {
                    // console.log(error);
                    if (error.response.status == 400) {
                        inputProblemaTipo.style.borderColor = 'red'
                        if (Array.isArray(error.response.data)) {
                            error.response.data.forEach(error => {
                                this.showToast('error', error)
                            })
                        } else {
                            this.showToast('error', error.response.data.error)
                        }

                    } else {
                        throw error
                    }
                }).finally(() => {
                    event.target.disabled = false
                    event.target.innerText = 'Salvar'
                })
        },

        editCategoria(event) {
            const categoriaDataRow = event.target
            let categoriaRow = categoriaDataRow.parentElement
            while (!(categoriaRow instanceof HTMLTableRowElement)) {
                categoriaRow = categoriaRow.parentElement
            }
            const categoriaAtual = event.target.innerText
            const inputCategoria = document.createElement('input')
            inputCategoria.className = 'form-control'
            inputCategoria.style.marginBottom = '5px'
            inputCategoria.value = categoriaAtual
            inputCategoria.oninput = (event) => {
                categoriaDataRow.innerText = event.target.value
            }
            inputCategoria.addEventListener('focusout', async () => {

                const dtoAtualizaCategoria = {
                    id: categoriaRow.cells.item(0).innerText,
                    tipo: inputCategoria.value.trim()
                }

                await axios
                        .put('/problemas/', dtoAtualizaCategoria)
                        .then(() => {
                            this.showToast('success', 'Categoria Atualizada com sucesso')
                            categoriaDataRow.style.borderColor = 'blue'
                        }).catch(error => {
                            // console.log(error);
                            if (error.status == 400) {
                                this.showToast('error', error.response.data.error)                                
                                categoriaDataRow.style.borderColor = 'red'
                                categoriaDataRow.innerText = categoriaAtual
                            } else { throw error }

                        }).finally(() => {
                            inputCategoria.replaceWith(categoriaDataRow)
                            inputCategoria.remove()
                        })
            })

            categoriaDataRow.replaceWith(inputCategoria)
            inputCategoria.focus()

        },

        async excluirCategoria(event) {
            let categoriaRow = event.target
            while (!(categoriaRow instanceof HTMLTableRowElement)) {
                categoriaRow = categoriaRow.parentElement
            }

            const categoriaId = categoriaRow.cells.item(0).innerText
            const categoriaTipo = categoriaRow.cells.item(1).innerText

            await axios
                    .delete(`/problemas/${categoriaId}`)
                    .then(() => {
                        categoriaRow.remove()
                        this.showToast('success', `Categoria ${categoriaTipo} excluída com sucesso`)
                    }).catch(error => {
                        // console.log(error);
                        if (error.status == 400) {
                            this.showToast('error', error.response.data.error)
                        } else {
                            throw error;
                        }
                    })
        },

        async atualizarCategoriaProblema() { await axios .put('/problemas', this.problemaSelecionado) },

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

    },
    async created() {
        this.isFetchingFromApi = true
        await axios
                .get('/problemas')
                .then(response => this.categorias = response.data )
                .finally(() => this.isFetchingFromApi = false)
    },

    mounted() {
        document.getElementById('inputText-problema-tipo').focus()
    }

}
</script>