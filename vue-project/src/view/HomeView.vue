<template>
	<div>
		<h2>Home View</h2>
		<!-- 모든 자식 컴포넌트에서 패스를 설정할 수 있다.-->
		<p>{{ $route.path }}</p>
		<button class="btn btn-primary" @click="$router.push('/about')">
			About으로 이동
		</button>
	</div>
</template>

<script setup>
import { useRoute } from 'vue-router';
const route = useRoute();
console.log('route path', route.path);
</script>

<style lang="scss" scoped></style>
