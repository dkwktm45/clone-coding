<template>
	<div>
		<h2>게시글 리스트</h2>
		<hr class="my-4" />
		<PostFilter
			v-model:title="params.title_like"
			:limit="params._limit"
			@update:limit="changeLimit"
		/>
		<hr class="my-4" />
		<AppLoading v-if="loading" />

		<AppError v-else-if="error" :message="error.message" />

		<template v-else-if="!isExist">
			<p class="text-center py-5 text-muted">No Results</p>
		</template>
		<template v-else>
			<AppGrid :items="posts" col-class="col-12 col-sm-6 col-md-4 col-lg-3">
				<template v-slot="{ item }">
					<PostItem
						:title="item.title"
						:content="item.content"
						:created-at="item.createdAt"
						@click="goPage(item.id)"
						@modal="openModal(item)"
						@preview="selectPreview(item.id)"
					></PostItem>
				</template>
			</AppGrid>

			<AppPagination
				:current-page="params._page"
				:page-count="pageCount"
				@page="page => (params._page = page)"
			/>
		</template>
		<Teleport to="#modal">
			<PostModel
				v-model="show"
				:title="modalTitle"
				:content="modalContent"
				:created-at="modalCreatedAt"
			>
			</PostModel>
		</Teleport>
		<template v-if="previewId">
			<hr class="my-5" />
			<AppCard>
				<PostDetailView :id="previewId"></PostDetailView>
			</AppCard>
		</template>
	</div>
</template>

<script setup>
import PostItem from '@/components/posts/PostItem.vue';
import PostFilter from '@/components/posts/PostFilter.vue';
import PostDetailView from '@/view/posts/PostDetailView.vue';
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import PostModel from '@/components/posts/PostModel.vue';
import { useAxios } from '@/hooks/useAxios';

const previewId = ref(null);
const selectPreview = id => (previewId.value = id);
const changeLimit = value => {
	params.value._limit = value;
	params.value._page = 1;
};
// paging data
const params = ref({
	_sort: 'createdAt',
	_order: 'desc',
	_limit: 6,
	_page: 1,
});
const isExist = computed(() => posts.value && posts.value.length > 0);
const totalCount = computed(() => response.value.headers['x-total-count']);
const pageCount = computed(() =>
	Math.ceil(totalCount.value / params.value._limit),
);
const router = useRouter();

const {
	response,
	error,
	data: posts,
	loading,
} = useAxios('/posts', { params });

const goPage = id => {
	// router.push(`/posts/${id}`);
	router.push({
		name: 'PostDetailView',
		params: {
			id,
		},
		query: {
			testText: 'hello',
		},
	});
};

const show = ref();

const modalTitle = ref('');
const modalContent = ref('');
const modalCreatedAt = ref('');
const openModal = ({ title, content, createdAt }) => {
	modalTitle.value = title;
	show.value = true;
	modalContent.value = content;
	modalCreatedAt.value = createdAt;
};
</script>

<style lang="scss" scoped></style>
