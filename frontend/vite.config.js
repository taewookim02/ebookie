import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue({
        template: {
            compilerOptions: {
                isCustomElement: tag => tag.includes("swiper")
            }
        }
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  build: {
    outDir: "../backend/src/main/resources/static",
    emptyOutDir: true,
  },
  server: {
    proxy: {
        "/api": "http://localhost:8081",
    },
  },
})
