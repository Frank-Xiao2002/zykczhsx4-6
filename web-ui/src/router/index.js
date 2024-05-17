import userList from "@/components/userlist.vue";
import updatePage from '@/components/updatePage.vue';
import insertPage from '@/components/insertPage.vue';
import {createRouter, createWebHistory} from 'vue-router';
import userLogin from "@/components/userLogin.vue";

// 创建 router 实例
const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'UserLogin',
            component: userLogin
        },
        {
            path: '/list',
            name: 'UserList',
            component: userList
        },
        {
            path: '/update',
            name: 'Update',
            component: updatePage,
        },
        {
            path: '/insertPage',
            name: 'Insert',
            component: insertPage
        }
    ],
});


export default router;