<template>
  <div>
    <el-input v-model="title" type="text" placeholder="제목을 입력해주세요"/>
  </div>
  <div class="mt-2">
    <el-input v-model="content" type="textarea" rows="15"></el-input>
  </div>
  <div class="mt-2">
    <el-button type="primary" @click="write()">글 작성완료</el-button>
  </div>
</template>

<script setup lang="ts">
import axios from "axios";
import {ref} from "vue";
const title = ref("")
const content = ref("")
const count = ref(0);

const write = () => {
  axios.post("http://localhost:8080/posts",{
    title: title.value,
    content: content.value
  })
}
</script>

<style scoped>

</style>