import { createRouter, createWebHistory } from 'vue-router'
import IndexView from '@/views/IndexView.vue'
import CadastroClienteView from '@/views/CadastroClienteView'
import CadastroFornecedorView from '@/views/CadastroFornecedorView.vue'
import ListaFornecedoresView from '@/views/ListaFornecedoresView.vue'
import ListaClientesView from '@/views/ListaClientesView.vue'
import TextosProntos from '@/views/TextosProntos.vue'

const routes = [
  {
    path: '/',
    component: IndexView
  },

  {
    path: '/administracao/TextosProntos',
    component: TextosProntos
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
  },

  {
    path: '/administracao/clientes/listagem',
    component: ListaClientesView
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
