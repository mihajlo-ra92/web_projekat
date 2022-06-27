Vue.component("sport-object", {
	data: function(){
		return{
			curentSportObject: null,
		}
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		This sport object:
		<br>
		<div v-if="curentSportObject != null">
			<p>Name: {{this.curentSportObject.name}}</p>
			<p>Object type: {{this.curentSportObject.objectType}}</p>
			<p>Last name: {{this.curentSportObject.isOpen}}</p>
			<p>Prosecna ocena: {{this.curentSportObject.avegareGrade}}</p>			
		</div>
	</div>	
	
	
</div>	  
`,

	methods:{
		
	}, 
	mounted () {
		console.log("Mounted sport object!");
        axios
			.get('rest/getSportObject')
      		.then(response => (this.curentSportObject = response.data));
	}
});