<template>
<div class="container" style="cursor: default">
  <footer class="py-3 my-4">
    <p class="text-center text-body-secondary">&copy; 2025 Serviço de Garantia por <a href="https://github.com/brenoyure" target="_blank" class="texto-git-dev">github.com/brenoyure</a></p>
    <p class="text-center text-body-secondary">Projeto em desenvolvimento: dúvidas, sugestões, problemas: enviar para <a href="mailto:byurifb@gmail.com" target="_blank" class="texto-git-dev">byurifb@gmail.com</a></p>
    <p class="text-center text-body-secondary">A versão atual do sistema, possui integração com o banco de dados MySQL/MariaDB do sistema de chamados OTRS/Znuny</p>
  </footer>
</div>
</template>
<script setup>

</script>

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