<template>
    <h2>Administração / Clientes / Listagem</h2>
    <TableClientes />
</template>
<script>
import TableClientes from '@/components/TableClientes.vue';

export default {
    name: 'ListaClientesView',
    components: { TableClientes }
}
</script>