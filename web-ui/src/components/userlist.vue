<script setup>
import axios from 'axios';
import {onMounted, ref} from "vue";
import router from "@/router/index.js";


const users = ref([])

function fetchUsers() {
  axios.get('/api/user') // 使用后端提供的API路径
      .then(response => {
        users.value = response.data; // 假设响应数据是直接的用户列表
      })
      .catch(error => {
        console.error('Error fetching users:', error);
      });
}

function deleteUser(id) {
  axios.delete('/api/user/' + id) // 使用后端提供的API路径
      .then(() => {
        fetchUsers(); // 重新获取用户列表
      })
      .catch(error => {
        console.error('Error deleting user:', error);
      });
}

function goToUpdatePage(id) {
  console.log('receive id=', id)
  router.push({
    name: 'Update',
    params: {
      id: id,
    }
  });
}


onMounted(() => {
  fetchUsers();
})

</script>


<template>
  <el-button type="success" size="large">
    <RouterLink to="/insert">Add User</RouterLink>
  </el-button>

  <el-table :data="users" stripe style="width: 100%" border>
    <el-table-column align="center" prop="id" label="ID"/>
    <el-table-column align="center" prop="username" label="Username"/>
    <el-table-column align="center" prop="password" label="Password"/>
    <el-table-column align="center" label="Operation">
      <template v-slot="scope123">
        <el-button link type="primary" @click.prevent="goToUpdatePage(scope123.row.id)">Modify</el-button>
        <el-button link type="primary" @click.prevent="deleteUser(scope123.row.id)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>

</template>


<style scoped>

</style>
