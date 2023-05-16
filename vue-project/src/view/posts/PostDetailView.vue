<template>
  <div>
    <h2>{{ post.title }}</h2>
    <p>{{ post.content }}</p>
    <p class="text-muted">{{ post.createdAt }}</p>
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
        <div class="btn btn-outline-danger" @click="remove">삭제</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { deletePost, getPostById } from '@/api/posts'
import { ref } from 'vue'
const post = ref({})
const props = defineProps({
  id: [String, Number]
})
const router = useRouter()
const form = ref(props.id)
// ref의 장점 한번에 객체 할당을 할 수 있다.
const fetchPost = async () => {
  const { data } = await getPostById(props.id)
  setPost(data)
}
const setPost = ({ title, content }) => {
  post.value.title = title
  post.value.content = content
  post.value.createdAt = Date.now()
}
// 반면 reactive는 한번에 하나의 데이터만 할당 가능
// form.title = data.title; --> 객체 할당 불가능
fetchPost()
const goListPage = () =>
  router.push({
    name: 'PostListView'
  })

const goEditPage = () => {
  router.push({
    name: 'PostEditView',
    params: { id: props.id }
  })
}

const remove = async () => {
  try {
    await deletePost(props.id)
    router.push({ name: 'PostListView' })
  } catch (e) {
    console.log('error: ', e)
  }
}
</script>

<style lang="scss" scoped></style>
