<template>
    <h2>
        <i class="bi bi-text-left"></i>
        Cadastro de Textos Prontos
    </h2>

    <div style="display: grid;">   
        <form style="display: grid;" @submit.prevent="cadastrarNovaDescricaoProblema" >
            <div v-if="isFetchingFromApi">
                <LoadingFromApi />
            </div>
            <div>
                <label style="font-weight: bold;" for="selectOne-descricaoProblema-categoria">Selecione uma Categoria: </label>
                <select id="selectOne-descricaoProblema-categoria" v-model="descricaoProblema.problemaId" class="form-select" required>
                    <option value="null">Selecione uma Categoria</option>
                    <option :value="categoria.id" :key="categoria.id" v-for="categoria in categorias">{{ categoria.tipo }}</option>
                </select>
            </div>
            <div>
                <label for="novoProblema-descricaoResumida" style="font-weight: bold">Descrição Resumida: </label>
                <input maxlength="55" id="novoProblema-descricaoResumida" class="form-control" v-model="descricaoProblema.descricaoResumida" required placeholder="ex. Mouse - Problema no Scroll" >
                <p v-if="descricaoProblema.descricaoResumida.length >= 30">Caracteres Digitados: {{ descricaoProblema.descricaoResumida.length }} de 55</p>
            </div>
            <div>
                <label for="novoProblema-descricaoDetalhada" style="font-weight: bold">Descrição Detalhada: </label>
                <textarea maxlength="255" rows="5" id="novoProblema-descricaoDetalhada" class="form-control" v-model="descricaoProblema.descricaoDetalhada" required placeholder="ex. Equipamento está apresentando defeito de ..." ></textarea>
                <p v-if="descricaoProblema.descricaoDetalhada.length >= 230">Caracteres Digitados: {{ descricaoProblema.descricaoDetalhada.length }} de 255</p>
            </div>
            <div style="display: inherit; margin-top: 5px;">
                <button id="btn-cadastrarNovaDescricaoProblema" class="btn btn-outline-primary">Salvar</button>
            </div>
        </form>
    
    <hr>
        <div>
            <h2>Textos Prontos Cadastrados</h2>
            <div>
                <p>
                    <b>Dica: </b>Clique em um dos campos abaixo edita-lo.
                </p>
                <p>
                    O item atualizado ficará com uma borda fina azul abaixo, indicando a alteração recente.
                </p>
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Categoria</th>
                        <th scope="col">Descrição Resumida</th>
                        <th scope="col">Descrição Detalhada</th>
                    </tr>
                </thead>
                <tbody id="tableBody-descricaoProblemas">
                    <tr v-for="descricaoProblema in descricaoProblemas" :key="descricaoProblema.id">
                        <td hidden>{{ descricaoProblema.id }}</td>          <!-- Id de cada Entidade -->
                        <td @click="editDescricaoCategoria" style="cursor: pointer;">{{ descricaoProblema.problema.tipo }}</td>
                        <td @click="editDescricaoResumo"    style="cursor: pointer;">{{ descricaoProblema.descricaoResumida }}</td>
                        <td @click="editDescricaoDetalhe"   style="cursor: pointer;">{{ descricaoProblema.descricaoDetalhada }}</td>
                        <td hidden>{{ descricaoProblema.problema.id }}</td> <!-- Id da Categoria -->
                        <td>
                            <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" :data-bs-target="`#exclueDescricaoProblem${descricaoProblema.id}`">Excluir</button>
                            <div class="modal fade" :id="`exclueDescricaoProblem${descricaoProblema.id}`" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmação de Exclusão</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <label>Confirmar Exclusão do Texto Pronto: <span style="text-transform: capitalize; font-weight: bold;">{{ descricaoProblema.descricaoResumida }}</span> ?</label>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-outline-danger" data-bs-dismiss="modal" @click="excluirDescricaoProblema">Excluir</button>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <LoadingFromApi v-if="isFetchingFromApi" />
                </tbody>
            </table>
        </div>
    
    </div>
    
    </template>
    
    <script>
    import axios from '@/axios'
    import LoadingFromApi from '@/components/LoadingFromApi.vue';
    export default {
        components: { LoadingFromApi },
        data() {
            return {
                isFetchingFromApi: false,
                categorias: [],
                descricaoProblemas: [],
                descricaoProblema: {
                    descricaoResumida: '',
                    descricaoDetalhada: '',
                    problemaId: null
                },
                categoriaSelecionada: null
            }
        },
    
        methods: {
            async cadastrarNovaDescricaoProblema() {
                const buttonCadastrar = document.getElementById('btn-cadastrarNovaDescricaoProblema')
                buttonCadastrar.innerText = 'Salvando...'
                buttonCadastrar.disabled = true
    
                await axios
                    .post('/descricaoProblemas', this.descricaoProblema)
                    .then(() => {
                        this.showToast('success', 'Novo Texto Pronto cadastrado com sucesso')
                        this.descricaoProblema = this.criarDtoDeAtualizacao()
                        this.fetchDescricaoProblemas()
                    })
                    .catch(error => {
                        console.log(error);
                        if (!Array.isArray(error.response.data)) {
                            this.showToast('error', error.response.data.error)
                        } else {
                            error.response.data.forEach(error => {
                                this.showToast('error', error)
                            })
                        }
                    }).finally(() => {
                        buttonCadastrar.innerText = 'Salvar'
                        buttonCadastrar.disabled = false
                    })
    
    
            },
    
            criarDtoDeNovaDescricaoProblema() {
                return {
                    descricaoResumida: null,
                    descricaoDetalhada: null,
                    problemaId: null }
            },
    
            editDescricaoCategoria(event) {
                const categoriaDataRow = event.target
                const categoriaIdDaRow = categoriaDataRow.parentElement.children[4].innerText
                const selectCategoria = document.createElement('select')
                selectCategoria.className = 'form-select'
                selectCategoria.style.width = '25vw'
                selectCategoria.style.marginRight = '5px'
                this.categorias.forEach(categoria => {
                    const categoriaOption = document.createElement('option')
                    categoriaOption.value = categoria.id
                    categoriaOption.innerText = categoria.tipo
                    selectCategoria.appendChild(categoriaOption)
                    if (categoria.id == categoriaIdDaRow) {
                        categoriaOption.selected = true
                    }
                })
                categoriaDataRow.replaceWith(selectCategoria)
                selectCategoria.focus()
                selectCategoria.addEventListener('focusout', () => {
                    const descricaoRow = selectCategoria.parentElement.children
                    const dtoDeAtualizacao =
                        this.criarDtoDeAtualizacao(descricaoRow[0].innerText,
                                                   descricaoRow[2].innerText,
                                                   descricaoRow[3].innerText,
                                                   selectCategoria.value)
    
                    const selectedCategoriaOptionText = selectCategoria.selectedOptions.item(0).text
                    categoriaDataRow.innerText = selectedCategoriaOptionText
                    selectCategoria.parentElement.children[4].innerText = selectCategoria.value
    
                    this.atualizarDescricaoProblema(dtoDeAtualizacao, categoriaDataRow, categoriaIdDaRow)
    
                    selectCategoria.replaceWith(categoriaDataRow)
                    selectCategoria.remove()
    
                })
    
            },
    
            editDescricaoResumo(event) {
                const resumoDataRow = event.target
                const descricaoResumoOriginalText = resumoDataRow.textContent
                const inputResumo = document.createElement('input')
                inputResumo.size = 50
                inputResumo.className = 'form-control'
                inputResumo.value = resumoDataRow.innerText
                inputResumo.oninput = event => resumoDataRow.innerText = event.target.value
                inputResumo.addEventListener('focusout', () => {
                    const descricaoRow = inputResumo.parentElement.children
                    const dtoDeAtualizacao = 
                        this.criarDtoDeAtualizacao(descricaoRow[0].textContent, /** Id da Entidade      */
                                                   inputResumo.value,           /** Descrição Resumida  */
                                                   descricaoRow[3].textContent, /** Descrição Detalhada */
                                                   descricaoRow[4].textContent) /** Id da Categoria     */
                    this.atualizarDescricaoProblema(dtoDeAtualizacao, resumoDataRow, descricaoResumoOriginalText)
                    inputResumo.replaceWith(resumoDataRow)
                    inputResumo.remove()})
    
                resumoDataRow.replaceWith(inputResumo)
                inputResumo.focus()
            },
    
            editDescricaoDetalhe(event) {
                const detalheDataRow = event.target
                const descricaoDetalheOriginalText = detalheDataRow.textContent
                const inputDetalhe = document.createElement('textarea')
                inputDetalhe.rows = 5
                inputDetalhe.className = 'form-control'
                inputDetalhe.style.marginBottom = '5px'
                inputDetalhe.value = detalheDataRow.innerText
                inputDetalhe.oninput = event => detalheDataRow.textContent = event.target.value
                inputDetalhe.addEventListener('focusout', () => {
                    const descricaoRow = inputDetalhe.parentElement.children
                    const dtoDeAtualizacao = 
                        this.criarDtoDeAtualizacao(descricaoRow[0].innerText, /** Id da Entidade      */
                                                   descricaoRow[2].innerText, /** Descrição Resumida  */
                                                   inputDetalhe.value,        /** Descrição Detalhada */
                                                   descricaoRow[4].innerText) /** Id da Categoria     */
                    this.atualizarDescricaoProblema(dtoDeAtualizacao, detalheDataRow, descricaoDetalheOriginalText)
                    inputDetalhe.replaceWith(detalheDataRow)
                    inputDetalhe.remove()
                })
                detalheDataRow.replaceWith(inputDetalhe)
                inputDetalhe.focus()
            },
    
            criarDtoDeAtualizacao(id, descricaoResumida, descricaoDetalhada, categoriaId) {
                return {
                    id: id,
                    descricaoResumida: descricaoResumida,
                    descricaoDetalhada: descricaoDetalhada,
                    problemaId: categoriaId }
            },
    
            async atualizarDescricaoProblema(dtoAtualizacao, dataRowForErrorCallBack, originalTextForErrorCallBack) {
                await axios
                    .put('/descricaoProblemas', dtoAtualizacao)
                    .then(() => {
                        dataRowForErrorCallBack.style.borderColor = 'blue'
                        this.showToast('success', `Texto Pronto <b>${dtoAtualizacao.descricaoResumida}</b> atualizado com sucesso`)
                    })
                    .catch((error) => {
                        console.log(error)
                        if (!Array.isArray(error.response.data)) {
                            this.showToast('error', error.response.data.error, '')
                        }
                        dataRowForErrorCallBack.style.borderColor = 'red'
                        dataRowForErrorCallBack.textContent = originalTextForErrorCallBack
                    })
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
    
            async excluirDescricaoProblema(event) {
                let rowToBeDeleted = event.target.parentElement
                while (!(rowToBeDeleted instanceof HTMLTableRowElement)) {     /* Chama o parentElement, no caso o botão */
                    rowToBeDeleted = rowToBeDeleted.parentElement            /* de Excluir de dentro do Modal até chegar no Table Row  */
                }
                const descProblemId      = rowToBeDeleted.cells[0].innerText
                const descProblemResumo  = rowToBeDeleted.cells[2].innerText
    
                await axios
                    .delete(`/descricaoProblemas/${descProblemId}`)
                    .then(() => {
                        this.showToast('success', `Texto Pronto: ${descProblemResumo} excluído com sucesso`)
                        rowToBeDeleted.remove()})
            },
    
            async fetchDescricaoProblemas() {
                await axios
                    .get('/descricaoProblemas')
                    .then(response => this.descricaoProblemas = response.data)
                    .finally(() => {
                        const selectOneDescricaoProblemaCategoria = document.getElementById('selectOne-descricaoProblema-categoria')
                        selectOneDescricaoProblemaCategoria.options[0].selected = true
                        selectOneDescricaoProblemaCategoria.focus() 
                    })
            }
    
        },

        async mounted() {
            this.isFetchingFromApi = true
            await axios
                    .get('/problemas')
                    .then(response => response.data.forEach(c => this.categorias.push(c)))
                    .then(await this.fetchDescricaoProblemas())
                    .finally(() => this.isFetchingFromApi = false)
        }
    
    }

    /** -- Método para Criar uma nova Row --
            criarNovaDataRowDescricaoProblema(id, categoriaTipo, descricaoResumida, descricaoDetalhada, categoriaId) {
                const tableBody = document.getElementById('tableBody-descricaoProblemas')
                const row = tableBody.insertRow()
    
                const descProblmIdHiddenDataRow = row.insertCell()
                descProblmIdHiddenDataRow.innerText = id
                descProblmIdHiddenDataRow.hidden = true
    
                const categoriaTipoDataRow = row.insertCell()
                categoriaTipoDataRow.innerText = categoriaTipo
                categoriaTipoDataRow.onclick = this.editDescricaoCategoria
    
                const descResumo = row.insertCell()
                descResumo.innerText = descricaoResumida
                descResumo.onclick = this.editDescricaoResumo
    
                const descDetalhe = row.insertCell()
                descDetalhe.innerText = descricaoDetalhada
                descDetalhe.onclick = this.editDescricaoDetalhe
    
                const categoriaIdHiddenDataRow = row.insertCell()
                categoriaIdHiddenDataRow.innerText = categoriaId
                categoriaIdHiddenDataRow.hidden = true
    
                for (let i = 0; i < row.cells.length; i++) {
                    const dataRow = row.cells.item(i);
                    dataRow.style.cursor = 'pointer'
                }
            },
     */
    
    </script>