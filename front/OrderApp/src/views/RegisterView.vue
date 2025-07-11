<template>
      <Alert v-model="alert.show" :type="alert.type" :message="alert.message" />

      <div class="flex items-center justify-center p-20">
            <div class="w-full max-w-md p-8 space-y-6 shadow-xl rounded-xl bg-base-200">
                  <h2 class="text-center text-3xl font-bold text-primary">Registrar</h2>
                  <form @submit.prevent="register" class="space-y-4">
                        <div class="form-control">
                              <label class="label" for="username">
                                    <span class="label-text">Usuário</span>
                              </label>
                              <input id="username" v-model="username" type="text" placeholder="seu_usuario"
                                    class="input input-bordered w-full" required />
                        </div>

                        <div class="form-control">
                              <label class="label" for="email">
                                    <span class="label-text">Email</span>
                              </label>
                              <input id="email" v-model="email" type="email" placeholder="seu@email.com"
                                    class="input input-bordered w-full" required />
                        </div>

                        <div class="form-control">
                              <label class="label" for="password">
                                    <span class="label-text">Senha</span>
                              </label>
                              <input id="password" v-model="password" type="password" placeholder="••••••••"
                                    class="input input-bordered w-full" required />
                        </div>

                        <div class="form-control">
                              <button class="btn btn-primary w-full" :disabled="loading">
                                    <span v-if="loading" class="loading loading-spinner"></span>
                                    <span v-else>Registrar</span>
                              </button>
                        </div>
                  </form>

                  <div class="text-sm text-center">
                        Já tem uma conta?
                        <br>
                        <router-link to="/login" class="link link-primary">Entre</router-link>
                  </div>
            </div>
      </div>
</template>

<script setup>
import { ref } from 'vue'
import Alert from '@/components/Alert.vue'

const username = ref('') 
const email = ref('')
const password = ref('')
const loading = ref(false)
const error = ref('')

const alert = ref({
      show: false,
      type: 'info',
      message: ''
})

function showAlert(type, message) {
      alert.value = { show: true, type, message }
}

const register = async () => {
      loading.value = true
      error.value = ''

      try {
            await new Promise((resolve) => setTimeout(resolve, 1500))

            if (username.value === 'admin' && email.value === '123@a.com' && password.value === '123') {
                  showAlert('success', 'Registro bem-sucedido')
            } else {
                  showAlert('error', 'Credenciais inválidas')
            }
      } catch (err) {
            error.value = err.message
            showAlert('error', error.value)
      } finally {
            loading.value = false
      }
}
</script>

<style lang="scss" scoped>
.input {
      transition: border-color 0.2s ease;

      &:focus {
            border-color: theme('colors.primary');
      }
}
</style>
