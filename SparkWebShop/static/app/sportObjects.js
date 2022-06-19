Vue.component("sport-objects", {
	data: function () {
		    return {
		      sportObjects: null,
		      search: ''
		    }
	},
	computed: {
		filteredSportObjects() {
			if(this.sportObjects !== null){
				const nameFilter = this.sportObjects.filter(so => so.name.toLowerCase().includes(this.search.toLowerCase()));
				const objectTypeFilter = this.sportObjects.filter(so => so.objectType.toLowerCase().includes(this.search.toLowerCase()));
				return nameFilter;
			}
		}
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Raspoloživi sportski objekti:
		<input type="text" v-model="search" placeholder="Pretraga objekata"/>
		<br>
		<br>
		<table border="1">
			<tr bgcolor="lightgrey">
				<th>Naziv</th>
				<th>Tip objekta</th>
				<th>Status</th>
				<th>Adresa</th>
			</tr>
				
			<tr v-for="so in filteredSportObjects">
				<td>{{so.name }}</td>
				<td>{{so.objectType }}</td>
				<td v-if="so.isOpen">Otvoren</td>
				<td v-else>Zatvoren</td>
				<td>{{so.location.address }}</td>
			</tr>
		</table>
	</div>		  
</div>
`	  
	, 
	methods : {
		
	},
	mounted () {
		console.log("Mounted sportObjects!");
        axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => (this.sportObjects = response.data))
    },
});	  