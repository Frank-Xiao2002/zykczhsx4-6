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
            component: userLogin
        },
        {
            path: '/list',
            component: userList
        },
        {
            path: '/update/:id',
            name: 'Update',
            component: updatePage,
            props: true
        },
        {
            path: '/insert',
            component: insertPage
        }
    ],
});


export default router;