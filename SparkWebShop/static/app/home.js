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
<div>
	Home:
	<input type="text" name="username" v-model="input.username" placeholder="Username" />
    <input type="password" name="password" v-model="input.password" placeholder="Password" />
	<button type="button" v-on:click="login()">Login</button>
	<form action="#/sportObjects">
            <input type="submit" value="Log in" />
        </form>
	<p>
		<a href="#/sportObjects">Pregled sadržaja korpe</a>
	</p>
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
				
            }
	},
	mounted () {
        console.log("Mounted home");
        axios
          .get('rest/proizvodi/getJustUsers')
          .then(response => (this.users = response.data))
    },
});