<script setup>
const title = document.title
</script>

<template>
  <section class="home-container bg-gradient-to-br from-base-100 to-base-200 p-10">
    <div class="text-center max-w-3xl mx-auto space-y-8">
      <h1 class="text-5xl font-bold text-primary">Bem-vindo ao {{ title }}</h1>
      <p class="text-lg text-base-content">
        O <strong>{{ title }}</strong> é uma aplicação moderna e eficiente para gerenciamento de pedidos,
        com foco em performance, escalabilidade e experiência do usuário.
      </p>

      <div class="divider text-neutral">Tecnologias Utilizadas</div>

      <div class="grid grid-cols-1 md:grid-cols-2 gap-6 text-left">
        <div class="card bg-base-200 shadow-md border border-base-300 p-5">
          <h2 class="text-xl font-semibold text-secondary mb-2">Frontend</h2>
          <ul class="list-disc pl-5 text-sm leading-relaxed">
            <li><strong>Vue 3</strong> — Framework JavaScript progressivo</li>
            <li><strong>Vite</strong> — Ferramenta de build moderna e rápida</li>
            <li><strong>SCSS</strong> — CSS com organização e estrutura</li>
            <li><strong>TailwindCSS</strong> — Utilitários CSS altamente customizáveis</li>
            <li><strong>DaisyUI</strong> — Componentes prontos sobre Tailwind</li>
          </ul>
        </div>

        <div class="card bg-base-200 shadow-md border border-base-300 p-5">
          <h2 class="text-xl font-semibold text-secondary mb-2">Backend</h2>
          <ul class="list-disc pl-5 text-sm leading-relaxed">
            <li><strong>Spring Boot</strong> — Framework Java robusto e produtivo</li>
            <li>Integração com bancos de dados</li>
          </ul>
        </div>
      </div>

      <div>
        <router-link to="/login" class="btn btn-primary mt-6">Acessar o sistema</router-link>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">

</style>
