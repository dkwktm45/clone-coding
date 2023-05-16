<template>
  <div v-if="show" class="app-alert alert alert-success" :class="styleClass" role="alert">
    {{ message }}
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  show: {
    type: Boolean,
    default: false
  },
  message: {
    type: String,
    required: true
  },
  type: {
    type: String,
    default: '',
    validator: (value) => ['success', 'error'].includes(value)
  }
})

const styleClass = computed(() => (props.type === 'error' ? 'alert-danger' : 'alert-success'))
</script>

<style scoped>
.app-alert {
  position: fixed;
  top: 10px;
  right: 10px;
}
</style>
