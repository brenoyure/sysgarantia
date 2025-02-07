<script setup>
import { ProgressBar } from 'primevue';

</script>
<template>
    <div>
        <span>Carregando dados da API...</span>
    </div>
    <div class="card" style="margin-left: 7px;">
        <ProgressBar mode="indeterminate" style="height: 6px"></ProgressBar>
    </div>
    <!-- <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
        <span class="visually-hidden">Loading...</span>
    </div> -->
</template>

<style scoped>
.spinner-grow {
    margin-right: 5px;
}
</style>