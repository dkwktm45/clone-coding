<template>
  <div>
    <h2>{{ post.title }}</h2>
    <div>{{ post.content }}</div>
    <el-button @click="moveToEdit()" type="warning">
      수정
    </el-button>
  </div>
</template>

<script setup lang="ts">
import {defineProps, onMounted, ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const router = useRouter();

const moveToEdit = () => {
  router.push({name: 'edit',params : {postId : props.postId}})
}

const props = defineProps({
  postId: {
    type: [Number, String],
    required: true
  }
});

const post = ref({
  id: 0,
  title: "",
  content: ""
});
onMounted(() => {
  axios.get(`/api/posts/${props.postId}`).then((response) => {
    post.value = response.data
  })
})
</script>
<style scoped>
</style>