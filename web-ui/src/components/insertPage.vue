<template>
  <div class="add-user-form">
    <form @submit.prevent="saveUser">
      <label for="username">用户名：</label>
      <input id="username" class="form-control" type="text" v-model="user.username" name="username"><br>
      <label for="password">密 码：</label>
      <input id="password" class="form-control" type="text" v-model="user.password" name="password"
             @keyup.enter="insert"><br>
      <button class="btn btn-primary btn-lg btn-block" type="button" @click="insert">保存</button>
    </form>
  </div>
</template>

<script setup>
import axios from "axios"
import {ref} from "vue"
import router from "@/router/index.js";

const user = ref({
  username: '',
  password: ''
})

function insert() {
  axios.post('/api/user', user.value)
      .then(response => {
        console.log('User added successfully');
        router.push('/list');
      })
      .catch(error => {
        console.error('Error adding user', error.response.data);
      });
}

</script>

<style scoped>
/* 样式与 UpdatePage.vue 保持一致 */
.add-user-form {
  max-width: 500px; /* 宽度与 UpdatePage.vue 一致 */
  margin: auto; /* 水平居中 */
  padding: 20px; /* 内边距调整 */
  background: #fff; /* 背景颜色 */
  border-radius: 8px; /* 边角圆润 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
  margin-top: 20px; /* 顶部间距 */
}

.form-control {
  display: block;
  width: calc(100% - 20px); /* 减去 padding 的宽度 */
  margin-bottom: 15px; /* 间距调整 */
  border: 1px solid #ccc; /* 边框颜色调整 */
  padding: 10px; /* 内边距调整 */
}

.btn {
  width: 100%; /* 按钮宽度 */
  padding: 10px 20px; /* 内边距调整 */
  background-color: #0056b3; /* 背景颜色调整 */
  border-color: #0056b3; /* 边框颜色调整 */
  margin-top: 10px; /* 顶部间距调整 */
}

.btn:hover {
  background-color: #004494; /* 鼠标悬停时的背景颜色调整 */
  border-color: #004494; /* 边框颜色调整 */
}

.form-control[readonly] {
  background-color: #f8f9fa; /* 只读输入框的背景颜色 */
  color: #6c757d; /* 文本颜色 */
  cursor: not-allowed; /* 鼠标样式 */
}
</style>