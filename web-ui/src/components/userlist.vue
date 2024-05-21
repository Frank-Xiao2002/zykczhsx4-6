<template>
  <el-button type="success" size="large">
    <RouterLink to="/insert">Add User</RouterLink>
  </el-button>

  <!--TODO finish operation buttons-->
  <el-table :data="users" stripe style="width: 100%" border>
    <el-table-column prop="id" label="ID"/>
    <el-table-column prop="username" label="Username"/>
    <el-table-column prop="password" label="Password"/>
    <el-table-column label="Operation">
      <template #default="scope">
        <el-button link type="primary" @click.prevent="goToUpdatePage()">Change</el-button>
        <el-button link type="primary" @click.prevent="deleteUser(id)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>

  <div class="table-container">
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>Id</th>
          <th>Username</th>
          <th>Password</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.password }}</td>
          <td>
            <button class="btn btn-primary" @click="goToUpdatePage(user.id)">更改</button>
            <button class="btn btn-danger" @click="deleteUser(user.id)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

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


<style scoped>
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

.userlistheader {
  background-color: #023d7e;
  color: #023d7e;
  font-color: white;
  padding: 10px 20px;

  text-align: center; /* 修改这里使文字居中 */
}

.userlistheader .btn-add-user {
  background-color: #023d7e;
  color: white;
  border: 1px solid #023d7e;
  padding: 10px 20px;
  border-radius: 5px;
  text-decoration: none;
}

.table-container {
  padding: 20px;
  background-color: #f9f9f9;
}

.table {
  width: 100%;
  margin-bottom: 0;
  text-align: center; /* Center table content */
}

.table thead th {
  background-color: #023d7e;
  color: white;
  border-color: #023d7e;
}

.table td, .table th {
  padding: .75rem;
  vertical-align: top;
  border-top: 1px solid #dee2e6;
}

.table-bordered {
  border: 1px solid #dee2e6;
}

.btn-primary, .btn-danger {
  padding: .375rem .75rem;
  border-radius: .25rem;
  color: white;
  text-decoration: none;
  display: inline-block;
  font-size: .875rem;
}

.btn-primary {
  background-color: #023d7e;
  border-color: #023d7e;
}

.btn-danger {
  background-color: #dc3545;
  border-color: #dc3545;
}

.btn-primary:hover, .btn-danger:hover {
  opacity: 0.85;
}
</style>
