<template>

<div class="accordion" id="accordionPanelsStayOpenExample">
    {{ conteudo }}
</div>

</template>

<script setup>
import defineProps from 'vue'

defineProps({ conteudo: String })

</script>
