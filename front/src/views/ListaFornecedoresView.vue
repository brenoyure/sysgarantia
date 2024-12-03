<template>
    <h2>Administração / Fornecedores / Listagem</h2>
    <TableFornecedores />
</template>
<script>
import TableFornecedores from '@/components/TableFornecedores.vue';

export default {
    name: 'ListaFornecedoresView',
    components: { TableFornecedores }
}
</script>