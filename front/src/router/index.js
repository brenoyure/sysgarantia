import { createRouter, createWebHistory } from 'vue-router'
import IndexView from '@/views/IndexView.vue'
import CadastroClienteView from '@/views/CadastroClienteView'
import CadastroFornecedorView from '@/views/CadastroFornecedorView.vue'
import ListaFornecedoresView from '@/views/ListaFornecedoresView.vue'

const routes = [
  {
    path: '/',
    component: IndexView
  },

  {
    path: '/administracao/clientes/cadastro',
    component: CadastroClienteView
  },

  {
    path: '/administracao/fornecedores/cadastro',
    component: CadastroFornecedorView
  },

  {
    path: '/administracao/fornecedores/listagem',
    component: ListaFornecedoresView
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
