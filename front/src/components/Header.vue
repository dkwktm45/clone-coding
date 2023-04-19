<template>
  <el-header class="header">
    <!--        <RouterLink to="/">Home</RouterLink>
            <RouterLink to="/write">write</RouterLink>-->
    <el-menu mode="horizontal">
      <el-menu-item index="1"><RouterLink to="/">Home</RouterLink></el-menu-item>
      <el-menu-item index="2"><RouterLink to="/write">write</RouterLink></el-menu-item>
    </el-menu>
  </el-header>
</template>

<script>
export default {
  name: "Header.vue"
}
</script>

<style scoped>

.header{
  padding: 0;
}
</style>