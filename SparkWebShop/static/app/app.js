const Home = { template: '<home></home>' }
const SportObjects = { template: '<sport-objects></sport-objects>' }
const Register = { template: '<register></register>' }
const ShoppingCart = { template: '<shopping-cart></shopping-cart>' }
const WebShop = { template: '<web-shop></web-shop>' }
const MyProfil = { template: '<myProfil></myProfil>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Home},
	    { path: '/sc', component: ShoppingCart },
	    { path: '/sportObjects', component: SportObjects},
	    { path: '/Register', component: Register},
	    { path: '/MyProfil', component: MyProfil}
	  ]
});

var app = new Vue({
	router,
	el: '#webShop',
});

