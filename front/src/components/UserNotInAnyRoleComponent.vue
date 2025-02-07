<script setup>
import { onMounted } from 'vue';
import NavBarComponent from './NavBarComponent.vue';

onMounted(() => {
    const navLinks = document.getElementsByTagName('a')
    for (let i = 0; i < navLinks.length; i++) {
        navLinks.item(i).classList.add('disabled')
        navLinks.item(i).style.cursor = 'not-allowed'
    }
    const routerLinks = document.getElementsByTagName('routerlink')
    for (let i = 0; i < routerLinks.length; i++) {
        routerLinks.item(i).classList.add('disabled')
        routerLinks.item(i).style.cursor = 'not-allowed'
    }
})

</script>

<template>
    <NavBarComponent />
    <div class="container">
        <div class="alert alert-info" role="alert" style="text-align: center; cursor: default;">
            <h6>Você logou com Sucesso</h6>
        </div>
        <div class="alert alert-danger" role="alert" style="text-align: center; cursor: default;">
            <h6>
                Porém, seu usuário não está em nenhuma <i>role</i> de permissões.
                <br>
                Contate um usuário com permissões administrativas no KeyClock (Identity Provider) para que ele lhe atribua alguma role, pelo menos a <i>USER</i>.
                <br>
            </h6>
        </div>
    </div>

</template>
