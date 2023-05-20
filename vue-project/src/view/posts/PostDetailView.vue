<template>
	<AppLoading v-if="loading" />

	<AppError v-else-if="error" :message="error.message" />

	<div v-else>
		<h2>{{ post.title }}</h2>
		<p>id: {{ props.id }}, isOdd: {{ isOdd }}</p>
		<p>{{ post.content }}</p>
		<p class="text-muted">
			{{ $dayjs(post.createdAt).format('YYYY. MM. DD') }}
		</p>
		<hr class="my-4" />
		<AppError v-if="removeError" :message="removeError.message" />
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
				<button
					class="btn btn-outline-danger"
					@click="remove"
					:disabled="removeLoading"
				>
					<template v-if="removeLoading">
						<span
							class="spinner-border spinner-border-sm"
							role="status"
							aria-hidden="true"
						></span>
						<span class="sr-only">Loading...</span>
					</template>
					<template v-else>삭제</template>
				</button>
			</div>
		</div>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useAxios } from '@/hooks/useAxios';
import { useNumber } from '@/composables/number';
import { toRefs } from 'vue';
import { useAlert } from '@/composables/alert';
const props = defineProps({
	id: [String, Number],
});

const router = useRouter();
const { error, data: post, loading } = useAxios(`/posts/${props.id}`);
const { id: idRef } = toRefs(props);
const { isOdd } = useNumber(idRef);
const { vAlert, vSuccess } = useAlert();
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

const {
	error: removeError,
	loading: removeLoading,
	execute,
} = useAxios(
	`/posts/${props.id}`,
	{ method: 'delete' },
	{
		immediate: false,
		onSuccess: () => {
			vSuccess('삭제가 완료되었습니다.');
			router.push({ name: 'PostList' });
		},
		onError: err => {
			vAlert(err.message);
		},
	},
);
const remove = async () => {
	if (confirm('삭제 하시겠습니까?') === false) {
		return;
	}
	execute();
};
</script>

<style lang="scss" scoped></style>
