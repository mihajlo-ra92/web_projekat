const Home = { template: '<home></home>' }
const SportObjects = { template: '<sport-objects></sport-objects>' }
const Register = { template: '<register></register>' }
const ShoppingCart = { template: '<shopping-cart></shopping-cart>' }
const Profile = { template: '<profile></profile>'}
const WebShop = { template: '<web-shop></web-shop>' }
const MyProfile = { template: '<my-profile></my-profile>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Home},
	    { path: '/sc', component: ShoppingCart },
	    { path: '/sportObjects', component: SportObjects},
	    { path: '/register', component: Register},
	    { path: '/profile', component: Profile},
	    { path: '/my-profile', component: MyProfile}
	  ]
});

var app = new Vue({
	router,
	el: '#webShop',
});

