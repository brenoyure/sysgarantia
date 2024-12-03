<template>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">Fornecedor</th>
            <th scope="col">E-mails(s)</th>
            <th scope="col">Informações</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>DATEN</td>
            <td>atendimento.governo@daten.com.br, suporte5@daten.com.br</td>
            <td><RouterLink class="btn btn-outline-secondary" to="/administracao/fornecedores/cadastro">Informações</RouterLink></td>
        </tr>
        
    </tbody>
</table>
</template>
<script>
export default {
    name: 'TableFornecedoresView'
}
</script>