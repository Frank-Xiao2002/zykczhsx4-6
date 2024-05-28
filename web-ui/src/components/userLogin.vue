<template>
  <div class="container">
    <div>用户登录</div>
    <el-form :model="login_form" style="max-width: 400px" :rules="rule" ref="ruleFormRef">
      <el-form-item prop="username" label="Username">
        <el-input v-model="login_form.username" placeholder="在此输入用户名"/>
      </el-form-item>
      <el-form-item prop="password" label="Password">
        <el-input v-model="login_form.password" type="password" show-password placeholder="在此输入密码"
                  @keyup.enter="userLogin"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="userLogin">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import axios from 'axios';
import {ref} from "vue";
import router from "@/router/index.js";

const login_form = ref({
  username: '',
  password: ''
})

const rule = ref({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {max: 50, message: '用户名长度不能超过50个字符', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ]
})

async function userLogin() {
  if (login_form.value.username.trim() === '' || login_form.value.password.trim() === '') {
    ElMessage.error({message: "用户名及密码不能为空", grouping: true});
    return;
  }
  try {
    const response = await axios.post('/api/login', {
      username: login_form.value.username,
      password: login_form.value.password
    });
    router.push("/list");
  } catch (error) {
    // console.error(error.response.data);
    // TODO: 优化错误提示
    ElMessage.error({message: "用户名或密码错误", grouping: true});
  }
}

</script>


<style scoped>
</style>
