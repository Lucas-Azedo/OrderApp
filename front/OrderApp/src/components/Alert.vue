<template>
  <Transition name="fade">
    <div v-if="modelValue" role="alert"
      class="alert alert-soft fixed top-0 left-1/2 -translate-x-1/2 mt-2 z-20 shadow-md text-center flex items-center justify-center px-6 py-3"
      :class="{
        'alert-success': type === 'success',
        'alert-info': type === 'info',
        'alert-warning': type === 'warning',
        'alert-error': type === 'error'
      }">
      <span class="text-base">{{ message }}</span>
    </div>
  </Transition>
</template>

<script setup>
import { watch, onBeforeUnmount } from 'vue'

const props = defineProps({
  type: {
    type: String,
    default: 'info',
    validator: v => ['success', 'info', 'warning', 'error'].includes(v)
  },
  message: { type: String, required: true },
  modelValue: { type: Boolean, default: false },
  duration: { type: Number, default: 3000 }
})

const emit = defineEmits(['update:modelValue'])

function close() {
  emit('update:modelValue', false)
}

let timer
watch(() => props.modelValue, val => {
  clearTimeout(timer)
  if (val && props.duration) {
    timer = setTimeout(close, props.duration)
  }
}, { immediate: true })

onBeforeUnmount(() => clearTimeout(timer))
</script>

<style scoped lang="scss">
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.25s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
