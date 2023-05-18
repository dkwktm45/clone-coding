<template>
	<div>
		<h2>게시글 등록</h2>
		<hr class="my-4" />
		<AppError v-if="error" :message="error.message" />
		<PostForm
			v-model:title="form.title"
			v-model:content="form.content"
			@submit.prevent="save"
		>
			<template #actions>
				<button type="button" class="btn btn-outline-dark" @click="goListPage">
					목록
				</button>
				<button class="btn btn-primary" type="button" :disabled="loading">
					<template v-if="loading">
						<span
							class="spinner-border spinner-border-sm"
							role="status"
							aria-hidden="true"
						></span>
						<span class="sr-only">Loading...</span>
					</template>
					<button v-else class="btn btn-primary">저장</button>
				</button>
			</template>
		</PostForm>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { createPost } from '@/api/posts';
import PostForm from '@/components/posts/PostForm.vue';
import { useAlert } from '@/composables/alert';
import { useAxios } from '@/hooks/useAxios';

const { vAlert, vSuccess } = useAlert();
const router = useRouter();

const form = ref({
	title: null,
	content: null,
	createdAt: null,
});
const { error, loading, execute } = useAxios(
	'/posts',
	{
		method: 'post',
	},
	{
		immediate: false,
		onSuccess: () => {
			router.push({ name: 'PostListView' });
			vSuccess('작성이 완료됐습니다.');
		},
		onError: err => {
			vAlert(err.message);
			error.value = err.message;
		},
	},
);
const save = async () => {
	execute({ ...form.value, createdAt: Date.now() });
};

const goListPage = () => router.push({ name: 'PostListView' });
</script>
<style lang="scss" scoped></style>
