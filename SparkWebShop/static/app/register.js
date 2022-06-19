Vue.component("register", {
	data: function () {
		return {
			user: {
				username: 'tUsername',
				password: 'tPassword',
				firstName: 'tFName'
			}
		}
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		<p>Register!!!!</p>
		<button v-on:click="register" >Register test</button>
	</div>		  
</div>
`	  
	, 
	methods : {
		register : function (){
		console.log("Register!!!");
		axios
		    .post('/rest/proizvodi/register', this.user)
		    .then(response => (console.log(response)))
		    .catch((error) => console.log(error))
		}
	},
	mounted () {
		console.log("Mounted register!");
        axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => (this.sportObjects = response.data))
    },
});	  