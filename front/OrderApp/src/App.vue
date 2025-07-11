<script setup>
import { RouterView } from "vue-router";
import TheHeader from "@/components/TheHeader.vue";
</script>

<template>
	<TheHeader/>

	<main class="py-8">
		<router-view />
	</main>
</template>

<style scoped lang="scss">
</style>
