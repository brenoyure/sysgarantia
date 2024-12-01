import { createRouter, createWebHistory } from 'vue-router'
import IndexView from '@/views/IndexView.vue'
import CadastroClienteView from '@/views/CadastroClienteView'

const routes = [
  {
    path: '/',
    name: 'index',
    component: IndexView
  },
  
  {
      path: '/administracao/clientes/cadastro',
      name: 'administracao-clientes-cadastro',
      component: CadastroClienteView
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
