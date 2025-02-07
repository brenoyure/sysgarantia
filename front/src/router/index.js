import CadastroClienteView from '@/views/CadastroClienteView'
import CadastroEmailsTemplates from '@/views/CadastroEmailsTemplates.vue'
import CadastroFornecedorView from '@/views/CadastroFornecedorView.vue'
import CategoriasProblemas from '@/views/CategoriasProblemas.vue'
import IndexView from '@/views/IndexView.vue'
import ListaClientesView from '@/views/ListaClientesView.vue'
import ListaEmailsTemplatesView from '@/views/ListaEmailsTemplatesView.vue'
import ListaFornecedoresView from '@/views/ListaFornecedoresView.vue'
import PageNotFound from '@/views/PageNotFound.vue'
import RelatoriosGarantia from '@/views/RelatoriosGarantia.vue'
import TextosProntos from '@/views/TextosProntos.vue'
import { createRouter, createWebHistory } from 'vue-router'

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
  },

  {
    path: '/administracao/emailsTemplates/listagem',
    component: ListaEmailsTemplatesView
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
