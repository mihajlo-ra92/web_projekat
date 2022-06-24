const Home = { template: '<home></home>' }
const SportObjects = { template: '<sport-objects></sport-objects>' }
const Register = { template: '<register></register>' }
const ShoppingCart = { template: '<shopping-cart></shopping-cart>' }
const WebShop = { template: '<web-shop></web-shop>' }
const MyProfile = { template: '<my-profile></my-profile>'}
const EditProfile = { template: '<edit-profile></edit-profile>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Home},
	    { path: '/sc', component: ShoppingCart },
	    { path: '/sportObjects', component: SportObjects},
	    { path: '/register', component: Register},
	    { path: '/edit-profile', component: EditProfile},
	    { path: '/my-profile', component: MyProfile}
	  ]
});

var app = new Vue({
	router,
	el: '#webShop',
});

