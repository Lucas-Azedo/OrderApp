<script setup>
import { useRouter } from "vue-router";
import ThemeController from "@/components/ThemeController.vue";
import Breadcrumbs from "@/components/Breadcrumbs.vue";

const router = useRouter();
const title = 'OrderApp';
</script>

<template>
	<div class="fixed navbar bg-base-100 shadow-sm z-50">
		<div class="flex-1 w-auto">
			<router-link :to="{ name: 'home' }" class="text-xl">{{ title }}</router-link>
		</div>
		<div class="inline-flex flex-none gap-x-2">
			<router-link
				:to="{ name: 'home' }"
				class="btn btn-ghost"
				:class="{ 'btn-active': router.currentRoute.value.name === 'home' }"
			>Início</router-link>

			<router-link
				:to="{ name: 'login' }"
				class="btn btn-ghost"
				:class="{ 'btn-active': router.currentRoute.value.name === 'login' }"
			>Login</router-link>

			<ThemeController class="mx-2"/>
		</div>
	</div>

	<Breadcrumbs/>
</template>

<style scoped lang="scss">

</style>