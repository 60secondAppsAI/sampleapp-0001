import Vue from 'vue'
import VueRouter from 'vue-router'
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Issues from  '@/pages/Issues.vue';
import IssueDetail from  '@/pages/IssueDetail.vue';
import Comments from  '@/pages/Comments.vue';
import CommentDetail from  '@/pages/CommentDetail.vue';
import Projects from  '@/pages/Projects.vue';
import ProjectDetail from  '@/pages/ProjectDetail.vue';
import Tasks from  '@/pages/Tasks.vue';
import TaskDetail from  '@/pages/TaskDetail.vue';
import Attachments from  '@/pages/Attachments.vue';
import AttachmentDetail from  '@/pages/AttachmentDetail.vue';

Vue.use(VueRouter)

let routes = [
	{
		// will match everything
		path: '*',
		component: () => import('../views/404.vue'),
	},
	{
		path: '/',
		name: 'Home',
			redirect: '/users',
							},
	{
		path: '/dashboard',
		name: 'Dashboard',
		layout: "dashboard",
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "dashboard" */ '../views/Dashboard.vue'),
	},
	{
		path: '/layout',
		name: 'Layout',
		layout: "dashboard",
		component: () => import('../views/Layout.vue'),
	},
	{
		path: '/users',
		name: 'Users',
		layout: "dashboard",
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: "dashboard",
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/issues',
		name: 'Issues',
		layout: "dashboard",
		component: Issues,
	},
	{
	    path: '/issue/:issueId', 
	    name: 'IssueDetail',
		layout: "dashboard",
	    component: IssueDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/comments',
		name: 'Comments',
		layout: "dashboard",
		component: Comments,
	},
	{
	    path: '/comment/:commentId', 
	    name: 'CommentDetail',
		layout: "dashboard",
	    component: CommentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/projects',
		name: 'Projects',
		layout: "dashboard",
		component: Projects,
	},
	{
	    path: '/project/:projectId', 
	    name: 'ProjectDetail',
		layout: "dashboard",
	    component: ProjectDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tasks',
		name: 'Tasks',
		layout: "dashboard",
		component: Tasks,
	},
	{
	    path: '/task/:taskId', 
	    name: 'TaskDetail',
		layout: "dashboard",
	    component: TaskDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/attachments',
		name: 'Attachments',
		layout: "dashboard",
		component: Attachments,
	},
	{
	    path: '/attachment/:attachmentId', 
	    name: 'AttachmentDetail',
		layout: "dashboard",
	    component: AttachmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/requests/quickadd',
		name: 'QuickAdd',
		layout: "dashboard",
		meta: {
			title: 'quickadd',
			sidebarMap: ['applications'],
			breadcrumbs: ['Requests', 'QuickAdd'],
		},
		component: () => import('../pages/QuickAdd.vue'),
	},
	{
		path: '/tables',
		name: 'Tables',
		layout: "dashboard",
		component: () => import('../views/Tables.vue'),
	},
	{
		path: '/billing',
		name: 'Billing',
		layout: "dashboard",
		component: () => import('../views/Billing.vue'),
	},
	{
		path: '/rtl',
		name: 'RTL',
		layout: "dashboard-rtl",
		meta: {
			layoutClass: 'dashboard-rtl',
		},
		component: () => import('../views/RTL.vue'),
	},
	{
		path: '/Profile',
		name: 'Profile',
		layout: "dashboard",
		meta: {
			layoutClass: 'layout-profile',
		},
		component: () => import('../views/Profile.vue'),
	},
	{
		path: '/sign-in',
		name: 'Sign-In',
		component: () => import('../views/Sign-In.vue'),
	},
	{
		path: '/sign-up',
		name: 'Sign-Up',
		meta: {
			layoutClass: 'layout-sign-up',
		},
		component: () => import('../views/Sign-Up.vue'),
	},
]

// Adding layout property from each route to the meta
// object so it can be accessed later.
function addLayoutToRoute( route, parentLayout = "default" )
{
	route.meta = route.meta || {} ;
	route.meta.layout = route.layout || parentLayout ;
	
	if( route.children )
	{
		route.children = route.children.map( ( childRoute ) => addLayoutToRoute( childRoute, route.meta.layout ) ) ;
	}
	return route ;
}

routes = routes.map( ( route ) => addLayoutToRoute( route ) ) ;

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes,
	scrollBehavior (to, from, savedPosition) {
		if ( to.hash ) {
			return {
				selector: to.hash,
				behavior: 'smooth',
			}
		}
		return {
			x: 0,
			y: 0,
			behavior: 'smooth',
		}
	}
})

export default router
