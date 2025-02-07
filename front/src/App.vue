<template>
  <NavBarComponent />
  <div class="container">
    <UserDoesntHaveRequiredPermissions v-if="userDoesntHaveRequiredPermissions" />
    <RouterView />
  </div>
</template>

<script setup>
import { RouterView, useRouter } from 'vue-router';
import NavBarComponent from '@/components/NavBarComponent.vue';
import { onMounted, ref } from 'vue';
import userdetails from './userdetails';
import UserDoesntHaveRequiredPermissions from './components/UserDoesntHaveRequiredPermissions.vue';

const userDoesntHaveRequiredPermissions = ref(false)

const isUserInRole = (role => {
  if (userdetails && userdetails.roles.length > 0) {
    return userdetails.roles.includes(role)
  }
  return true
  })

onMounted(() => {
  useRouter().beforeEach((to, from, next) => {
    if (to.path.startsWith('/administracao') && !isUserInRole('ADMIN')) {
      userDoesntHaveRequiredPermissions.value = true
    } else {
      userDoesntHaveRequiredPermissions.value = false
      next()
    }
  })
})

</script>