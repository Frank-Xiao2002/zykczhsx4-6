<template>
  <div class="container">
    <el-form>
      <div>用户登录</div>
      <div>
        <el-input v-model="username" placeholder="请输入用户名"></el-input>
        <div v-if="!username && submitAttempted" class="error-msg">用户名不能为空</div>
        <el-input v-model="password" type="password" placeholder="请输入密码"
                  @keyup.enter="userLogin"></el-input>
        <div v-if="!password && submitAttempted" class="error-msg">密码不能为空</div>
        <el-button @click="userLogin">登录</el-button>
        <div>{{ errorMsg }}</div>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import axios from 'axios';
import {ref} from "vue";

const username = ref('')
const password = ref('')
const errorMsg = ref('')
const submitAttempted = ref(false)

function userLogin() {
  errorMsg.value = '';
  submitAttempted.value = true;

  axios.post('/users/login', {
    username: username.value,
    password: password.value
  }).then(response => {
    console.log("登录成功", response);
    this.$router.push("/userlists");
  }).catch(error => {
    console.error(error.response.data)
    errorMsg.value = "登录失败: " + (error.response.data || "服务器错误");
    console.error(errorMsg.value);
  });
}
</script>


<style scoped>
</style>
