<template>
	<div>
		<h2>Home View</h2>
		<!-- 모든 자식 컴포넌트에서 패스를 설정할 수 있다.-->
		<p>{{ $route.path }}</p>
		<button class="btn btn-primary" @click="$router.push('/about')">
			About으로 이동
		</button>
		<AppGrid :items="items" v-slot="{ item }" col-class="col-6">
			<AppCard>{{ item }}</AppCard>
		</AppGrid>
		<h2>store</h2>
		<p>counter: {{ store.counter }}</p>
		<p>doubleCount: {{ store.doubleCount }}</p>
		<p>구조분해 counter: {{ counter }}</p>
		<p>구조분해 doubleCount: {{ doubleCount }}</p>
		<button @click="store.increment()">click!!</button>
	</div>
</template>

<script></script>
<script setup>
import { ref } from 'vue';
import { useCounterStore } from '@/store/counter';
import { storeToRefs } from 'pinia';

const items = ref(['사과', '딸기', '포도', '바나나']);
const store = useCounterStore();

// 구조분해 할당을 할때는 store 내부에 정의된 메소드 storeToRefs를 사용하자
// - 구조분해 할당을 하게 된다면 반응형을 잃어버리기 때문!!
const { counter, doubleCount } = storeToRefs(store);
</script>

<style lang="scss" scoped></style>
