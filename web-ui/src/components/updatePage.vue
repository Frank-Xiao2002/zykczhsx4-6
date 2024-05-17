<template>
  <div class="update-form">
    <form @submit.prevent="update">
      <label for="id">ID：</label>
      <input id="id" class="form-control" type="text" v-model="user.id" readonly="readonly"><br>
      <label for="username">用户名：</label>
      <input id="username" class="form-control" type="text" v-model="user.username"><br>
      <label for="password">密码：</label>
      <input id="password" class="form-control" type="text" v-model="user.password"><br>
      <button class="btn btn-primary btn-lg btn-block" type="submit" @click="update">提交</button>
    </form>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";


const user = ref({
  id: '',
  username: '',
  password: ''
})

defineProps({
  id: String,
  name: String,
  password: String
})

onMounted(() => {
  user.id = route.params.id;
  user.username = route.params.username;
  user.password = route.params.password;
})

function update() {
  axios.put('api/user', this.user)
      .then(response => {
        console.log('User updated successfully', response);
        this.$router.push('/userlists');
      })
      .catch(error => {
        console.error('Update failed:', error);
      });
}

</script>

<style scoped>
/* 适用于 UpdatePage.vue 的样式 */
.update-form {
  max-width: 500px; /* 限制最大宽度，使其不会过宽 */
  margin: auto; /* 水平居中 */
  padding: 20px; /* 增加内边距 */
  background: #fff; /* 白色背景 */
  border-radius: 8px; /* 圆角 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 轻微的阴影 */
  margin-top: 20px; /* 与页面顶部的距离 */
}

.update-form .form-control {
  display: block; /* 确保它们是块级元素 */
  width: calc(100% - 20px); /* 减去 padding 的宽度 */
  margin-bottom: 15px; /* 间距 */
  border: 1px solid #ccc; /* 边框颜色更轻 */
  padding: 10px 10px; /* padding */
}

.update-form .btn {
  width: 100%; /* 自适应内容宽度 */
  padding: 10px 10px; /* 增加左右内边距 */
  background-color: #0056b3; /* 更亮的蓝色 */
  border-color: #0056b3; /* 边框颜色 */
  margin-top: 10px; /* 按钮与输入框间距 */
}

.update-form .btn:hover {
  background-color: #004494; /* 悬停时更深的蓝色 */
  border-color: #004494; /* 边框颜色 */
}

/* 调整只读输入框的样式 */
.update-form .form-control[readonly] {
  background-color: #f8f9fa; /* 更浅的背景颜色 */
  color: #6c757d; /* 文本颜色 */
  cursor: not-allowed; /* 鼠标样式 */
}
</style>
