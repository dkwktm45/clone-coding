import { createWebHistory, createRouter } from 'vue-router';
import HomeView from '@/view/HomeView.vue';
import AboutView from '@/view/AboutView.vue';
import PostCreateView from '@/view/posts/PostCreateView.vue';
import PostDetailView from '@/view/posts/PostDetailView.vue';
import PostListView from '@/view/posts/PostListView.vue';
import PostEditView from '@/view/posts/PostEditView.vue';
import NotFoundView from '@/view/NotFoundView.vue';
import NestedView from '@/view/nested/NestedView.vue';
import NestedOneView from '@/view/nested/NestedOneView.vue';
import NestedTwoView from '@/view/nested/NestedTwoView.vue';
import NestedHomeView from '@/view/nested/NestedHomeView.vue';
import MyPage from '@/view/MyPage.vue';

const routes = [
	{
		path: '/',
		name: 'Home',
		component: HomeView,
	},
	{
		path: '/about',
		name: 'About',
		component: AboutView,
	},
	{
		path: '/posts/create',
		name: 'PostCreateView',
		component: PostCreateView,
	},
	{
		path: '/posts/:id',
		name: 'PostDetailView',
		component: PostDetailView,
		props: true,
	},
	{
		path: '/posts',
		name: 'PostListView',
		component: PostListView,
	},
	{
		path: '/posts/:id/edit',
		name: 'PostEditView',
		component: PostEditView,
	},
	{
		path: '/my',
		name: 'MyPageView',
		component: MyPage,
		beforeEnter: (to, from) => {
			// console.log(to);
			// console.log(from);
		},
	},
	{
		path: '/:pathMatch(.*)*',
		name: 'NotFound',
		component: NotFoundView,
	},
	{
		path: '/nested',
		name: 'NestedView',
		component: NestedView,
		children: [
			{
				path: '',
				name: 'NestedHomeView',
				component: NestedHomeView,
			},
			{
				path: 'one',
				name: 'NestedOneView',
				component: NestedOneView,
			},
			{
				path: 'two',
				name: 'NestedTwoView',
				component: NestedTwoView,
			},
		],
	},
];

const router = createRouter({
	history: createWebHistory(),
	routes: routes,
});

/*router.beforeEach((to, from) => {
	console.log('to: ', to);
	console.log('from: ', from);
});*/

export default router;
