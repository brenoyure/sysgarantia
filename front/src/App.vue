<template>
  <NavBarComponent/>
  <div class="container">
    <RouterView />
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
import NavBarComponent from '@/components/NavBarComponent.vue';

</script>