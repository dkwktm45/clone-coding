<template>
  <div>
    <div>
      <el-input v-model="post.title" type="text" placeholder="제목을 입력해주세요"/>
    </div>
    <div class="mt-2">
      <el-input v-model="post.content" type="textarea" rows="15"></el-input>
    </div>
    <div class="mt-2">
      <el-button type="warning" @click="edit()">글 작성완료</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import axios from "axios";
import {defineProps, ref} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();

const post = ref({
  id: 0,
  title: "",
  content: ""
});
const props = defineProps({
  postId: {
    type: [Number, String],
    required: true
  }
});

axios.get(`/api/posts/${props.postId}`).then((response) => {
  post.value = response.data
})

const edit = () => {
  axios.patch(`/api/posts/${props.postId}`,post.value).then(() => {
    router.replace({name : "home"})
  })
}
</script>

<style scoped>

</style>