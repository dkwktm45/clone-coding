<script setup lang="ts">
import axios from "axios";
import {ref} from "vue";

const posts = ref([]);

axios.get("/api/posts?page=1&size=5").then((response) => {
  response.data.forEach((r: any) => {
    posts.value.push(r)
  })
})

</script>
<template>
  <ul class="list-group list-group-flush">
    <li class="list-group-item" v-for="post in posts" :key="post.id">
        <router-link :to="{name: 'read', params: { postId: post.id } }">{{
            post.title
          }}
        </router-link>
    </li>
  </ul>
</template>
<style scoped >
</style>