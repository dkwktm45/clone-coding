<template>
	<div>
		<h2>게시글 수정</h2>
		<hr class="my-4" />
		<PostForm
			v-model:title="form.title"
			v-model:content="form.content"
			@submit.prevent="edit"
		>
			<template #actions>
				<button
					type="button"
					class="btn btn-outline-danger"
					@click="goDetailPage"
				>
					취소
				</button>
				<button class="btn btn-primary">수정</button>
			</template>
		</PostForm>
		<AppAlert :items="alert" />
	</div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { getPostById, updatePost } from '@/api/posts';
import PostForm from '@/components/posts/PostForm.vue';
const router = useRouter();
const route = useRoute();
const id = route.params.id;
const form = ref({
	title: null,
	content: null,
	createdAt: null,
});

const fetchPost = async () => {
	const { data } = await getPostById(id);
	setFrom(data);
};
fetchPost();
const setFrom = ({ title, content, createdAt }) => {
	form.value.title = title;
	form.value.content = content;
	form.value.createdAt = createdAt;
};
const edit = async () => {
	try {
		await updatePost(id, { ...form.value });
		// router.push({ name: 'PostDetailView', params: { id } })
		vAlert('수정이 완료 됐습니다.', 'success');
	} catch (e) {
		vAlert(e.message);
	}
};
const goDetailPage = () =>
	router.push({
		name: 'PostListView',
		params: { id },
	});

const alert = ref([]);
const vAlert = (message, type = 'error') => {
	alert.value.push({ message, type });
	setTimeout(() => {
		// showAlert.value = false
		alert.value.shift();
	}, 2000);
};
</script>

<style lang="scss" scoped></style>
