<template>
  <div>
    <h2>Home View</h2>
    <!-- 모든 자식 컴포넌트에서 패스를 설정할 수 있다.-->
    <p>{{ $route.path }}</p>
    <button class="btn btn-primary" @click="$router.push('/about')">About으로 이동</button>
    <AppGrid :items="items" v-slot="{ item }" col-class="col-6">
      <AppCard>{{ item }}</AppCard>
    </AppGrid>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import AppGrid from '@/components/AppGrid.vue'
import AppCard from '@/components/AppCard.vue'
import { ref } from 'vue'
const route = useRoute()

const items = ref(['사과', '딸기', '포도', '바나나'])
console.log('route path', route.path)
</script>

<style lang="scss" scoped></style>
