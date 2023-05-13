<template>
	<div>
		<h2>{{ form.title }}</h2>
		<p>{{ form.content }}</p>
		<p class="text-muted">{{ form.createdAt }}</p>
		<hr class="my-4" />
		<div class="row g-2">
			<div class="col-auto">
				<div class="btn btn-outline-dark">이전글</div>
			</div>
			<div class="col-auto">
				<div class="btn btn-outline-dark">다음글</div>
			</div>
			<div class="col-auto me-auto"></div>
			<div class="col-auto">
				<div class="btn btn-outline-dark" @click="goListPage">목록</div>
			</div>
			<div class="col-auto">
				<div class="btn btn-outline-primary" @click="goEditPage">수정</div>
			</div>
			<div class="col-auto">
				<div class="btn btn-outline-danger">삭제</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { getPostById } from '@/api/posts';
import { ref } from 'vue';
const props = defineProps({
	id: Number,
});
const router = useRouter();
const form = ref(props.id);
// ref의 장점 한번에 객체 할당을 할 수 있다.
const fetchPost = () => {
	const data = getPostById(props.id);
	form.value = { ...data };
};
// 반면 reactive는 한번에 하나의 데이터만 할당 가능
// form.title = data.title; --> 객체 할당 불가능
fetchPost();
const goListPage = () =>
	router.push({
		name: 'PostListView',
	});

const goEditPage = () => {
	router.push({
		name: 'PostEditView',
		params: { id: props.id },
	});
};
</script>

<style lang="scss" scoped></style>
