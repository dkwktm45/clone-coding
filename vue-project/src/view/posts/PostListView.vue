<template>
  <div>
    <h2>게시글 리스트</h2>
    <hr class="my-4" />
    <PostFilter v-model:title="params.title_like" v-model:limit="params._limit" />
    <hr class="my-4" />
    <AppGrid :items="posts">
      <template v-slot="{ item }">
        <PostItem
          :title="item.title"
          :content="item.content"
          :created-at="item.createdAt"
          @click="goPage(item.id)"
        ></PostItem>
      </template>
    </AppGrid>

    <AppPagination
      :current-page="params._page"
      :page-count="pageCount"
      @page="(page) => (params._page = page)"
    />

    <template v-if="posts && posts.length > 0">
      <hr class="my-5" />
      <AppCard>
        <PostDetailView :id="posts[0].id"></PostDetailView>
      </AppCard>
    </template>
  </div>
</template>

<script setup>
import PostItem from '@/components/posts/PostItem.vue'
import AppCard from '@/components/AppCard.vue'
import PostFilter from '@/components/posts/PostFilter.vue'
import AppPagination from '@/components/AppPagination.vue'
import PostDetailView from '@/view/posts/PostDetailView.vue'
import AppGrid from '@/components/AppGrid.vue'
import { createPost, getPosts } from '@/api/posts.js'
import { computed, ref, watchEffect } from 'vue'
import { useRouter } from 'vue-router'

// paging data
const params = ref({
  _sort: 'createdAt',
  _order: 'desc',
  _limit: 3,
  _page: 1,
  title_like: 9
})
const totalCount = ref(0)
const pageCount = computed(() => Math.ceil(totalCount.value / params.value._limit))
const posts = ref([])
const router = useRouter()
const fetchPosts = async () => {
  try {
    const { data, headers } = await getPosts(params.value) // 구조분해 할당?
    posts.value = data
    totalCount.value = headers['x-total-count']
  } catch (e) {
    console.log('error : ', e)
  }
}
fetchPosts()

watchEffect(fetchPosts) // 변경점이 생겼을 때 다시 값을 나타내게 해준다.
const goPage = (id) => {
  // router.push(`/posts/${id}`);
  router.push({
    name: 'PostDetailView',
    params: {
      id
    },
    query: {
      testText: 'hello'
    }
  })
}
</script>

<style lang="scss" scoped></style>
