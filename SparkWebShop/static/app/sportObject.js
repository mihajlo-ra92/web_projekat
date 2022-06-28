Vue.component("sport-object", {
	data: function(){
		return{
			SportObject : {},
		}
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		This sport object:
		<br>
		<div v-if="SportObject  != null">
		<br>
			<p>Name:{{this.SportObject.name}}</p>
			<p>Object type:{{this.SportObject.objectType}}</p>
			<p>Is open:{{this.SportObject.isOpen}}</p>
			<p>Average grade:{{this.SportObject.avegareGrade}}</p>
		
			<table v-if="SportObject.content != null">
				<tr bgcolor="lightgrey">
					<th scope="col">Content</th>
				</tr>
				
				<tr v-for="so in SportObject.content">
					<td scope="row">{{so}}</td>
				</tr>
			</table>			
			<input type="text">
			<button v-on:click="inputField">Add content</button>
		</div>
	</div>	
	
	
</div>	  
`,

	methods:{
		inputField : function(){
			console.log("Treba da se doda post za content");
		}
	}, 
	mounted () {
		console.log("Mounted sport object!");
			axios
			.get('/rest/MenagersSportObject')
			.then(response => (
				this.SportObject  = response.data
			))
		},
});