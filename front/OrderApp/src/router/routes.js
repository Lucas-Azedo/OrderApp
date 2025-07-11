export default [
	{
		path: '/',
		name: 'home',
		component: () => import('@/views/HomeView.vue'),
		meta: { title: 'Home' },
	},
	{
		path: '/login',
		name: 'login',
		component: () => import('@/views/LoginView.vue'),
		meta: { title: 'Login' },
	},
	{
		path: '/register',
		name: 'register',
		component: () => import('@/views/RegisterView.vue'),
		meta: { title: 'Registro' },
	},
	{
		path: '/admin',
		name: 'admin',
		component: () => import('@/views/AdminView.vue'),
		meta: { title: 'Admin' },
	},
	{
		path: '/:pathMatch(.*)*',
		name: 'notFound',
		component: () => import('@/views/NotFoundView.vue'),
		meta: { title: '404' },
	}
];
