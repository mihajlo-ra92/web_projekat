Vue.component("home", {
	data: function () {
		    return {
				users: null
		    }
	},
	template: ` 
<div>
	Home:
	<form action="#/sportObjects">
            <input type="submit" value="Go to Google" />
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
		
	},
	mounted () {
        console.log("Mounted home");
        axios
          .get('rest/proizvodi/getJustUsers')
          .then(response => (this.users = response.data))
    },
});