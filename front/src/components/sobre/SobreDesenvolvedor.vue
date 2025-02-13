<template>

<div style="text-align: center;">
    <h5>ℹ️ Um pouco sobre o Desenvolvedor ℹ️</h5>
    <p>
    Olá, sou Breno Yuri, estudante do curso de <i>Análise e Desenvolvimento de Sistemas</i> da faculdade <i>Unifanor Wyden</i>.
<br>
    Estudo programação, desde 2022, com os cursos do <i>Guanabara</i>, e logo em seguida, até então, pela plataforma <i>Alura</i>.
<br>
    Meu foco maior é na parte do backend, utilizando como linguagem principal o Java.
<br>
    Meus dois hobbies, jogar no PC, e recentemente programar (resolver problemas/buxos)
  </p>

  <h5>🛠️ Algumas Ferramentas que Utilizo 🛠️</h5>
  <p>
    Utilizo outras tecnologias para auxilar também, como a 
    ferramenta de containers <i>Docker</i>, simplificando bastante a parte de colocar o sistema em produção, assim como, 
    as ferramentas <i>Terraform</i> e <i>Ansible</i> que automatizam a parte de provisionar e configurar um ambiente (VM) em nuvem,
    atualmente a da <i>AWS</i>. Estou engatinhando ainda, criando máquinas <i>EC2</i> e o banco de dados, utilizando o <i>RDS</i> (banco gerenciado), ao invés de 
    instalar o banco direto na VM ou utiliza-lo a partir do Docker.
  </p>

  <h5>💡 Ideia do projeto 💡</h5>
  <p>
    A ideia inicial deste projeto veio no meu trabalho anterior, quando acessamos o banco de dados do sistema de chamados, o <i>OTRS</i>, 
    para fazermos relatórios dos chamados para o PowerBI, e por curiosidade, começei a mapear as tabelas do banco em entidades (classes) da JPA do Java.
  </p>
  <p>
    Começei estudando as tabelas do banco, ao abrir um ticket de chamado, onde ficam todas as informações, como nome do serviço, responsável e etc.
    E como era armazenado a parte do inventário dos equipamentos, no <i>OTRS</i>, chamado de <i>CMDB</i>, antigo <i>BDGC</i>.
    A partir daí, começei a criar funções para retornar o número de série do equipamento baseado no seu identificador, listar os chamados utilizando filtros, como, 
    chamados abertos da data X à data Y, simulando exatamente relatórios. 
  </p>
  <p>
    O que aconteceu foi que nessa época eu estava fazendo a formação JavaEE da Alura, e já tinha passado pela parte do JSF (especificação JavaEE para criação de interfaces na web), e 
    me veio a ideia de criar o sistema de envio de solicitaçãoes de garantia para o setor de suporte, ainda imaginava que era algo muito complexo fazer o front end utilizando os frameworks frontend ou html e javascript puros.
  </p>
  <p>
    Basicamente, na formação JavaEE, vemos EJBs, injeção de dependências, API JavaMail (para envio de e-mails), JSF, JPA (para a persistência de dados), e a parte de autenticação e autorização.
    Então eu só precisava juntar todos esses conhecimentos para criar o sistema.
  </p>

  <h5>🤔 Mas por que desenvolver esse sistema? Reinventar a roda? 🤔</h5>
  <p>
    🤔 Todo sistema de chamado tem a opção de enviar e-mails a partir dos tickets, então qual a vantagem de utilizar esse sistema? 🤔
  </p>
  <p>
    Quando trabalhava no suporte tinha um fornecedor de computadores, que exigia o preenchimento de um formulário deles, ao solicitar garantia, provavelmente o sistema deles 
    "lia" os campos do formulário (em .docx) e abria o chamado. Tinhamos que abrir arquivos <i>docx</i> de solicitações anteriores, copiarmos o texto do problema, trocar número de série, enfim 
    um processo manual e muito sussetível a falhas, e complicado para ensinar pessoas novas no setor.
  </p>
  <p>
    Então tive a ideia do sistema de garantia, na primeira versão da tela <i>Serviço de Garantia</i> só tinham alguns campos, 
    número de série, ticket (do sistema de chamados local), e uma combobox para selecionar o problema, e um campo para você enviar anexo.
    O restante como corpo e assunto do e-mail, era hardcoded, ou seja, era em código não tinha como alterar, mas para a primeira versão já funcionava bem.
    Conseguimos então, enviar uma solicitação de garantia para esse fornecedor, em coisa de 5 segundos. Muito rápido não?
  </p>
  <p>
    Sobre a integração com o sistema de chamados era assim: só apareciam os tickets abertos na fila do Nível 1 (Setor de Suporte), e o sistema de garantia enviava o e-mail com cópia para o OTRS, 
    com o assunto iniciando com <i>[Ticket#numero_do_ticket]</i>, dessa forma, ao receber o e-mail, o sistema de chamados colocava esse e-mail dentro do ticket, ou seja, 
    como se o técnico tivesse enviado a partir do sistema de chamados, ficando tudo organizado, como se estivessemos fazendo da maneira manual.
  </p>
  <p>
    Tenho intenção de disponibilizar esse sistema de garantia e oferecer suporte total, a quem quiser utiliza-lo em sua empresa. O front-end é separado do back, apenas consome a API, então pode ficar 
    livre para modifica-lo e utilizar a interface que mais lhe agrade. Utilizei o Bootstrap para os estilos pois não domino muito front-end, a parte de CSS avançado com responsividade.
  </p>

  <h5>😎 Melhorias das versões recentes 😎</h5>
  <p>
    Nas versões recentes para deixar o sistema mais dinâmico, e como também, já ia sair do trabalho (o anterior), criei a parte de poder cadastrar (o crud) fornecedores e clientes.
    Nos fornecedores com a integração com o sistema de chamados, ou seja, você cadastra o fornecedor e marca quais serviços são dele, para que ao selecionar o fornecedor, o sistema filtre 
    os tickets do fornecedor e que estão abertos ou com status de novo.
  </p>
  <p>
    Tendo como inspiração uma funcionalidade do OTRS, criei a parte de <i>Modelos de E-mails</i>. Basicamente no OTRS, possui a parte de templates ao criar por exemplo um modelo de e-mail em que você utiliza 
    alguma sintaxe do sistema e ele substitui por um valor dinâmico. O que eu fiz foi basicamente estender isso para todos os dados que uma solicitação de garantia possui.
    Ao criar um modelo de e-mail, ao digitar, por exemplo "Prezados atendente do fornecedor $fornecedor.nome falamos da $cliente.nome, nosso endereço é $cliente.endereco.logradouro", 
    o sistema substitui por ""Prezados atendente do fornecedor D3LL Corp. falamos da Empresa Cliente LTDA, nosso endereço é Rua Coronel de Carvalho", ou seja, você pode reaproveitar o modelo de e-mail 
    para vários fornecedores, clientes e equipamentos.
  </p>

  <p class="text-center text-body-secondary">
    Em caso de dúvidas, sugestões, problemas, suporte: enviar para <a href="mailto:byurifb@gmail.com" target="_blank" class="texto-git-dev">byurifb@gmail.com</a>
  </p>

</div>

</template>

<style scoped>

.texto-git-dev {
  text-decoration: none;
  font-style: italic;
  border-radius: 10px;
}

.texto-git-dev:hover {
  animation-name: texto-git-dev-animation-hover;
  animation-duration: 300ms;
  animation-fill-mode: forwards;
}

.texto-git-dev:active {
  animation-name: texto-git-dev-animation-active;
  animation-duration: 300ms;
  animation-fill-mode: forwards;
}

@keyframes texto-git-dev-animation-hover {
  to {
    padding: 5px;
    border: solid #80bdff 1px;
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
  }
}

@keyframes texto-git-dev-animation-active {
  to {
    padding: 10px;
    text-decoration: underline;
    border: solid #80bdff 1px;
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
    font-style: normal;
  }
}

.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  width: 100%;
  height: 3rem;
  background-color: rgba(0, 0, 0, .1);
  border: solid rgba(0, 0, 0, .15);
  border-width: 1px 0;
  box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}

.btn-bd-primary {
  --bd-violet-bg: #712cf9;
  --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

  --bs-btn-font-weight: 600;
  --bs-btn-color: var(--bs-white);
  --bs-btn-bg: var(--bd-violet-bg);
  --bs-btn-border-color: var(--bd-violet-bg);
  --bs-btn-hover-color: var(--bs-white);
  --bs-btn-hover-bg: #6528e0;
  --bs-btn-hover-border-color: #6528e0;
  --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
  --bs-btn-active-color: var(--bs-btn-hover-color);
  --bs-btn-active-bg: #5a23c8;
  --bs-btn-active-border-color: #5a23c8;
}

.bd-mode-toggle {
  z-index: 1500;
}

.bd-mode-toggle .dropdown-menu .active .bi {
  display: block !important;
}
</style>