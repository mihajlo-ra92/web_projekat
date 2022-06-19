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
				const addressFilter = this.sportObjects.filter(so => so.location.address.toLowerCase().includes(this.search.toLowerCase()));
				const gradeFilter = this.sportObjects.filter(so => so.avegareGrade == this.search);
				const allFilters = nameFilter.concat(objectTypeFilter, addressFilter, gradeFilter);
				const final = Array.from(allFilters.reduce((map, obj) => map.set(obj.id,
				obj),new Map()).values());
				return final;
			}
		}
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Raspoloživi sportski objekti:
		<br>
		<input type="text" v-model="search" placeholder="Pretraga objekata"/>
		<br>
		<table class="table table-bordered table-hover"">
			<tr bgcolor="lightgrey">
				<th scope="col">Naziv</th>
				<th scope="col">Tip objekta</th>
				<th scope="col">Status</th>
				<th scope="col">Adresa</th>
				<th scope="col">Ocena</th>
			</tr>
				
			<tr v-for="so in filteredSportObjects">
				<td scope="row">{{so.name }}</td>
				<td>{{so.objectType }}</td>
				<td v-if="so.isOpen">Otvoren</td>
				<td v-else>Zatvoren</td>
				<td>{{so.location.address }}</td>
				<td>{{so.avegareGrade }}</td>
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