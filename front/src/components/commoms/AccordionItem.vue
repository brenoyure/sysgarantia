<template>
<div class="accordion" id="accordionPanelsStayOpenExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
        {{ titulo }}
      </button>
    </h2>
    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show">
      <div class="accordion-body">
        {{ conteudo }}
      </div>
    </div>
  </div>
</div>

</template>

<script setup>
import defineProps from 'vue'

defineProps({
    titulo: String,
    conteudo: String
})

</script>