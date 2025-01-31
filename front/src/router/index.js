import { createRouter, createWebHistory } from 'vue-router'
import IndexView from '@/views/IndexView.vue'
import CadastroClienteView from '@/views/CadastroClienteView'
import CadastroFornecedorView from '@/views/CadastroFornecedorView.vue'
import ListaFornecedoresView from '@/views/ListaFornecedoresView.vue'
import ListaClientesView from '@/views/ListaClientesView.vue'
import CategoriasProblemas from '@/views/CategoriasProblemas.vue'
import TextosProntos from '@/views/TextosProntos.vue'
import PageNotFound from '@/views/PageNotFound.vue'
import RelatoriosGarantia from '@/views/RelatoriosGarantia.vue'
import CadastroEmailsTemplates from '@/views/CadastroEmailsTemplates.vue'

const routes = [

  {
    path: '/:pathMatch(.*)*',
    component: PageNotFound
  },

  {
    path: '/',
    component: IndexView
  },

  {
    path: '/administracao/problemas/categorias',
    component: CategoriasProblemas
  },

  {
    path: '/administracao/problemas/TextosProntos',
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
  },

  {
    path: '/administracao/relatorios',
    component: RelatoriosGarantia
  },

  {
    path: '/administracao/emailsTemplates/cadastro',
    component: CadastroEmailsTemplates
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
