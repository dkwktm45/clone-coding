<template>
	<div>
		<h2>About View</h2>
		<p>{{ $route.path }}</p>
		<button class="btn btn-primary" @click="goHomePage()">Home</button>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const goHomePage = () => {
	router.push({
		name: 'Home',
	});
};
</script>

<style></style>
