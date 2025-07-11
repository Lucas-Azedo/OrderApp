<template>
      <Alert v-model="alert.show" :type="alert.type" :message="alert.message" />

      <div class="flex items-center justify-center p-20">
            <div class="w-full max-w-md p-8 space-y-6 shadow-xl rounded-xl bg-base-200">
                  <h2 class="text-center text-3xl font-bold text-primary">Entrar</h2>

                  <form @submit.prevent="login" class="space-y-4">
                        <div class="form-control">
                              <label class="label" for="credential">
                                    <span class="label-text">Login</span>
                              </label>
                              <input id="credential" v-model="credential" type="text"
                                    placeholder="seu@email.com ou nome de usuário" class="input input-bordered w-full"
                                    required />
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
                                    <span v-else>Entrar</span>
                              </button>
                        </div>
                  </form>

                  <div class="text-sm text-center">
                        Não tem uma conta?
                        <br>
                        <router-link to="/register" class="link link-primary">Cadastre-se</router-link>
                  </div>
            </div>
      </div>
</template>

<script setup>
import { ref } from 'vue'
import Alert from '@/components/Alert.vue'

const credential = ref('')
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

async function login() {
      loading.value = true
      error.value = ''

      try {
            await new Promise(r => setTimeout(r, 1500))

            if (
                  (credential.value === '123@a.com' || credential.value === 'admin') &&
                  password.value === '123'
            ) {
                  showAlert('success', 'Conexão bem-sucedida!')
            } else {
                  showAlert('error', 'Credenciais inválidas')
            }
      } catch (err) {
            error.value = err.message
            showAlert('error', err.message)
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
