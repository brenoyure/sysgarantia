<template>
  <NavBarComponent/>
  <div class="container">
    <router-view />
  </div>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';

export default {
    components: { NavBarComponent }
}
</script>