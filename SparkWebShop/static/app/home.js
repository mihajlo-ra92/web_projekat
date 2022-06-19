Vue.component("home", {
	data: function () {
		    return {
				users: null,
				input: {
                    username: "",
                    password: ""
                },
                approved: false
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Log in:
		<br>
		<input type="text" name="username" v-model="input.username" placeholder="Username" />
		<br>
	    <input type="password" name="password" v-model="input.password" placeholder="Password" />
		<br>
		<button type="button" v-on:click="login()">Login</button>
		<br>
		<br>
		Korisnici:
		<table border="1">
			<tr bgcolor="lightgrey">
				<th>Username</th>
				<th>Password</th>
			</tr>
				
			<tr v-for="user in users">
				<td>{{user.username}}</td>
				<td>{{user.password }}</td>
			</tr>
		</table>
	</div>	
	
	
</div>	  
`
	, 
	methods : {
		login() {
                for (let i = 0; i < this.users.length; i++) {
					if (this.input.username === this.users[i].username &&
					this.input.password === this.users[i].password){
						console.log('pronadjen');
						console.log(this.users[i].username);
						this.approved = true;
						console.log(this.approved);
						router.push('/sportObjects');
					}
            	}
				if(this.approved === false){
					console.log('Neuspesno logovanje!!!!');
				}
        }
	},
	mounted () {
        console.log("Mounted home");
        axios
          .get('rest/proizvodi/getJustUsers')
          .then(response => (this.users = response.data))
    },
});